// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.anarchyware.managers;

import java.awt.Color;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import me.zero.anarchyware.clickgui.component.Frame;
import me.zero.anarchyware.clickgui.ClickGui;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import me.zero.anarchyware.module.Module;
import me.zero.anarchyware.module.ModuleManager;
import me.zero.anarchyware.AnarchyWare;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class ConfigManager
{
    public File ClientBaseName;
    public File Settings;
    
    public ConfigManager() {
        this.ClientBaseName = new File("AnarchyWare");
        if (!this.ClientBaseName.exists()) {
            this.ClientBaseName.mkdirs();
        }
        this.Settings = new File("AnarchyWare" + File.separator + "Settings");
        if (!this.Settings.exists()) {
            this.Settings.mkdirs();
        }
        this.loadMods();
        this.loadSettingsList();
        this.loadBinds();
        this.loadFramePos();
    }
    
    public void SaveAll() {
        this.saveBinds();
        this.saveMods();
        this.saveSettingsList();
        this.saveGUI();
    }
    
    public void saveBinds() {
        try {
            final File file = new File(this.ClientBaseName.getAbsolutePath(), "Binds.txt");
            final BufferedWriter out = new BufferedWriter(new FileWriter(file));
            final ModuleManager moduleManager = AnarchyWare.moduleManager;
            for (final Module module : ModuleManager.getModules()) {
                out.write(module.getName() + ":" + Keyboard.getKeyName((int)module.getKey()));
                out.write("\r\n");
            }
            out.close();
        }
        catch (Exception ex) {}
    }
    
    public void saveGUI() {
        try {
            final File file = new File(this.ClientBaseName.getAbsolutePath(), "FramePositions.txt");
            final BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (final Frame frame : ClickGui.frames) {
                out.write(frame.category + ":x:" + frame.getX());
                out.write("\r\n");
                out.write(frame.category + ":y:" + frame.getY());
                out.write("\r\n");
            }
            out.close();
        }
        catch (Exception ex) {}
    }
    
    public void loadFramePos() {
        try {
            final File file = new File(this.ClientBaseName.getAbsolutePath(), "FramePositions.txt");
            final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                final String curLine = line.trim();
                final String name = curLine.split(":")[0];
                final String xory = curLine.split(":")[1];
                final String pos = curLine.split(":")[2];
                for (final Frame frame : ClickGui.frames) {
                    if (frame.category.equals(name)) {
                        if (xory.contains("x")) {
                            frame.setX(Integer.parseInt(xory));
                        }
                        if (!xory.contains("y")) {
                            continue;
                        }
                        frame.setY(Integer.parseInt(xory));
                    }
                }
            }
            br.close();
        }
        catch (Exception var11) {
            var11.printStackTrace();
            this.saveBinds();
        }
    }
    
    public void loadBinds() {
        try {
            final File file = new File(this.ClientBaseName.getAbsolutePath(), "Binds.txt");
            final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                final String curLine = line.trim();
                final String name = curLine.split(":")[0];
                final String bind = curLine.split(":")[1];
                final ModuleManager moduleManager = AnarchyWare.moduleManager;
                for (final Module m : ModuleManager.getModules()) {
                    if (m != null && m.getName().equalsIgnoreCase(name)) {
                        m.setKey(Keyboard.getKeyIndex(bind));
                    }
                }
            }
            br.close();
        }
        catch (Exception var11) {
            var11.printStackTrace();
            this.saveBinds();
        }
    }
    
    public void saveMods() {
        try {
            final File file = new File(this.ClientBaseName.getAbsolutePath(), "EnabledModules.txt");
            final BufferedWriter out = new BufferedWriter(new FileWriter(file));
            final ModuleManager moduleManager = AnarchyWare.moduleManager;
            for (final Module module : ModuleManager.getModules()) {
                if (module.isToggled()) {
                    out.write(module.getName());
                    out.write("\r\n");
                }
            }
            out.close();
        }
        catch (Exception ex) {}
    }
    
    public void writeCrash(final String alah) {
        try {
            final SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
            final Date date = new Date();
            final File file = new File(this.ClientBaseName.getAbsolutePath(), "crashlog-".concat(format.format(date)).concat(".bruh"));
            final BufferedWriter outWrite = new BufferedWriter(new FileWriter(file));
            outWrite.write(alah);
            outWrite.close();
        }
        catch (Exception ex) {}
    }
    
    public void loadMods() {
        try {
            final File file = new File(this.ClientBaseName.getAbsolutePath(), "EnabledModules.txt");
            final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                final ModuleManager moduleManager = AnarchyWare.moduleManager;
                for (final Module m : ModuleManager.getModules()) {
                    if (m.getName().equals(line)) {
                        m.toggle();
                    }
                }
            }
            br.close();
        }
        catch (Exception var7) {
            var7.printStackTrace();
            this.saveMods();
        }
    }
    
    public void saveSettingsList() {
        try {
            final File file = new File(this.Settings.getAbsolutePath(), "Number.txt");
            final BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (final Setting i : AnarchyWare.settingsManager.getSettings()) {
                if (i.isSlider()) {
                    out.write(i.getId() + ":" + i.getValDouble() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }
            out.close();
        }
        catch (Exception ex) {}
        try {
            final File file = new File(this.Settings.getAbsolutePath(), "Boolean.txt");
            final BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (final Setting i : AnarchyWare.settingsManager.getSettings()) {
                if (i.isCheck()) {
                    out.write(i.getId() + ":" + i.getValBoolean() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }
            out.close();
        }
        catch (Exception ex2) {}
        try {
            final File file = new File(this.Settings.getAbsolutePath(), "String.txt");
            final BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (final Setting i : AnarchyWare.settingsManager.getSettings()) {
                if (i.isCombo()) {
                    out.write(i.getId() + ":" + i.getValString() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }
            out.close();
        }
        catch (Exception ex3) {}
        try {
            final File file = new File(this.Settings.getAbsolutePath(), "Color.txt");
            final BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (final Setting i : AnarchyWare.settingsManager.getSettings()) {
                if (i.isColorPicker()) {
                    out.write(i.getId() + ":" + i.getValColor().getRGB() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }
            out.close();
        }
        catch (Exception ex4) {}
    }
    
    public void loadSettingsList() {
        try {
            final File file = new File(this.Settings.getAbsolutePath(), "Number.txt");
            final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                final String curLine = line.trim();
                final String name = curLine.split(":")[0];
                final String isOn = curLine.split(":")[1];
                final String m = curLine.split(":")[2];
                final ModuleManager moduleManager = AnarchyWare.moduleManager;
                for (final Module mm : ModuleManager.getModules()) {
                    if (mm != null && mm.getName().equalsIgnoreCase(m)) {
                        final Setting mod = AnarchyWare.settingsManager.getSettingByID(name);
                        mod.setValDouble(Double.parseDouble(isOn));
                    }
                }
            }
            br.close();
        }
        catch (Exception var13) {
            var13.printStackTrace();
        }
        try {
            final File file = new File(this.Settings.getAbsolutePath(), "Color.txt");
            final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                final String curLine = line.trim();
                final String name = curLine.split(":")[0];
                final int color = Integer.parseInt(curLine.split(":")[1]);
                final String m = curLine.split(":")[2];
                final ModuleManager moduleManager2 = AnarchyWare.moduleManager;
                for (final Module mm : ModuleManager.getModules()) {
                    if (mm != null && mm.getName().equalsIgnoreCase(m)) {
                        final Setting mod = AnarchyWare.settingsManager.getSettingByID(name);
                        mod.setValColor(new Color(color));
                    }
                }
            }
            br.close();
        }
        catch (Exception var13) {
            var13.printStackTrace();
        }
        try {
            final File file = new File(this.Settings.getAbsolutePath(), "Boolean.txt");
            final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                final String curLine = line.trim();
                final String name = curLine.split(":")[0];
                final String isOn = curLine.split(":")[1];
                final String m = curLine.split(":")[2];
                final ModuleManager moduleManager3 = AnarchyWare.moduleManager;
                for (final Module mm : ModuleManager.getModules()) {
                    if (mm != null && mm.getName().equalsIgnoreCase(m)) {
                        final Setting mod = AnarchyWare.settingsManager.getSettingByID(name);
                        mod.setValBoolean(Boolean.parseBoolean(isOn));
                    }
                }
            }
            br.close();
        }
        catch (Exception var14) {
            var14.printStackTrace();
        }
        try {
            final File file = new File(this.Settings.getAbsolutePath(), "String.txt");
            final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                final String curLine = line.trim();
                final String name = curLine.split(":")[0];
                final String isOn = curLine.split(":")[1];
                final String m = curLine.split(":")[2];
                final ModuleManager moduleManager4 = AnarchyWare.moduleManager;
                for (final Module mm : ModuleManager.getModules()) {
                    if (mm != null && mm.getName().equalsIgnoreCase(m)) {
                        final Setting mod = AnarchyWare.settingsManager.getSettingByID(name);
                        mod.setValString(isOn);
                    }
                }
            }
            br.close();
        }
        catch (Exception var15) {
            var15.printStackTrace();
        }
    }
}
