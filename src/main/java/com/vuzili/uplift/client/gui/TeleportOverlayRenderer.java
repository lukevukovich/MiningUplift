package com.vuzili.uplift.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vuzili.uplift.network.TeleportOverlayHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Renders the teleport overlay effect on the client screen.
 * Shows a purple-tinted portal effect with a message during dimension teleportation.
 */
@OnlyIn(Dist.CLIENT)
public class TeleportOverlayRenderer {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            TeleportOverlayHandler.tick();
        }
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
        if (!TeleportOverlayHandler.isOverlayActive()) return;

        Minecraft mc = Minecraft.getInstance();
        int screenWidth = mc.getMainWindow().getScaledWidth();
        int screenHeight = mc.getMainWindow().getScaledHeight();
        float fade = TeleportOverlayHandler.getFadeProgress();

        // Render black/purple overlay
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableAlphaTest();

        // Purple-ish portal color with fade
        int alpha = (int) (255 * fade);
        int color = (alpha << 24) | (0x10 << 16) | (0x05 << 8) | 0x20; // Dark purple

        // Fill screen with color
        AbstractGui.fill(0, 0, screenWidth, screenHeight, color);

        // Add secondary lighter purple overlay for portal effect
        int alpha2 = (int) (100 * fade);
        int color2 = (alpha2 << 24) | (0x8B << 16) | (0x00 << 8) | 0xCF; // Portal purple
        AbstractGui.fill(0, 0, screenWidth, screenHeight, color2);

        RenderSystem.enableAlphaTest();
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();

        // Render teleport message
        String message = TeleportOverlayHandler.getOverlayMessage();
        if (message != null && !message.isEmpty()) {
            int textAlpha = (int) (255 * fade);
            int textColor = (textAlpha << 24) | 0xFFFFFF; // White with alpha
            
            int textWidth = mc.fontRenderer.getStringWidth(message);
            int textX = (screenWidth - textWidth) / 2;
            int textY = screenHeight / 2 - 10;
            
            // Shadow for better visibility
            mc.fontRenderer.drawStringWithShadow(message, textX, textY, textColor);
            
            // Draw loading dots animation
            int ticksRemaining = TeleportOverlayHandler.getTicksRemaining();
            int totalDuration = TeleportOverlayHandler.getTotalDuration();
            int elapsed = totalDuration - ticksRemaining;
            int dotCount = (elapsed / 10) % 4;
            String dots = "";
            for (int i = 0; i < dotCount; i++) {
                dots += ".";
            }
            int dotsWidth = mc.fontRenderer.getStringWidth(dots);
            mc.fontRenderer.drawStringWithShadow(dots, (screenWidth - dotsWidth) / 2, textY + 15, textColor);
        }
    }
}
