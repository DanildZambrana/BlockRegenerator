package us.siriusteam.blockregenerator.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class FileManager {
    private File file = null;
    private FileConfiguration fileC = null;
    private JavaPlugin plugin;
    private String nameOfFile;
    private String nameOfDefaultFile;

    public FileManager(String nameOfFile, JavaPlugin plugin) {
        this.plugin = plugin;
        this.nameOfFile = nameOfFile;
    }

    public FileManager(JavaPlugin plugin, String nameOfFile, String nameOfDefaultFile) {
        this.plugin = plugin;
        this.nameOfFile = nameOfFile;
        this.nameOfDefaultFile = nameOfDefaultFile;
    }

    public FileConfiguration getFile() {
        if (fileC == null) {
            reloadFile();
        }
        return fileC;
    }

    public void reloadFile() {
        if (fileC == null) {
            file = new File(plugin.getDataFolder(), nameOfFile);
        }
        fileC = YamlConfiguration.loadConfiguration(file);
        Reader defConfigStream;
        try {
            if (nameOfDefaultFile == null){
                defConfigStream = new InputStreamReader(plugin.getResource(nameOfDefaultFile), "UTF8");
            }else {
                defConfigStream = new InputStreamReader(plugin.getResource("Default.yml"), "UTF8");
            }
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                fileC.setDefaults(defConfig);

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            fileC.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void registerFile(String archive) {
        file = new File(plugin.getDataFolder(), nameOfFile);
        if (!file.exists()) {
            this.getFile().options().copyDefaults(true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c" + archive + " &7is not founded, creating archive..."));
            saveFile();
        }
    }

    public void registerFile() {
        file = new File(plugin.getDataFolder(), nameOfFile);
        if (!file.exists()) {
            this.getFile().options().copyDefaults(true);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c" + nameOfFile + " &7is not founded, creating archive..."));
            saveFile();
        }
    }
}
