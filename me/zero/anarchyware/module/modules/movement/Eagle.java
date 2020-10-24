// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class Eagle extends Module
{
    public Eagle() {
        super("Eagle", Category.MOVEMENT);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent e) {
        if (this.mc.field_71439_g != null && this.mc.field_71441_e != null && this.isToggled()) {
            final ItemStack i = this.mc.field_71439_g.func_184614_ca();
            final BlockPos bP = new BlockPos(this.mc.field_71439_g.field_70165_t, this.mc.field_71439_g.field_70163_u - 1.0, this.mc.field_71439_g.field_70161_v);
            if (i != null && i.func_77973_b() instanceof ItemBlock) {
                KeyBinding.func_74510_a(this.mc.field_71474_y.field_74311_E.func_151463_i(), false);
                if (this.mc.field_71441_e.func_180495_p(bP).func_177230_c() == Blocks.field_150350_a && !this.mc.field_71439_g.field_71075_bZ.field_75100_b) {
                    KeyBinding.func_74510_a(this.mc.field_71474_y.field_74311_E.func_151463_i(), true);
                }
            }
        }
    }
}
