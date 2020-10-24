// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.module.modules.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.zero.anarchyware.AnarchyWare;
import net.minecraftforge.client.event.ClientChatEvent;
import me.zero.anarchyware.module.Category;
import me.zero.anarchyware.module.Module;

public class ChatSuffix extends Module
{
    public ChatSuffix() {
        super("ChatSuffix", Category.CHAT);
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatEvent event) {
        final String suffix = " \u300a\uff21\uff2e\uff21\uff32\uff23\uff28\uff39\uff37\uff21\uff32\uff25\u300b";
        if (event.getMessage().endsWith(suffix)) {
            return;
        }
        if (event.getMessage().startsWith("/")) {
            return;
        }
        if (event.getMessage().startsWith(AnarchyWare.prefix)) {
            return;
        }
        event.setMessage(event.getMessage() + suffix);
    }
}
