// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.clickgui.component;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.Gui;
import me.zero.anarchyware.clickgui.ClickGui;
import net.minecraft.client.gui.FontRenderer;
import java.util.Iterator;
import me.zero.anarchyware.clickgui.component.components.Button;
import me.zero.anarchyware.module.Module;
import me.zero.anarchyware.module.ModuleManager;
import me.zero.anarchyware.AnarchyWare;
import me.zero.anarchyware.module.Category;
import java.util.ArrayList;

public class Frame
{
    public ArrayList<Component> components;
    public Category category;
    private boolean open;
    private int width;
    private int y;
    private int x;
    private int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;
    
    public Frame(final Category cat) {
        this.components = new ArrayList<Component>();
        this.category = cat;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        int tY = this.barHeight;
        final ModuleManager moduleManager = AnarchyWare.moduleManager;
        for (final Module mod : ModuleManager.getModules()) {
            if (mod.getCategory().equals(this.category)) {
                final Button modButton = new Button(mod, this, tY);
                this.components.add(modButton);
                tY += 12;
            }
        }
    }
    
    public ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public void setX(final int newX) {
        this.x = newX;
    }
    
    public void setY(final int newY) {
        this.y = newY;
    }
    
    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public void renderFrame(final FontRenderer fontRenderer) {
        Gui.func_73734_a(this.x, this.y, this.x + this.width, this.y + this.barHeight, ClickGui.color);
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        fontRenderer.func_175063_a(this.category.name(), (float)((this.x + 2) * 2 + 5), (this.y + 2.5f) * 2.0f + 5.0f, -1);
        fontRenderer.func_175063_a(this.open ? "-" : "+", (float)((this.x + this.width - 10) * 2 + 5), (this.y + 2.5f) * 2.0f + 5.0f, -1);
        GL11.glPopMatrix();
        if (this.open && !this.components.isEmpty()) {
            for (final Component component : this.components) {
                component.renderComponent();
            }
        }
    }
    
    public void refresh() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOff(off);
            off += comp.getHeight();
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }
    
    public boolean isWithinHeader(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }
}
