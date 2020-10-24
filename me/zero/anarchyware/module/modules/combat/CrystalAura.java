// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.combat;

import net.minecraft.potion.Potion;
import net.minecraft.util.math.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import java.util.function.Predicate;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.init.Blocks;
import me.zero.anarchyware.utils.EntityUtil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.RayTraceResult;
import java.util.Iterator;
import java.util.List;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.EntityLivingBase;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Comparator;
import net.minecraft.entity.item.EntityEnderCrystal;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.managers.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;
import me.zero.anarchyware.module.Module;

public class CrystalAura extends Module
{
    private static Minecraft mc;
    private BlockPos render;
    private Entity renderEnt;
    private long systemTime;
    private static boolean togglePitch;
    private boolean switchCooldown;
    private boolean isAttacking;
    private int oldSlot;
    private int newSlot;
    private static double yaw;
    private static double pitch;
    public boolean isActive;
    private long placeSystemTime;
    private long breakSystemTime;
    private long chatSystemTime;
    private long multiPlaceSystemTime;
    private long antiStuckSystemTime;
    private int placements;
    private static boolean isCurrentlyChangingServerSideAngles;
    Setting place;
    Setting explode;
    Setting range;
    Setting delay;
    
    public CrystalAura() {
        super("CrystalAura", Category.COMBAT);
        this.systemTime = -1L;
        this.switchCooldown = false;
        this.isAttacking = false;
        this.oldSlot = -1;
        this.isActive = false;
    }
    
    @Override
    public void setup() {
        this.rSetting(this.range = new Setting("Range", this, 5.0, 0.0, 10.0, true, "CARange"));
        this.rSetting(this.place = new Setting("Place", this, true, "CAPlace"));
        this.rSetting(this.delay = new Setting("Delay", this, 2.0, 0.0, 20.0, true, "CADelay"));
        this.placeSystemTime = -1L;
        this.breakSystemTime = -1L;
        this.chatSystemTime = -1L;
        this.multiPlaceSystemTime = -1L;
        this.antiStuckSystemTime = -1L;
        this.switchCooldown = false;
        this.placements = 0;
    }
    
