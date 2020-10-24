// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.movement;

import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class Sprint extends Module
{
    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }
    
    @Override
    public void onUpdate() {
        try {
            if (this.mc.field_71474_y.field_74351_w.func_151470_d() && !this.mc.field_71439_g.field_70123_F && !this.mc.field_71439_g.func_70051_ag()) {
                this.mc.field_71439_g.func_70031_b(true);
            }
        }
        catch (Exception ex) {}
    }
}
