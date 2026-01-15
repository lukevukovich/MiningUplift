package com.vuzili.uplift.network;

import java.util.concurrent.atomic.AtomicInteger;

import com.vuzili.uplift.Uplift;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * Forge networking setup for Uplift (1.15.2).
 * Provides a {@link SimpleChannel} to register server/client packets.
 */
public final class NetworkHandler {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Uplift.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    private static final AtomicInteger ID = new AtomicInteger(0);

    private NetworkHandler() {}

    public static void register() {
        // Register teleport overlay packet (server -> client)
        CHANNEL.registerMessage(
            nextId(),
            TeleportOverlayPacket.class,
            TeleportOverlayPacket::encode,
            TeleportOverlayPacket::decode,
            TeleportOverlayPacket::handle
        );
    }

    private static int nextId() {
        return ID.getAndIncrement();
    }

    /**
     * Send teleport overlay packet to a specific player.
     */
    public static void sendTeleportOverlay(ServerPlayerEntity player, boolean show, String message, int durationTicks) {
        CHANNEL.send(
            PacketDistributor.PLAYER.with(() -> player),
            new TeleportOverlayPacket(show, message, durationTicks)
        );
    }
}
