package com.vuzili.uplift.network;

/**
 * Client-side handler for teleport overlay state.
 * Stores whether the overlay is visible and the message to display.
 */
public class TeleportOverlayHandler {
    
    private static boolean overlayActive = false;
    private static String overlayMessage = "";
    private static int overlayTicksRemaining = 0;
    private static int totalDuration = 0;
    private static float fadeProgress = 0.0f;

    public static void handlePacket(boolean show, String message, int durationTicks) {
        overlayActive = show;
        overlayMessage = message;
        overlayTicksRemaining = durationTicks;
        totalDuration = durationTicks;
        if (show) {
            fadeProgress = 0.0f;
        }
    }

    public static boolean isOverlayActive() {
        return overlayActive;
    }

    public static String getOverlayMessage() {
        return overlayMessage;
    }

    public static float getFadeProgress() {
        return fadeProgress;
    }

    public static int getTicksRemaining() {
        return overlayTicksRemaining;
    }

    public static int getTotalDuration() {
        return totalDuration;
    }

    /**
     * Called each client tick to update overlay state.
     */
    public static void tick() {
        if (overlayActive) {
            if (overlayTicksRemaining > 0) {
                overlayTicksRemaining--;
                // Fade in during first 10 ticks, fade out during last 10 ticks
                int fadeInTicks = 10;
                int fadeOutTicks = 10;
                int elapsed = totalDuration - overlayTicksRemaining;
                
                if (elapsed < fadeInTicks) {
                    fadeProgress = (float) elapsed / fadeInTicks;
                } else if (overlayTicksRemaining < fadeOutTicks) {
                    fadeProgress = (float) overlayTicksRemaining / fadeOutTicks;
                } else {
                    fadeProgress = 1.0f;
                }
            } else {
                overlayActive = false;
                fadeProgress = 0.0f;
            }
        }
    }

    /**
     * Force hide the overlay (e.g., on dimension change complete).
     */
    public static void hide() {
        overlayActive = false;
        overlayTicksRemaining = 0;
        fadeProgress = 0.0f;
    }
}
