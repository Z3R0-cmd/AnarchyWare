// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.managers;

import java.util.Iterator;
import me.zero.anarchyware.module.Module;
import java.util.ArrayList;

public class SettingsManager
{
    private ArrayList<Setting> settings;
    
    public SettingsManager() {
        this.settings = new ArrayList<Setting>();
    }
    
    public void rSetting(final Setting in) {
        this.settings.add(in);
    }
    
    public ArrayList<Setting> getSettings() {
        return this.settings;
    }
    
    public ArrayList<Setting> getSettingsByMod(final Module mod) {
        final ArrayList<Setting> out = new ArrayList<Setting>();
        for (final Setting s : this.getSettings()) {
            if (s.getParentMod().equals(mod)) {
                out.add(s);
            }
        }
        return out;
    }
    
    public Setting getSettingByDisplayName(final String name) {
        for (final Setting set : this.getSettings()) {
            if (set.getDisplayName().equalsIgnoreCase(name)) {
                return set;
            }
        }
        System.err.println("[AnarchyWare] Error Setting NOT found: '" + name + "'!");
        return null;
    }
    
    public Setting getSettingByID(final String id) {
        for (final Setting s : this.getSettings()) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        System.err.println("[AnarchyWare] Error Setting NOT found: '" + id + "'!");
        return null;
    }
}
