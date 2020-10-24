// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import me.zero.anarchyware.utils.ColorUtils;
import java.awt.Color;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class Watermark extends Module
{
    public Watermark() {
        super("Watermark", Category.HUD);
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
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent event) {
        if (this.mc.field_71439_g != null && this.mc.field_71441_e != null && event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            this.mc.field_71466_p.func_175063_a("AnarchyWare 1.0.0 Indev", 2.0f, 2.0f, -16777216);
        }
    }
}
