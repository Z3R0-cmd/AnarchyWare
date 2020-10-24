// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.combat;

import me.zero.anarchyware.managers.Setting;
import me.zero.anarchyware.AnarchyWare;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class TestCrystalAura extends Module
{
    public TestCrystalAura() {
        super("Good CrystalAura (Currently in development)", Category.COMBAT);
        AnarchyWare.settingsManager.rSetting(new Setting("Mode", this, "Normal", "crystalauramode"));
    }
}