    @Override
    public void onUpdate() {
        if (CrystalAura.mc.field_71441_e == null || CrystalAura.mc.field_71439_g == null || CrystalAura.mc.func_110432_I().func_111285_a() == null || CrystalAura.mc.field_71442_b == null) {
            return;
        }
        if (CrystalAura.mc == null || EntityEnderCrystal.class == null) {
            return;
        }
        if (CrystalAura.mc.field_71441_e.field_72996_f == null) {
            return;
        }
        final EntityEnderCrystal crystal = (EntityEnderCrystal)CrystalAura.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity instanceof EntityEnderCrystal).map(entity -> entity).min(Comparator.comparing(c -> CrystalAura.mc.field_71439_g.func_70032_d(c))).orElse(null);
        if (crystal != null && CrystalAura.mc.field_71439_g.func_70032_d((Entity)crystal) <= 5.0f) {
            if (System.nanoTime() / 1000000L - this.breakSystemTime >= 320L) {
                this.lookAtPacket(crystal.field_70165_t, crystal.field_70163_u, crystal.field_70161_v, (EntityPlayer)CrystalAura.mc.field_71439_g);
                CrystalAura.mc.field_71442_b.func_78764_a((EntityPlayer)CrystalAura.mc.field_71439_g, (Entity)crystal);
                CrystalAura.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
                this.breakSystemTime = System.nanoTime() / 1000000L;
            }
            if (System.nanoTime() / 1000000L - this.antiStuckSystemTime <= 700L) {
                return;
            }
        }
        else {
            resetRotation();
        }
        int crystalSlot = (CrystalAura.mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP) ? CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c : -1;
        if (crystalSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (CrystalAura.mc.field_71439_g.field_71071_by.func_70301_a(l).func_77973_b() == Items.field_185158_cP) {
                    crystalSlot = l;
                    break;
                }
            }
        }
        boolean offhand = false;
        if (CrystalAura.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_185158_cP) {
            offhand = true;
        }
        else if (crystalSlot == -1) {
            return;
        }
        Entity ent = null;
        Entity lastTarget = null;
        BlockPos finalPos = null;
        final List<BlockPos> blocks = this.findCrystalBlocks();
        final List<Entity> entities = new ArrayList<Entity>();
        entities.addAll((Collection<? extends Entity>)CrystalAura.mc.field_71441_e.field_73010_i.stream().filter(entityPlayer -> true).collect(Collectors.toList()));
        double damage = 0.5;
        for (final Entity entity2 : entities) {
            if (entity2 != CrystalAura.mc.field_71439_g) {
                if (((EntityLivingBase)entity2).func_110143_aJ() <= 0.0f) {
                    continue;
                }
                if (CrystalAura.mc.field_71439_g.func_70068_e(entity2) > 25.0) {
                    continue;
                }
                for (final BlockPos blockPos : blocks) {
                    if (!canBlockBeSeen(blockPos) && CrystalAura.mc.field_71439_g.func_174818_b(blockPos) > 25.0) {
                        continue;
                    }
                    final double b = entity2.func_174818_b(blockPos);
                    if (b > 56.2) {
                        continue;
                    }
                    final double d = calculateDamage(blockPos.func_177958_n() + 0.5, blockPos.func_177956_o() + 1, blockPos.func_177952_p() + 0.5, entity2);
                    if (d < 7.0 && ((EntityLivingBase)entity2).func_110143_aJ() + ((EntityLivingBase)entity2).func_110139_bj() > 20.0f) {
                        continue;
                    }
                    if (d <= damage) {
                        continue;
                    }
                    final double self = calculateDamage(blockPos.func_177958_n() + 0.5, blockPos.func_177956_o() + 1, blockPos.func_177952_p() + 0.5, (Entity)CrystalAura.mc.field_71439_g);
                    if (CrystalAura.mc.field_71439_g.func_110143_aJ() + CrystalAura.mc.field_71439_g.func_110139_bj() - self <= 7.0) {
                        continue;
                    }
                    if (self > d) {
                        continue;
                    }
                    damage = d;
                    finalPos = blockPos;
                    ent = entity2;
                    lastTarget = entity2;
                }
            }
        }
        if (damage == 0.5) {
            this.render = null;
            this.renderEnt = null;
            resetRotation();
            return;
        }
        this.render = finalPos;
        this.renderEnt = ent;
        if (!offhand && CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c != crystalSlot) {
            CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c = crystalSlot;
            resetRotation();
            this.switchCooldown = true;
            return;
        }
        this.lookAtPacket(finalPos.func_177958_n() + 0.5, finalPos.func_177956_o() - 0.5, finalPos.func_177952_p() + 0.5, (EntityPlayer)CrystalAura.mc.field_71439_g);
        final RayTraceResult result = CrystalAura.mc.field_71441_e.func_72933_a(new Vec3d(CrystalAura.mc.field_71439_g.field_70165_t, CrystalAura.mc.field_71439_g.field_70163_u + CrystalAura.mc.field_71439_g.func_70047_e(), CrystalAura.mc.field_71439_g.field_70161_v), new Vec3d(finalPos.func_177958_n() + 0.5, finalPos.func_177956_o() - 0.5, finalPos.func_177952_p() + 0.5));
        EnumFacing f;
        if (result == null || result.field_178784_b == null) {
            f = EnumFacing.UP;
        }
        else {
            f = result.field_178784_b;
        }
        if (this.switchCooldown) {
            this.switchCooldown = false;
            return;
        }
        if (System.nanoTime() / 1000000L - this.placeSystemTime >= this.delay.getValInt()) {
            CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(finalPos, f, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            ++this.placements;
            this.antiStuckSystemTime = System.nanoTime() / 1000000L;
            this.placeSystemTime = System.nanoTime() / 1000000L;
        }
        if (CrystalAura.togglePitch) {
            final EntityPlayerSP field_71439_g;
            final EntityPlayerSP player = field_71439_g = CrystalAura.mc.field_71439_g;
            field_71439_g.field_70125_A += 4.0E-4f;
            CrystalAura.togglePitch = false;
        }
        else {
            final EntityPlayerSP field_71439_g2;
            final EntityPlayerSP player2 = field_71439_g2 = CrystalAura.mc.field_71439_g;
            field_71439_g2.field_70125_A -= 4.0E-4f;
            CrystalAura.togglePitch = true;
        }
    }
    
    private void lookAtPacket(final double px, final double py, final double pz, final EntityPlayer me) {
        final double[] v = EntityUtil.calculateLookAt(px, py, pz, me);
        setYawAndPitch((float)v[0], (float)v[1]);
    }
    
    private boolean canPlaceCrystal(final BlockPos blockPos) {
        final BlockPos boost = blockPos.func_177982_a(0, 1, 0);
        final BlockPos boost2 = blockPos.func_177982_a(0, 2, 0);
        return (CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150357_h || CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150343_Z) && CrystalAura.mc.field_71441_e.func_180495_p(boost).func_177230_c() == Blocks.field_150350_a && CrystalAura.mc.field_71441_e.func_180495_p(boost2).func_177230_c() == Blocks.field_150350_a && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(boost)).isEmpty() && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(boost2)).isEmpty();
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(CrystalAura.mc.field_71439_g.field_70165_t), Math.floor(CrystalAura.mc.field_71439_g.field_70163_u), Math.floor(CrystalAura.mc.field_71439_g.field_70161_v));
    }
    
    private List<BlockPos> findCrystalBlocks() {
        final NonNullList positions = NonNullList.func_191196_a();
        positions.addAll((Collection)this.getSphere(getPlayerPos(), (float)this.range.getValInt(), this.range.getValInt(), false, true, 0).stream().filter((Predicate<? super Object>)this::canPlaceCrystal).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)positions;
    }
    
    public List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plus_y) {
        final List<BlockPos> circleblocks = new ArrayList<BlockPos>();
        final int cx = loc.func_177958_n();
        final int cy = loc.func_177956_o();
        final int cz = loc.func_177952_p();
        for (int x = cx - (int)r; x <= cx + r; ++x) {
            for (int z = cz - (int)r; z <= cz + r; ++z) {
                for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        final BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }
    
    public static float calculateDamage(final double posX, final double posY, final double posZ, final Entity entity) {
        final float doubleExplosionSize = 12.0f;
        final double distancedsize = entity.func_70011_f(posX, posY, posZ) / 12.0;
        final Vec3d vec3d = new Vec3d(posX, posY, posZ);
        final double blockDensity = entity.field_70170_p.func_72842_a(vec3d, entity.func_174813_aQ());
        final double v = (1.0 - distancedsize) * blockDensity;
        final float damage = (float)(int)((v * v + v) / 2.0 * 7.0 * 12.0 + 1.0);
        double finald = 1.0;
        if (entity instanceof EntityLivingBase) {
            finald = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(damage), new Explosion((World)CrystalAura.mc.field_71441_e, (Entity)null, posX, posY, posZ, 6.0f, false, true));
        }
        return (float)finald;
    }
    
    public static float getBlastReduction(final EntityLivingBase entity, float damage, final Explosion explosion) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer ep = (EntityPlayer)entity;
            final DamageSource ds = DamageSource.func_94539_a(explosion);
            damage = CombatRules.func_189427_a(damage, (float)ep.func_70658_aO(), (float)ep.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
            final int k = EnchantmentHelper.func_77508_a(ep.func_184193_aE(), ds);
            final float f = MathHelper.func_76131_a((float)k, 0.0f, 20.0f);
            damage *= 1.0f - f / 25.0f;
            if (entity.func_70644_a(Potion.func_188412_a(11))) {
                damage -= damage / 4.0f;
            }
            return damage;
        }
        damage = CombatRules.func_189427_a(damage, (float)entity.func_70658_aO(), (float)entity.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
        return damage;
    }
    
    private static float getDamageMultiplied(final float damage) {
        final int diff = CrystalAura.mc.field_71441_e.func_175659_aa().func_151525_a();
        return damage * ((diff == 0) ? 0.0f : ((diff == 2) ? 1.0f : ((diff == 1) ? 0.5f : 1.5f)));
    }
    
    public static float calculateDamage(final EntityEnderCrystal crystal, final Entity entity) {
        return calculateDamage(crystal.field_70165_t, crystal.field_70163_u, crystal.field_70161_v, entity);
    }
    
    public static boolean canBlockBeSeen(final BlockPos blockPos) {
        return CrystalAura.mc.field_71441_e.func_147447_a(new Vec3d(CrystalAura.mc.field_71439_g.field_70165_t, CrystalAura.mc.field_71439_g.field_70163_u + CrystalAura.mc.field_71439_g.func_70047_e(), CrystalAura.mc.field_71439_g.field_70161_v), new Vec3d((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()), false, true, false) == null;
    }
    
    private static void setYawAndPitch(final float yaw1, final float pitch1) {
        CrystalAura.yaw = yaw1;
        CrystalAura.pitch = pitch1;
    }
    
    private static void resetRotation() {
        CrystalAura.yaw = CrystalAura.mc.field_71439_g.field_70177_z;
        CrystalAura.pitch = CrystalAura.mc.field_71439_g.field_70125_A;
    }
    
    static {
        CrystalAura.mc = Minecraft.func_71410_x();
        CrystalAura.togglePitch = false;
    }
}
