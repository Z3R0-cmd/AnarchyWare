// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.misc;

import me.zero.anarchyware.utils.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.init.MobEffects;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class WeaknessAlert extends Module
{
    private boolean hasAnnounced;
    
    public WeaknessAlert() {
        super("WeaknessAlert", Category.MISC);
        this.hasAnnounced = false;
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.field_71441_e != null && this.mc.field_71439_g != null) {
            if (this.mc.field_71439_g.func_70644_a(MobEffects.field_76437_t) && !this.hasAnnounced) {
                this.hasAnnounced = true;
                ChatUtil.sendMessage(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "WeaknessDetect" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Hey" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + this.mc.func_110432_I().func_111285_a() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " unlucky move mate" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you now have " + ChatFormatting.RED + "weakness");
            }
            if (!this.mc.field_71439_g.func_70644_a(MobEffects.field_76437_t) && this.hasAnnounced) {
                this.hasAnnounced = false;
                ChatUtil.sendMessage(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "WeaknessDetect" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Phew" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + this.mc.func_110432_I().func_111285_a() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " that was close" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you no longer have " + ChatFormatting.RED + "weakness");
            }
        }
    }
}
