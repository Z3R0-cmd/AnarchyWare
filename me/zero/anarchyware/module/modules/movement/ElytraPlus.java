// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.Vec3d;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class ElytraPlus extends Module
{
    public ElytraPlus() {
        super("Elytra+", Category.MOVEMENT);
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.field_71439_g.func_184613_cA()) {
            final Vec3d vec3d = this.mc.field_71439_g.func_70040_Z();
            final double d0 = 1.5;
            final double d2 = 0.1;
            final EntityPlayerSP field_71439_g = this.mc.field_71439_g;
            field_71439_g.field_70159_w += vec3d.field_72450_a * 0.1 + (vec3d.field_72450_a * 1.0 - this.mc.field_71439_g.field_70159_w) * 0.8;
            final EntityPlayerSP field_71439_g2 = this.mc.field_71439_g;
            field_71439_g2.field_70181_x += vec3d.field_72448_b * 0.1 + (vec3d.field_72448_b * 1.0 - this.mc.field_71439_g.field_70181_x) * 0.8;
            final EntityPlayerSP field_71439_g3 = this.mc.field_71439_g;
            field_71439_g3.field_70179_y += vec3d.field_72449_c * 0.1 + (vec3d.field_72449_c * 1.0 - this.mc.field_71439_g.field_70179_y) * 0.8;
            this.mc.field_71439_g.func_70107_b(this.mc.field_71439_g.field_70165_t, this.mc.field_71439_g.field_70163_u, this.mc.field_71439_g.field_70161_v);
            this.mc.field_71439_g.field_70159_w = this.mc.field_71439_g.field_70159_w;
            this.mc.field_71439_g.field_70181_x = this.mc.field_71439_g.field_70181_x;
            this.mc.field_71439_g.field_70179_y = this.mc.field_71439_g.field_70179_y;
        }
    }
}
