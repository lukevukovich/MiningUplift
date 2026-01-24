package com.vuzili.uplift.world;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

/**
 * Persists portal-to-portal links for the cave dimension portals.
 * Links are keyed by the exact portal position (encoded as long), not by chunk,
 * to support multiple portals and precise linking like vanilla Nether portals.
 */
public class CavePortalLinkData extends WorldSavedData {
    public static final String DATA_NAME = "uplift_portal_links";

    // Maps portal position (encoded as long) in THIS dimension to linked portal position in OTHER dimension
    private final Map<Long, BlockPos> portalLinks = new HashMap<>();

    public CavePortalLinkData() {
        super(DATA_NAME);
    }

    @Override
    public void read(CompoundNBT nbt) {
        portalLinks.clear();
        ListNBT list = nbt.getList("portal_links", 10); // 10 = Compound
        for (int i = 0; i < list.size(); i++) {
            CompoundNBT entry = list.getCompound(i);
            long key = entry.getLong("portal_pos");
            int x = entry.getInt("linked_x");
            int y = entry.getInt("linked_y");
            int z = entry.getInt("linked_z");
            portalLinks.put(key, new BlockPos(x, y, z));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        ListNBT list = new ListNBT();
        for (Map.Entry<Long, BlockPos> e : portalLinks.entrySet()) {
            CompoundNBT entry = new CompoundNBT();
            entry.putLong("portal_pos", e.getKey());
            BlockPos p = e.getValue();
            entry.putInt("linked_x", p.getX());
            entry.putInt("linked_y", p.getY());
            entry.putInt("linked_z", p.getZ());
            list.add(entry);
        }
        nbt.put("portal_links", list);
        return nbt;
    }

    /**
     * Get the linked destination portal position for a portal at the given position.
     * @param portalPos The portal position in this dimension
     * @return The linked portal position in the other dimension, or null if not linked
     */
    public BlockPos getLinkedPortal(BlockPos portalPos) {
        return portalLinks.get(portalPos.toLong());
    }

    /**
     * Store a link from a portal position in this dimension to a portal in another dimension.
     * @param portalPos The portal position in this dimension (the "leader" block of the portal cluster)
     * @param linkedPos The linked portal position in the other dimension
     */
    public void setLinkedPortal(BlockPos portalPos, BlockPos linkedPos) {
        portalLinks.put(portalPos.toLong(), linkedPos);
        markDirty();
    }

    /**
     * Remove a portal link (e.g., when a portal is destroyed).
     * @param portalPos The portal position to unlink
     */
    public void removeLink(BlockPos portalPos) {
        if (portalLinks.remove(portalPos.toLong()) != null) {
            markDirty();
        }
    }

    /**
     * Find the nearest linked portal to a given position within a search radius.
     * Useful for finding which portal link applies when player enters a portal.
     * @param pos The position to search from
     * @param maxDistance Maximum search distance
     * @return The nearest linked portal position and its destination, or null if none found
     */
    public Map.Entry<BlockPos, BlockPos> findNearestLink(BlockPos pos, int maxDistance) {
        Map.Entry<BlockPos, BlockPos> nearest = null;
        double nearestDistSq = maxDistance * maxDistance;
        
        for (Map.Entry<Long, BlockPos> entry : portalLinks.entrySet()) {
            BlockPos portalPos = BlockPos.fromLong(entry.getKey());
            double distSq = portalPos.distanceSq(pos);
            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                final BlockPos foundPortal = portalPos;
                final BlockPos linkedPos = entry.getValue();
                nearest = new java.util.AbstractMap.SimpleEntry<>(foundPortal, linkedPos);
            }
        }
        return nearest;
    }

    /**
     * Check if any portal within a radius has a link.
     */
    public boolean hasLinkNear(BlockPos pos, int radius) {
        for (Long key : portalLinks.keySet()) {
            BlockPos portalPos = BlockPos.fromLong(key);
            if (portalPos.withinDistance(pos, radius)) {
                return true;
            }
        }
        return false;
    }

    public static CavePortalLinkData get(ServerWorld world) {
        return world.getSavedData().getOrCreate(CavePortalLinkData::new, DATA_NAME);
    }
}
