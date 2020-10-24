// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.utils;

import org.lwjgl.input.Keyboard;
import net.minecraft.world.World;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Wrapper
{
    private static FontRenderer fontRenderer;
    
    public static Minecraft getMinecraft() {
        return Minecraft.func_71410_x();
    }
    
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().field_71439_g;
    }
    
    public static World getWorld() {
        return (World)getMinecraft().field_71441_e;
    }
    
    public static int getKey(final String keyname) {
        return Keyboard.getKeyIndex(keyname.toUpperCase());
    }
}
