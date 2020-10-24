// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.movement;

import net.minecraft.util.math.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.Vec3i;
import me.zero.anarchyware.module.Category;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import me.zero.anarchyware.module.Module;

public class Anchor extends Module
{
    private final Vec3d[] surroundTargets;
    private Vec3d playerPos;
    private BlockPos basePos;
    private int offsetStep;
    private int playerHotbarSlot;
    private int lastHotbarSlot;
    boolean usingEChests;
    
    public Anchor() {
        super("Anchor", Category.MOVEMENT);
        this.surroundTargets = new Vec3d[] { new Vec3d(0.0, 0.0, 0.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, -1.0) };
        this.offsetStep = 0;
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        this.usingEChests = false;
    }
    
    @Override
    public void onUpdate() {
        if (this.isToggled() && this.mc.field_71439_g != null) {
            if (this.offsetStep == 0) {
                this.basePos = new BlockPos(this.mc.field_71439_g.func_174791_d()).func_177977_b();
                this.playerHotbarSlot = this.mc.field_71439_g.field_71071_by.field_70461_c;
            }
            for (int i = 0; i < (int)Math.floor(3.0); ++i) {
                if (this.offsetStep >= this.surroundTargets.length) {
                    this.endLoop();
                    return;
                }
                final Vec3d offset = this.surroundTargets[this.offsetStep];
                this.placeBlock(new BlockPos((Vec3i)this.basePos.func_177963_a(offset.field_72450_a, offset.field_72448_b, offset.field_72449_c)));
                ++this.offsetStep;
            }
        }
    }
    
