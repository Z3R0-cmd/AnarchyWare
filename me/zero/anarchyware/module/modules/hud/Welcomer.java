// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import java.util.Calendar;
import me.zero.anarchyware.utils.ColorUtils;
import java.awt.Color;
import me.zero.anarchyware.AnarchyWare;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.managers.Setting;
import me.zero.anarchyware.module.Module;

public class Welcomer extends Module
{
    Setting x;
    Setting y;
    
    public Welcomer() {
        super("Welcomer", Category.HUD);
    }
    
    @Override
    public void setup() {
        AnarchyWare.settingsManager.rSetting(this.x = new Setting("X Position", this, 2.0, 0.0, 1000.0, false, "WelcomerX"));
        AnarchyWare.settingsManager.rSetting(this.y = new Setting("Y Position", this, 350.0, 0.0, 1000.0, false, "WelcomerY"));
    }
    
    public int GenRainbow() {
        final float[] hue = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
        final int red = rgb >> 16 & 0xFF;
        final int green = rgb >> 8 & 0xFF;
        final int blue = rgb & 0xFF;
        final int color;
        final int argb = color = ColorUtils.toRGBA(red, green, blue, 255);
        return color;
    }
    
    private String WelcomeMessages() {
        final int timeOfDay = Calendar.getInstance().get(11);
        if (timeOfDay < 12) {
            return "Good Morning, ";
        }
        if (timeOfDay < 16) {
            return "Good Afternoon, ";
        }
        if (timeOfDay < 21) {
            return "Good Evening, ";
        }
        return "Good Night, ";
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent event) {
        if (this.mc.field_71439_g != null && this.mc.field_71441_e != null && event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            this.mc.field_71466_p.func_175063_a(this.WelcomeMessages() + this.mc.func_110432_I().func_111285_a(), (float)this.x.getValInt(), (float)this.y.getValInt(), this.GenRainbow());
        }
    }
}
