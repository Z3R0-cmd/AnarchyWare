// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.render;

import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class RainRemover extends Module
{
    public RainRemover() {
        super("RainRemover", Category.RENDER);
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.field_71441_e == null) {
            return;
        }
        if (this.mc.field_71441_e.func_72896_J()) {
            this.mc.field_71441_e.func_72894_k(0.0f);
        }
    }
}
