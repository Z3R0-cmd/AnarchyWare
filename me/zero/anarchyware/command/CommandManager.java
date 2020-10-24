// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.command;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import me.zero.anarchyware.AnarchyWare;
import net.minecraftforge.client.event.ClientChatEvent;
import me.zero.anarchyware.command.commands.Bind;
import me.zero.anarchyware.command.commands.Toggle;
import java.util.HashSet;

public class CommandManager
{
    public static HashSet<Command> commands;
    
    public static void init() {
        CommandManager.commands.clear();
        CommandManager.commands.add(new Toggle());
        CommandManager.commands.add(new Bind());
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void chatEvent(final ClientChatEvent event) {
        final String[] args = event.getMessage().split(" ");
        if (event.getMessage().startsWith(AnarchyWare.prefix)) {
            event.setCanceled(true);
            for (final Command c : CommandManager.commands) {
                if (args[0].equalsIgnoreCase(AnarchyWare.prefix + c.getCommand())) {
                    c.onCommand(args);
                }
            }
        }
    }
    
    static {
        CommandManager.commands = new HashSet<Command>();
    }
}
