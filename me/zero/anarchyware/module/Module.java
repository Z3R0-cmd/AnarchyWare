// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module;

import me.zero.anarchyware.AnarchyWare;
import me.zero.anarchyware.managers.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.Minecraft;

public class Module
{
    protected Minecraft mc;
    private String name;
    private String displayName;
    private Category category;
    private boolean toggled;
    private Integer key;
    
    public Module(final String name, final Category category) {
        this.mc = Minecraft.func_71410_x();
        this.name = name;
        this.category = category;
        this.toggled = false;
        this.key = 0;
        this.setup();
    }
    
    public void registerSettings() {
        this.selfSettings();
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void gameTickEvent(final TickEvent event) {
        if (this.isToggled()) {
            if (this.mc.field_71441_e == null || this.mc.field_71439_g == null) {
                return;
            }
            if (this.toggled) {
                this.onUpdate();
            }
        }
    }
    
    public void onUpdate() {
    }
    
    public void selfSettings() {
    }
    
    public void rSetting(final Setting setting) {
        AnarchyWare.settingsManager.rSetting(setting);
    }
    
    public void onToggle() {
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        this.onToggle();
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public Integer getKey() {
        return this.key;
    }
    
    public void setKey(final Integer key) {
        this.key = key;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(final Category category) {
        this.category = category;
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public String getDisplayName() {
        return (this.displayName == null) ? this.name : this.displayName;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    
    public void setup() {
    }
    
    public void setState(final Boolean tog) {
        if (this.isToggled()) {
            if (tog) {
                return;
            }
            this.toggle();
        }
        else {
            if (!tog) {
                return;
            }
            this.toggle();
        }
    }
}
