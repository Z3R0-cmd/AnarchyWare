// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.utils;

import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import java.util.Objects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.client.Minecraft;

public class BlockUtil
{
    private static final Minecraft mc;
    
    public static double vanillaSpeed() {
        double baseSpeed = 0.272;
        if (Minecraft.func_71410_x().field_71439_g.func_70644_a(MobEffects.field_76424_c)) {
            final int amplifier = Objects.requireNonNull(Minecraft.func_71410_x().field_71439_g.func_70660_b(MobEffects.field_76424_c)).func_76458_c();
            baseSpeed *= 1.0 + 0.2 * amplifier;
        }
        return baseSpeed;
    }
    
    public static boolean isMoving() {
        return Minecraft.func_71410_x().field_71439_g.field_191988_bg != 0.0 || Minecraft.func_71410_x().field_71439_g.field_70702_br != 0.0;
    }
    
    public static int getSlot(final Item item) {
        for (int i = 0; i < 9; ++i) {
            final Item item2 = Minecraft.func_71410_x().field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
            if (item.equals(item2)) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getSlot(final Block block) {
        for (int i = 0; i < 9; ++i) {
            final Item item = Minecraft.func_71410_x().field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
            if (item instanceof ItemBlock && ((ItemBlock)item).func_179223_d().equals(block)) {
                return i;
            }
        }
        return -1;
    }
    
    public static void placeBlock(final BlockPos pos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (!BlockUtil.mc.field_71441_e.func_180495_p(pos.func_177972_a(enumFacing)).func_177230_c().equals(Blocks.field_150350_a) && !isIntercepted(pos)) {
                final Vec3d vec = new Vec3d(pos.func_177958_n() + 0.5 + enumFacing.func_82601_c() * 0.5, pos.func_177956_o() + 0.5 + enumFacing.func_96559_d() * 0.5, pos.func_177952_p() + 0.5 + enumFacing.func_82599_e() * 0.5);
                final float[] old = { BlockUtil.mc.field_71439_g.field_70177_z, BlockUtil.mc.field_71439_g.field_70125_A };
                BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v, vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(vec.field_72448_b - (BlockUtil.mc.field_71439_g.field_70163_u + BlockUtil.mc.field_71439_g.func_70047_e()), Math.sqrt((vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t) * (vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t) + (vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v) * (vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v))))), BlockUtil.mc.field_71439_g.field_70122_E));
                BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
                BlockUtil.mc.field_71442_b.func_187099_a(BlockUtil.mc.field_71439_g, BlockUtil.mc.field_71441_e, pos.func_177972_a(enumFacing), enumFacing.func_176734_d(), new Vec3d((Vec3i)pos), EnumHand.MAIN_HAND);
                BlockUtil.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
                BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
                BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(old[0], old[1], BlockUtil.mc.field_71439_g.field_70122_E));
                return;
            }
        }
    }
    
    public static boolean isIntercepted(final BlockPos pos) {
        for (final Entity entity : BlockUtil.mc.field_71441_e.field_72996_f) {
            if (new AxisAlignedBB(pos).func_72326_a(entity.func_174813_aQ())) {
                return true;
            }
        }
        return false;
    }
    
    static {
        mc = Minecraft.func_71410_x();
    }
}
