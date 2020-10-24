// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.movement;

import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class NoPush extends Module
{
    public NoPush() {
        super("NoPush", Category.MOVEMENT);
    }
    
    @Override
    public void onUpdate() {
        this.mc.field_71439_g.field_70144_Y = 1.0f;
    }
}
