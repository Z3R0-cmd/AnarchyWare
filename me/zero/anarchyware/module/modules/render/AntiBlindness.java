// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.render;

import net.minecraft.potion.Potion;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class AntiBlindness extends Module
{
    public AntiBlindness() {
        super("AntiBlindness", Category.RENDER);
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.field_71439_g.func_70644_a(Potion.func_188412_a(9))) {
            this.mc.field_71439_g.func_184596_c(Potion.func_188412_a(9));
        }
        if (this.mc.field_71439_g.func_70644_a(Potion.func_188412_a(15))) {
            this.mc.field_71439_g.func_184596_c(Potion.func_188412_a(15));
        }
    }
}
