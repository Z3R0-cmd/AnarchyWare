// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.zero.anarchyware.module.ModuleManager;
import me.zero.anarchyware.AnarchyWare;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import me.zero.anarchyware.utils.ColorUtils;
import java.awt.Color;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class Arraylist extends Module
{
    public Arraylist() {
        super("Arraylist", Category.HUD);
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
            float currY = (float)(this.mc.field_71466_p.field_78288_b + 2);
            final ModuleManager moduleManager = AnarchyWare.moduleManager;
            for (final Module m : ModuleManager.getModules()) {
                if (m.isToggled()) {
                    this.mc.field_71466_p.func_175063_a(m.getName(), 2.0f, currY, ColorUtils.getRainbow());
                    currY += this.mc.field_71466_p.field_78288_b;
                }
            }
        }
    }
}
