// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.misc;

import java.util.Iterator;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import me.zero.anarchyware.utils.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.Entity;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class EntityAlert extends Module
{
    private int donkeyDelay;
    private int llamaDelay;
    private int muleDelay;
    
    public EntityAlert() {
        super("EntityAlert", Category.MISC);
        this.donkeyDelay = 0;
        this.llamaDelay = 0;
        this.muleDelay = 0;
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.field_71441_e != null && this.mc.field_71439_g != null) {
            ++this.donkeyDelay;
            ++this.llamaDelay;
            ++this.muleDelay;
            for (final Entity entity : this.mc.field_71441_e.func_72910_y()) {
                if (entity == null || this.mc.func_147118_V() == null || this.mc.field_71441_e.field_72996_f == null || this.mc.field_71441_e.field_72996_f.isEmpty()) {
                    return;
                }
                if (entity instanceof EntityDonkey && this.donkeyDelay >= 100) {
                    ChatUtil.sendMessage(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "donkey " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.field_70142_S) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.field_70137_T) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.field_70136_U) + ChatFormatting.GRAY + "]");
                    this.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a(SoundEvents.field_187604_bf, 1.0f, 1.0f));
                    this.donkeyDelay = -750;
                }
                else if (entity instanceof EntityLlama && this.llamaDelay >= 100) {
                    ChatUtil.sendMessage(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "llama " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.field_70142_S) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.field_70137_T) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.field_70136_U) + ChatFormatting.GRAY + "]");
                    this.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a(SoundEvents.field_187604_bf, 1.0f, 1.0f));
                    this.llamaDelay = -750;
                }
                else {
                    if (!(entity instanceof EntityMule) || this.muleDelay < 100) {
                        continue;
                    }
                    ChatUtil.sendMessage(ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "EntityAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Found a " + ChatFormatting.AQUA + "mule " + ChatFormatting.WHITE + "at " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + Math.round(entity.field_70142_S) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.field_70137_T) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.field_70136_U) + ChatFormatting.GRAY + "]");
                    this.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a(SoundEvents.field_187604_bf, 1.0f, 1.0f));
                    this.muleDelay = -750;
                }
            }
        }
    }
}
