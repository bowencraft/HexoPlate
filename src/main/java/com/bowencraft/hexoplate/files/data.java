package com.bowencraft.hexoplate.files;

import com.bowencraft.hexoplate.utils.Create;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class data {

    private static File file;
    private static FileConfiguration hexodata;

    public static FileConfiguration get() {
        return hexodata;
    }

    // finds or generates the custom config file
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("HexoPlate").getDataFolder(), "/data/data.yml");
        if (!file.exists()){
            try{
                file.createNewFile();
            } catch(IOException e){}
        }
        hexodata = YamlConfiguration.loadConfiguration(file);
        // hexodata.set("data-type.offsettopolar", 6);

        Create.loadcentralHex();
        Create.loadplate();
        Create.loadcreatestatus();
        Create.loadplayerplate();

    }

    public static void save() {
        try{
            Create.savecentralHex();
            Create.saveplate();
            Create.savecreatestatus();
            Create.saveplayerplate();

            hexodata.save(file);

        } catch(IOException e){
            System.out.println("Couldn't save file");
        }
    }

    public static void reload(){
        hexodata = YamlConfiguration.loadConfiguration(file);
    }
}
