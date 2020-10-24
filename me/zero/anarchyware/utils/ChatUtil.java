// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.utils;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import me.zero.anarchyware.AnarchyWare;
import net.minecraft.client.Minecraft;

public class ChatUtil
{
    public static void sendMessage(final String message) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(AnarchyWare.chatprefix + " " + message));
    }
}
