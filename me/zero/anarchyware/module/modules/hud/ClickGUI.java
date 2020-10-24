// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.hud;

import net.minecraft.client.gui.GuiScreen;
import me.zero.anarchyware.clickgui.ClickGui;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class ClickGUI extends Module
{
    public ClickGUI() {
        super("ClickGUI", Category.HUD);
        if (this.getKey() != null && this.getKey() != 0 && this.getKey() != 0) {
            this.setKey(54);
        }
    }
    
    @Override
    public void onEnable() {
        if (this.mc.field_71439_g != null && this.mc.field_71441_e != null) {
            this.mc.func_147108_a((GuiScreen)new ClickGui());
            this.toggle();
        }
    }
}
