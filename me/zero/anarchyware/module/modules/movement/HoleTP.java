// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import me.zero.mcutils.EntityUtils;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class HoleTP extends Module
{
    public HoleTP() {
        super("HoleTP", Category.MOVEMENT);
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.field_71439_g.field_70122_E && !EntityUtils.isEntityInLiquid((Entity)this.mc.field_71439_g)) {
            final EntityPlayerSP field_71439_g = this.mc.field_71439_g;
            --field_71439_g.field_70181_x;
        }
    }
}
