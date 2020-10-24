// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.managers;

import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.GuiMultiplayer;
import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordRPC;
import net.minecraft.client.Minecraft;

public class DiscordManager
{
    private static final Minecraft mc;
    private static final DiscordRPC rpc;
    public static DiscordRichPresence rp;
    private static String details;
    private static String state;
    
    public static void startup() {
        System.out.println("[AnarchyWare] Discord RPC Starting!");
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordManager.rpc.Discord_Initialize("768219685559861249", handlers, true, "");
        DiscordManager.rp.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordManager.rp.largeImageKey = "logo";
        DiscordManager.rp.largeImageText = "AnarchyWare 1.0.0 Indev";
        DiscordManager.rp.smallImageText = DiscordManager.mc.func_110432_I().func_111285_a();
        DiscordManager.rpc.Discord_UpdatePresence(DiscordManager.rp);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    DiscordManager.details = "In the menus";
                    DiscordManager.state = "https://discord.gg/YsdCADk";
                    if (DiscordManager.mc.func_71387_A()) {
                        DiscordManager.details = "Singleplayer - " + DiscordManager.mc.func_71401_C().func_71221_J();
                    }
                    else if (DiscordManager.mc.field_71462_r instanceof GuiMultiplayer) {
                        DiscordManager.details = "Multiplayer Menu";
                    }
                    else if (DiscordManager.mc.func_147104_D() != null) {
                        DiscordManager.details = "On " + DiscordManager.mc.func_147104_D().field_78845_b.toLowerCase();
                    }
                    else if (DiscordManager.mc.field_71462_r instanceof GuiWorldSelection) {
                        DiscordManager.details = "Singleplayer Menu";
                    }
                    DiscordManager.rp.details = DiscordManager.details;
                    DiscordManager.rp.state = DiscordManager.state;
                    DiscordManager.rpc.Discord_UpdatePresence(DiscordManager.rp);
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }, "RPC-Callback-Handler").start();
    }
    
    public static void shutdown() {
        System.out.println("[AnarchyWare] Discord RPC Shutting Down!");
        DiscordManager.rpc.Discord_Shutdown();
    }
    
    static {
        mc = Minecraft.func_71410_x();
        rpc = DiscordRPC.INSTANCE;
        DiscordManager.rp = new DiscordRichPresence();
    }
}
