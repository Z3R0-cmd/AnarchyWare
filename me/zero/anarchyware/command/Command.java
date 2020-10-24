// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.command;

public class Command
{
    private String command;
    private String[] usage;
    
    public Command(final String name, final String[] usage) {
        this.command = name;
        this.usage = usage;
    }
    
    public void onCommand(final String[] args) {
    }
    
    public String getCommand() {
        return this.command;
    }
}
