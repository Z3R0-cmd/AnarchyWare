// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.combat;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import me.zero.anarchyware.managers.Setting;
import me.zero.anarchyware.AnarchyWare;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class AutoClicker extends Module
{
    private long lastClick;
    private long hold;
    private double speed;
    private double holdLength;
    private double min;
    private double max;
    
    public AutoClicker() {
        super("AutoClicker", Category.COMBAT);
        AnarchyWare.settingsManager.rSetting(new Setting("MinCPS", this, 8.0, 1.0, 20.0, false, "Min CPS"));
        AnarchyWare.settingsManager.rSetting(new Setting("MaxCPS", this, 12.0, 1.0, 20.0, false, "Max CPS"));
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent e) {
        if (Mouse.isButtonDown(0)) {
            if (System.currentTimeMillis() - this.lastClick > this.speed * 1000.0) {
                this.lastClick = System.currentTimeMillis();
                if (this.hold < this.lastClick) {
                    this.hold = this.lastClick;
                }
                final int key = this.mc.field_71474_y.field_74312_F.func_151463_i();
                KeyBinding.func_74510_a(key, true);
                KeyBinding.func_74507_a(key);
                this.updateVals();
            }
            else if (System.currentTimeMillis() - this.hold > this.holdLength * 1000.0) {
                KeyBinding.func_74510_a(this.mc.field_71474_y.field_74312_F.func_151463_i(), false);
                this.updateVals();
            }
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.updateVals();
    }
    
    private void updateVals() {
        this.min = AnarchyWare.settingsManager.getSettingByDisplayName("Min CPS").getValDouble();
        this.max = AnarchyWare.settingsManager.getSettingByDisplayName("Max CPS").getValDouble();
        if (this.min >= this.max) {
            this.max = this.min + 1.0;
        }
        this.speed = 1.0 / ThreadLocalRandom.current().nextDouble(this.min - 0.2, this.max);
        this.holdLength = this.speed / ThreadLocalRandom.current().nextDouble(this.min, this.max);
    }
}
