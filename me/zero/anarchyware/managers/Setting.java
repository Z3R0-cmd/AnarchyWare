// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.managers;

import java.awt.Color;
import java.util.ArrayList;
import me.zero.anarchyware.module.Module;

public class Setting
{
    private String displayName;
    private String id;
    private Module parent;
    private String mode;
    private String sval;
    private ArrayList<String> options;
    private boolean bval;
    private double dval;
    private double min;
    private double max;
    private boolean onlyint;
    private Color color;
    private String customVal;
    
    public Setting(final String displayName, final Module parent, final String sval, final ArrayList<String> options, final String id) {
        this.onlyint = false;
        this.displayName = displayName;
        this.parent = parent;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
        this.id = id;
    }
    
    public Setting(final String displayName, final Module parent, final boolean bval, final String id) {
        this.onlyint = false;
        this.displayName = displayName;
        this.parent = parent;
        this.bval = bval;
        this.mode = "Check";
        this.id = id;
    }
    
    public Setting(final String displayName, final Module parent, final double dval, final double min, final double max, final boolean onlyint, final String id) {
        this.onlyint = false;
        this.displayName = displayName;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
        this.id = id;
    }
    
    public Setting(final String displayName, final Module parent, final Color color, final String id) {
        this.onlyint = false;
        this.displayName = displayName;
        this.parent = parent;
        this.color = color;
        this.mode = "ColorPicker";
        this.id = id;
    }
    
    public Setting(final String displayName, final Module parent, final String customVal, final String id) {
        this.onlyint = false;
        this.displayName = displayName;
        this.parent = parent;
        this.customVal = customVal;
        this.mode = "CustomString";
        this.id = id;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public String getId() {
        return this.id;
    }
    
    public Module getParentMod() {
        return this.parent;
    }
    
    public String getValString() {
        return this.sval;
    }
    
    public void setValString(final String in) {
        this.sval = in;
    }
    
    public ArrayList<String> getOptions() {
        return this.options;
    }
    
    public boolean getValBoolean() {
        return this.bval;
    }
    
    public void setValBoolean(final boolean in) {
        this.bval = in;
    }
    
    public double getValDouble() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return this.dval;
    }
    
    public int getValInt() {
        return (int)this.getValDouble();
    }
    
    public void setValDouble(final double in) {
        this.dval = in;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider");
    }
    
    public boolean isColorPicker() {
        return this.mode.equalsIgnoreCase("ColorPicker");
    }
    
    public boolean isCustomString() {
        return this.mode.equalsIgnoreCase("CustomString");
    }
    
    public boolean onlyInt() {
        return this.onlyint;
    }
    
    public Color getValColor() {
        return this.color;
    }
    
    public void setValColor(final Color newColor) {
        this.color = newColor;
    }
    
    public int getColorRed() {
        return this.color.getRed();
    }
    
    public int getColorGreen() {
        return this.color.getGreen();
    }
    
    public int getColorBlue() {
        return this.color.getBlue();
    }
    
    public int getColorRgb() {
        return this.color.getRGB();
    }
    
    public String getCustomVal() {
        return this.customVal;
    }
    
    public void setCustomVal(final String newString) {
        this.customVal = newString;
    }
}
