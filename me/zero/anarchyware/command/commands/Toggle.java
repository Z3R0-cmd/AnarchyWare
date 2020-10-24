// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.command.commands;

import java.util.Iterator;
import me.zero.anarchyware.utils.ChatUtil;
import net.minecraft.util.text.TextFormatting;
import me.zero.anarchyware.module.Module;
import me.zero.anarchyware.module.ModuleManager;
import me.zero.anarchyware.AnarchyWare;
import me.zero.anarchyware.command.Command;

public class Toggle extends Command
{
    public Toggle() {
        super("Toggle", new String[] { "t", "toggle" });
    }
    
    @Override
    public void onCommand(final String[] args) {
        if (args.length > 1) {
            try {
                final ModuleManager moduleManager = AnarchyWare.moduleManager;
                for (final Module m : ModuleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(args[1])) {
                        m.toggle();
                        if (m.isToggled()) {
                            ChatUtil.sendMessage(TextFormatting.AQUA + m.getName() + TextFormatting.WHITE + " is now " + TextFormatting.GREEN + "ON");
                        }
                        else {
                            ChatUtil.sendMessage(TextFormatting.AQUA + m.getName() + TextFormatting.WHITE + " is now " + TextFormatting.RED + "OFF");
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
