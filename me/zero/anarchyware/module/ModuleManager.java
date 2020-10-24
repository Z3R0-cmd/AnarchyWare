// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module;

import me.zero.anarchyware.module.modules.render.Tracers;
import me.zero.anarchyware.module.modules.movement.Eagle;
import me.zero.anarchyware.module.modules.movement.NoFall;
import me.zero.anarchyware.module.modules.movement.Anchor;
import me.zero.anarchyware.module.modules.misc.FakeCreative;
import me.zero.anarchyware.module.modules.render.AntiBlindness;
import me.zero.anarchyware.module.modules.movement.NoPush;
import me.zero.anarchyware.module.modules.movement.ElytraPlus;
import me.zero.anarchyware.module.modules.misc.AutoArmor;
import me.zero.anarchyware.module.modules.combat.AutoClicker;
import me.zero.anarchyware.module.modules.render.Fullbright;
import me.zero.anarchyware.module.modules.movement.HoleTP;
import me.zero.anarchyware.module.modules.combat.AutoTotem;
import me.zero.anarchyware.module.modules.combat.CrystalAura;
import me.zero.anarchyware.module.modules.hud.Welcomer;
import me.zero.anarchyware.module.modules.hud.ClickGUI;
import me.zero.anarchyware.module.modules.misc.WeaknessAlert;
import me.zero.anarchyware.module.modules.chat.PrefixChat;
import me.zero.anarchyware.module.modules.hud.Watermark;
import me.zero.anarchyware.module.modules.chat.ChatSuffix;
import me.zero.anarchyware.module.modules.misc.EntityAlert;
import me.zero.anarchyware.module.modules.render.RainRemover;
import me.zero.anarchyware.module.modules.movement.Sprint;
import me.zero.anarchyware.module.modules.hud.Arraylist;
import me.zero.anarchyware.module.modules.misc.DiscordRPC;
import java.util.ArrayList;

public class ModuleManager
{
    private static ArrayList<Module> modules;
    
    public ModuleManager() {
        ModuleManager.modules.add(new DiscordRPC());
        ModuleManager.modules.add(new Arraylist());
        ModuleManager.modules.add(new Sprint());
        ModuleManager.modules.add(new RainRemover());
        ModuleManager.modules.add(new EntityAlert());
        ModuleManager.modules.add(new ChatSuffix());
        ModuleManager.modules.add(new Watermark());
        ModuleManager.modules.add(new PrefixChat());
        ModuleManager.modules.add(new WeaknessAlert());
        ModuleManager.modules.add(new ClickGUI());
        ModuleManager.modules.add(new Welcomer());
        ModuleManager.modules.add(new CrystalAura());
        ModuleManager.modules.add(new AutoTotem());
        ModuleManager.modules.add(new HoleTP());
        ModuleManager.modules.add(new Fullbright());
        ModuleManager.modules.add(new AutoClicker());
        ModuleManager.modules.add(new AutoArmor());
        ModuleManager.modules.add(new ElytraPlus());
        ModuleManager.modules.add(new NoPush());
        ModuleManager.modules.add(new AntiBlindness());
        ModuleManager.modules.add(new FakeCreative());
        ModuleManager.modules.add(new Anchor());
        ModuleManager.modules.add(new NoFall());
        ModuleManager.modules.add(new Eagle());
        ModuleManager.modules.add(new Tracers());
    }
    
    public static ArrayList<Module> getModules() {
        return ModuleManager.modules;
    }
    
    public Module getModuleByName(final String name) {
        return ModuleManager.modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    
    static {
        ModuleManager.modules = new ArrayList<Module>();
    }
}
