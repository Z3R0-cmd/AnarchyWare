// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.render;

import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class Fullbright extends Module
{
    public Fullbright() {
        super("Fullbright", Category.RENDER);
    }
    
    @Override
    public void setup() {
        this.mc.field_71474_y.field_74333_Y = 100.0f;
    }
    
    @Override
    public void onDisable() {
        this.mc.field_71474_y.field_74333_Y = 1.0f;
    }
    
    @Override
    public void onEnable() {
        this.mc.field_71474_y.field_74333_Y = 100.0f;
    }
}
