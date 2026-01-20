package com.vuzili.uplift.world;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

public class CavePortalLinkData extends WorldSavedData {
    public static final String DATA_NAME = "uplift_portal_links";

    private final Map<Long, BlockPos> linksByChunk = new HashMap<>();

    public CavePortalLinkData() {
        super(DATA_NAME);
    }

    @Override
    public void read(CompoundNBT nbt) {
        linksByChunk.clear();
        ListNBT list = nbt.getList("links", 10); // 10 = Compound
        for (int i = 0; i < list.size(); i++) {
            CompoundNBT entry = list.getCompound(i);
            long key = entry.getLong("chunk");
            int x = entry.getInt("x");
            int y = entry.getInt("y");
            int z = entry.getInt("z");
            linksByChunk.put(key, new BlockPos(x, y, z));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        ListNBT list = new ListNBT();
        for (Map.Entry<Long, BlockPos> e : linksByChunk.entrySet()) {
            CompoundNBT entry = new CompoundNBT();
            entry.put("chunk", LongNBT.valueOf(e.getKey()));
            BlockPos p = e.getValue();
            entry.putInt("x", p.getX());
            entry.putInt("y", p.getY());
            entry.putInt("z", p.getZ());
            list.add(entry);
        }
        nbt.put("links", list);
        return nbt;
    }

    public BlockPos getLink(long chunkKey) {
        return linksByChunk.get(chunkKey);
    }

    public void putLink(long chunkKey, BlockPos pos) {
        linksByChunk.put(chunkKey, pos);
        markDirty();
    }

    public static CavePortalLinkData get(ServerWorld world) {
        return world.getSavedData().getOrCreate(CavePortalLinkData::new, DATA_NAME);
    }
}
