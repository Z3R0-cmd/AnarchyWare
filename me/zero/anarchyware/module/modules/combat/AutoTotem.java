// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.combat;

import net.minecraft.item.Item;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class AutoTotem extends Module
{
    public AutoTotem() {
        super("AutoTotem", Category.COMBAT);
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.field_71439_g == null || this.mc.field_71442_b == null || this.mc.field_71441_e == null) {
            return;
        }
        if (this.mc.field_71439_g.func_184582_a(EntityEquipmentSlot.OFFHAND).func_77973_b() == Items.field_190929_cY) {
            return;
        }
        final int slot = this.getItemSlot();
        if (slot != -1) {
            this.mc.field_71442_b.func_187098_a(this.mc.field_71439_g.field_71069_bz.field_75152_c, slot, 0, ClickType.PICKUP, (EntityPlayer)this.mc.field_71439_g);
            this.mc.field_71442_b.func_187098_a(this.mc.field_71439_g.field_71069_bz.field_75152_c, 45, 0, ClickType.PICKUP, (EntityPlayer)this.mc.field_71439_g);
            this.mc.field_71442_b.func_187098_a(this.mc.field_71439_g.field_71069_bz.field_75152_c, slot, 0, ClickType.PICKUP, (EntityPlayer)this.mc.field_71439_g);
            this.mc.field_71442_b.func_78765_e();
        }
        this.setDisplayName("AutoTotem" + this.getTotemCount());
    }
    
    private int getItemSlot() {
        for (int i = 0; i < 36; ++i) {
            if (this.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() != null && !(this.mc.field_71462_r instanceof GuiChest)) {
                final Item item = this.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
                if (item == Items.field_190929_cY) {
                    if (i < 9) {
                        i += 36;
                    }
                    return i;
                }
            }
        }
        return -1;
    }
    
    public int getTotemCount() {
        int count = 0;
        for (int i = 0; i < 36; ++i) {
            if (this.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() != null && this.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == Items.field_190929_cY) {
                count += this.mc.field_71439_g.field_71071_by.func_70301_a(i).func_190916_E();
            }
        }
        if (this.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190929_cY) {
            count += this.mc.field_71439_g.func_184592_cb().func_190916_E();
        }
        return count;
    }
}