    private void centerPlayer(final double x, final double y, final double z) {
        this.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(x, y, z, true));
        this.mc.field_71439_g.func_70107_b(x, y, z);
    }
    
    double getDst(final Vec3d vec) {
        return this.playerPos.func_72438_d(vec);
    }
    
    @Override
    public void onEnable() {
        if (this.mc.field_71439_g == null) {
            return;
        }
        final BlockPos centerPos = this.mc.field_71439_g.func_180425_c();
        this.playerPos = this.mc.field_71439_g.func_174791_d();
        final double y = centerPos.func_177956_o();
        double x = centerPos.func_177958_n();
        double z = centerPos.func_177952_p();
        final Vec3d plusPlus = new Vec3d(x + 0.5, y, z + 0.5);
        final Vec3d plusMinus = new Vec3d(x + 0.5, y, z - 0.5);
        final Vec3d minusMinus = new Vec3d(x - 0.5, y, z - 0.5);
        final Vec3d minusPlus = new Vec3d(x - 0.5, y, z + 0.5);
        if (this.getDst(plusPlus) < this.getDst(plusMinus) && this.getDst(plusPlus) < this.getDst(minusMinus) && this.getDst(plusPlus) < this.getDst(minusPlus)) {
            x = centerPos.func_177958_n() + 0.5;
            z = centerPos.func_177952_p() + 0.5;
            this.centerPlayer(x, y, z);
        }
        if (this.getDst(plusMinus) < this.getDst(plusPlus) && this.getDst(plusMinus) < this.getDst(minusMinus) && this.getDst(plusMinus) < this.getDst(minusPlus)) {
            x = centerPos.func_177958_n() + 0.5;
            z = centerPos.func_177952_p() - 0.5;
            this.centerPlayer(x, y, z);
        }
        if (this.getDst(minusMinus) < this.getDst(plusPlus) && this.getDst(minusMinus) < this.getDst(plusMinus) && this.getDst(minusMinus) < this.getDst(minusPlus)) {
            x = centerPos.func_177958_n() - 0.5;
            z = centerPos.func_177952_p() - 0.5;
            this.centerPlayer(x, y, z);
        }
        if (this.getDst(minusPlus) < this.getDst(plusPlus) && this.getDst(minusPlus) < this.getDst(plusMinus) && this.getDst(minusPlus) < this.getDst(minusMinus)) {
            x = centerPos.func_177958_n() - 0.5;
            z = centerPos.func_177952_p() + 0.5;
            this.centerPlayer(x, y, z);
        }
        this.playerHotbarSlot = this.mc.field_71439_g.field_71071_by.field_70461_c;
        this.lastHotbarSlot = -1;
    }
    
    @Override
    public void onDisable() {
        if (this.mc.field_71439_g != null) {
            if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
                this.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.playerHotbarSlot));
            }
            this.playerHotbarSlot = -1;
            this.lastHotbarSlot = -1;
        }
    }
    
    private void endLoop() {
        this.offsetStep = 0;
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            this.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.playerHotbarSlot));
            this.lastHotbarSlot = this.playerHotbarSlot;
        }
        this.setState(false);
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.mc.field_71441_e.func_180495_p(blockPos).func_185904_a().func_76222_j()) {
            this.placeBlockExecute(blockPos);
        }
    }
    
    private int findObiInHotbar() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.field_71439_g.field_71071_by.func_70301_a(i);
            final Block block;
            if (stack != ItemStack.field_190927_a && stack.func_77973_b() instanceof ItemBlock && (block = ((ItemBlock)stack.func_77973_b()).func_179223_d()) instanceof BlockObsidian) {
                slot = i;
                break;
            }
        }
        return slot;
    }
    
    private int findEChestsInHotbar() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.field_71439_g.field_71071_by.func_70301_a(i);
            final Block block;
            if (stack != ItemStack.field_190927_a && stack.func_77973_b() instanceof ItemBlock && (block = ((ItemBlock)stack.func_77973_b()).func_179223_d()) instanceof BlockEnderChest) {
                slot = i;
                break;
            }
        }
        return slot;
    }
    
    public void placeBlockExecute(final BlockPos pos) {
        final Vec3d eyesPos = new Vec3d(this.mc.field_71439_g.field_70165_t, this.mc.field_71439_g.field_70163_u + this.mc.field_71439_g.func_70047_e(), this.mc.field_71439_g.field_70161_v);
        final EnumFacing[] values;
        final EnumFacing[] var3 = values = EnumFacing.values();
        for (final EnumFacing side : values) {
            final BlockPos neighbor = pos.func_177972_a(side);
            final EnumFacing side2 = side.func_176734_d();
            if (canBeClicked(neighbor)) {
                final Vec3d hitVec = new Vec3d((Vec3i)neighbor).func_178787_e(new Vec3d(0.5, 0.5, 0.5)).func_178787_e(new Vec3d(side2.func_176730_m()).func_186678_a(0.5));
                if (eyesPos.func_72436_e(hitVec) <= 18.0625) {
                    faceVectorPacketInstant(hitVec);
                    final boolean needSneak = false;
                    final Block blockBelow = this.mc.field_71441_e.func_180495_p(neighbor).func_177230_c();
                    if (needSneak) {
                        this.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)this.mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    final int obiSlot = this.findObiInHotbar();
                    this.usingEChests = false;
                    if (obiSlot == -1) {}
                    if (this.lastHotbarSlot != obiSlot) {
                        this.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(obiSlot));
                        this.lastHotbarSlot = obiSlot;
                    }
                    this.mc.field_71442_b.func_187099_a(this.mc.field_71439_g, this.mc.field_71441_e, neighbor, side2, hitVec, EnumHand.MAIN_HAND);
                    this.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                    if (needSneak) {
                        this.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)this.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    return;
                }
            }
        }
    }
    
    private static boolean canBeClicked(final BlockPos pos) {
        return getBlock(pos).func_176209_a(getState(pos), false);
    }
    
    public static Block getBlock(final BlockPos pos) {
        return getState(pos).func_177230_c();
    }
    
    private static IBlockState getState(final BlockPos pos) {
        return Minecraft.func_71410_x().field_71441_e.func_180495_p(pos);
    }
    
    private static void faceVectorPacketInstant(final Vec3d vec) {
        final float[] rotations = getLegitRotations(vec);
        Minecraft.func_71410_x().field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(rotations[0], rotations[1], Minecraft.func_71410_x().field_71439_g.field_70122_E));
    }
    
    private static float[] getLegitRotations(final Vec3d vec) {
        final Vec3d eyesPos = getEyesPos();
        final double diffX = vec.field_72450_a - eyesPos.field_72450_a;
        final double diffY = vec.field_72448_b - eyesPos.field_72448_b;
        final double diffZ = vec.field_72449_c - eyesPos.field_72449_c;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[] { Minecraft.func_71410_x().field_71439_g.field_70177_z + MathHelper.func_76142_g(yaw - Minecraft.func_71410_x().field_71439_g.field_70177_z), Minecraft.func_71410_x().field_71439_g.field_70125_A + MathHelper.func_76142_g(pitch - Minecraft.func_71410_x().field_71439_g.field_70125_A) };
    }
    
    private static Vec3d getEyesPos() {
        return new Vec3d(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u + Minecraft.func_71410_x().field_71439_g.func_70047_e(), Minecraft.func_71410_x().field_71439_g.field_70161_v);
    }
    
    private enum AutoCenter
    {
        OFF, 
        TP;
    }
    
    private enum DebugMsgs
    {
        NONE, 
        IMPORTANT, 
        ALL;
    }
}
