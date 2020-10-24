// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import net.minecraft.entity.EntityLivingBase;
import java.util.List;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class Tracers extends Module
{
    public Tracers() {
        super("Tracers", Category.RENDER);
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent event) {
        if (this.mc == null) {
            return;
        }
        final List<Entity> targets = (List<Entity>)((List)this.mc.field_71441_e.field_72996_f.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList())).stream().filter(entity -> ((Entity)entity).func_70032_d((Entity)this.mc.field_71439_g) < 4.0f && entity != this.mc.field_71439_g).collect(Collectors.toList());
        for (final Entity e : targets) {
            if (e instanceof EntityPlayer) {}
        }
    }
}
