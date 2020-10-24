// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.movement;

import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class NoFall extends Module
{
    public NoFall() {
        super("NoFall", Category.MOVEMENT);
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.field_71439_g.field_70143_R >= 3.0f) {
            this.mc.field_71439_g.field_70143_R = 0.0f;
            this.mc.field_71439_g.field_70122_E = true;
        }
    }
}
