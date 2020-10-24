// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import me.zero.anarchyware.module.Module;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import me.zero.anarchyware.command.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import me.zero.anarchyware.managers.SettingsManager;
import me.zero.anarchyware.module.ModuleManager;
import me.zero.anarchyware.managers.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;

@Mod(name = "AnarchyWare", modid = "clientbase", version = "1.0.0 Indev", acceptedMinecraftVersions = "[1.12.2]")
public class AnarchyWare
{
    protected Minecraft mc;
    public static final String name = "AnarchyWare";
    public static final String version = "1.0.0 Indev";
    public static String topcornertext;
    public static ConfigManager configManager;
    public static ModuleManager moduleManager;
    public static SettingsManager settingsManager;
    public static String prefix;
    public static final String discordAppId = "768219685559861249";
    public static String chatprefix;
    
    public AnarchyWare() {
        this.mc = Minecraft.func_71410_x();
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        Display.setTitle("AnarchyWare v1.0.0 Indev");
    }
    
    @Mod.EventHandler
    public void Init(final FMLInitializationEvent event) {
        AnarchyWare.settingsManager = new SettingsManager();
        AnarchyWare.moduleManager = new ModuleManager();
        AnarchyWare.configManager = new ConfigManager();
        CommandManager.init();
        MinecraftForge.EVENT_BUS.register((Object)new CommandManager());
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onKeyPress(final InputEvent.KeyInputEvent event) {
        final ModuleManager moduleManager = AnarchyWare.moduleManager;
        for (final Module m : ModuleManager.getModules()) {
            if (Keyboard.isKeyDown((int)m.getKey())) {
                m.toggle();
            }
        }
    }
    
    static {
        AnarchyWare.topcornertext = "Admin";
        AnarchyWare.prefix = ".";
        AnarchyWare.chatprefix = TextFormatting.GRAY + "[" + TextFormatting.RED + "AnarchyWare" + TextFormatting.GRAY + "]";
    }
}
