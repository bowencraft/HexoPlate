package com.bowencraft.hexoplate.files;

import com.bowencraft.hexoplate.HexoPlate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class messages {


    private static File file;
    private static FileConfiguration messages;

    public static FileConfiguration get() {
        return messages;
    }

    // finds or generates the custom config file
    public static void setup(String lang) {

        file = new File(Bukkit.getServer().getPluginManager().getPlugin("HexoPlate").getDataFolder(), "locale/" + lang + ".yml");
        if (!file.exists()){
            try{
                file.createNewFile();
            } catch(IOException e){}
        }
        messages = YamlConfiguration.loadConfiguration(file);
        // messages.set("help-message", "Use /hexoplate help to check more information.");

    }

//    public static void save() {
//        try{
//            messages.save(file);
//        } catch(IOException e){
//            System.out.println("Couldn't save file");
//        }
//    }

    public static void reload(){
        messages = YamlConfiguration.loadConfiguration(file);
    }

}
