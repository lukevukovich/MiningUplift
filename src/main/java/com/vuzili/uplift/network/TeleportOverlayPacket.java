package com.vuzili.uplift.network;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Server-to-client packet to trigger portal overlay display.
 * Contains a message to show during the teleport and whether to show/hide the overlay.
 */
public class TeleportOverlayPacket {
    
    private final boolean show;
    private final String message;
    private final int durationTicks;

    public TeleportOverlayPacket(boolean show, String message, int durationTicks) {
        this.show = show;
        this.message = message;
        this.durationTicks = durationTicks;
    }

    public static void encode(TeleportOverlayPacket msg, PacketBuffer buf) {
        buf.writeBoolean(msg.show);
        buf.writeString(msg.message);
        buf.writeInt(msg.durationTicks);
    }

    public static TeleportOverlayPacket decode(PacketBuffer buf) {
        boolean show = buf.readBoolean();
        String message = buf.readString(256);
        int durationTicks = buf.readInt();
        return new TeleportOverlayPacket(show, message, durationTicks);
    }

    public static void handle(TeleportOverlayPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Run on client main thread
            TeleportOverlayHandler.handlePacket(msg.show, msg.message, msg.durationTicks);
        });
        ctx.get().setPacketHandled(true);
    }
}
