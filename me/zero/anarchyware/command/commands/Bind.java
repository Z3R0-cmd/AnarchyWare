// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.command.commands;

import java.util.Iterator;
import me.zero.anarchyware.utils.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import org.lwjgl.input.Keyboard;
import me.zero.anarchyware.module.Module;
import me.zero.anarchyware.module.ModuleManager;
import me.zero.anarchyware.AnarchyWare;
import me.zero.anarchyware.command.Command;

public class Bind extends Command
{
    public Bind() {
        super("bind", new String[] { "b", "bind" });
    }
    
    @Override
    public void onCommand(final String[] args) {
        if (args.length > 2) {
            try {
                final ModuleManager moduleManager = AnarchyWare.moduleManager;
                for (final Module m : ModuleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(args[1])) {
                        try {
                            m.setKey(Keyboard.getKeyIndex(args[2].toUpperCase()));
                            ChatUtil.sendMessage(ChatFormatting.AQUA + m.getName() + ChatFormatting.WHITE + " is now binded to " + ChatFormatting.RED + args[2].toUpperCase() + ChatFormatting.GRAY + " (" + ChatFormatting.WHITE + Keyboard.getKeyIndex(args[2].toUpperCase() + "") + ChatFormatting.GRAY + ")");
                        }
                        catch (Exception e) {
                            ChatUtil.sendMessage(ChatFormatting.RED + m.getName() + ChatFormatting.WHITE + " Something went wrong :(");
                            e.printStackTrace();
                        }
                    }
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
