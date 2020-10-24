// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.misc;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.GameType;
import net.minecraft.client.Minecraft;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class FakeCreative extends Module
{
    public FakeCreative() {
        super("FakeOP", Category.MISC);
    }
    
    @Override
    public void onUpdate() {
        Minecraft.func_71410_x();
        this.mc.field_71442_b.func_78746_a(GameType.CREATIVE);
    }
    
    @Override
    public void onEnable() {
        this.mc.field_71439_g.func_145747_a((ITextComponent)new TextComponentString("§7§o[Server: Opped " + this.mc.field_71439_g.func_70005_c_() + "]"));
    }
    
    @Override
    public void onDisable() {
        this.mc.field_71442_b.func_78746_a(GameType.SURVIVAL);
    }
}
