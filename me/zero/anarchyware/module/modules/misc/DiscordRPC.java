// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.misc;

import me.zero.anarchyware.managers.DiscordManager;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class DiscordRPC extends Module
{
    public DiscordRPC() {
        super("DiscordRPC", Category.MISC);
    }
    
    @Override
    public void onEnable() {
        DiscordManager.startup();
    }
    
    @Override
    public void onDisable() {
        DiscordManager.shutdown();
    }
}
