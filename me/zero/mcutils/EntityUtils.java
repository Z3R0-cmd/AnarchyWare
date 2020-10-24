// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.mcutils;

import net.minecraft.entity.Entity;

public class EntityUtils
{
    public static boolean isEntityInLiquid(final Entity e) {
        return e.func_180799_ab() || e.func_70090_H();
    }
}
