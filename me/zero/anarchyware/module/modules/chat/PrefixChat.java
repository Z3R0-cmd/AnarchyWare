// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiChat;
import me.zero.anarchyware.AnarchyWare;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class PrefixChat extends Module
{
    public PrefixChat() {
        super("PrefixChat", Category.CHAT);
    }
    
    @SubscribeEvent
    public void onKeyPress(final InputEvent.KeyInputEvent event) {
        if (this.mc.field_71462_r == null && Keyboard.isKeyDown(52)) {
            this.mc.func_147108_a((GuiScreen)new GuiChat(AnarchyWare.prefix));
        }
    }
}
