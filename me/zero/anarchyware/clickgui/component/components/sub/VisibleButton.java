// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.clickgui.component.components.sub;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.Gui;
import me.zero.anarchyware.module.Module;
import me.zero.anarchyware.clickgui.component.components.Button;
import me.zero.anarchyware.clickgui.component.Component;

public class VisibleButton extends Component
{
    private boolean hovered;
    private Button parent;
    private int offset;
    private int x;
    private int y;
    private Module mod;
    
    public VisibleButton(final Button button, final Module mod, final int offset) {
        this.parent = button;
        this.mod = mod;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    @Override
    public void renderComponent() {
        Gui.func_73734_a(this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth() * 1, this.parent.parent.getY() + this.offset + 12, this.hovered ? -14540254 : -15658735);
        Gui.func_73734_a(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 12, -15658735);
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Minecraft.func_71410_x().field_71466_p.func_175063_a("Visible: true", (float)((this.parent.parent.getX() + 7) * 2), (float)((this.parent.parent.getY() + this.offset + 2) * 2 + 5), -1);
        GL11.glPopMatrix();
    }
    
    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (!this.isMouseOnButton(mouseX, mouseY) || button != 0 || this.parent.open) {}
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
    }
}
