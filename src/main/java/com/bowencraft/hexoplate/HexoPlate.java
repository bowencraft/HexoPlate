//    __ __             ___  __     __
//   / // /____ _____  / _ \/ /__ _/ /____
//  / _  / -_) \ / _ \/ ___/ / _ `/ __/ -_)
// /_//_/\__/_\_\\___/_/  /_/\_,_/\__/\__/

package com.bowencraft.hexoplate;

import com.bowencraft.hexoplate.commands.CommandManager;
import com.bowencraft.hexoplate.commands.HexoplateTabComplete;
import com.bowencraft.hexoplate.files.messages;
import com.bowencraft.hexoplate.files.data;
import com.bowencraft.hexoplate.listeners.BlockInteraction;
import com.bowencraft.hexoplate.utils.Create;
import com.bowencraft.hexoplate.utils.HexPlayer;
import com.bowencraft.hexoplate.utils.HexAlgs;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public final class HexoPlate extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        //load config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        String lang = this.getConfig().getString("message-language");
        messages.setup(lang);
        // CustomConfig.get().addDefault("Data", "value");
        // setup default value in the file
        messages.get().options().copyDefaults(true);
        // messages.save();

        data.setup();
        data.get().options().copyDefaults(true);
        data.save();

        new Create(this);
        new HexAlgs(this);
        new HexPlayer(this);

        getServer().getPluginManager().registerEvents(new BlockInteraction(this), this);

        // getCommand("hexoplate").setExecutor(new HexoplateCommand(this));
        getCommand("hexoplate").setExecutor(new CommandManager(this));
        getCommand("hexo").setExecutor(new CommandManager(this));
        getCommand("hex").setExecutor(new CommandManager(this));

        getCommand("hexoplate").setTabCompleter(new HexoplateTabComplete());

        System.out.println("§aHexoPlate loaded successfully, version:" + getServer().getVersion());
        System.out.println("   __ __             ___  __     __     ");
        System.out.println("  / // /____ _____  / _ \\/ /__ _/ /____  ");
        System.out.println(" / _  / -_) \\ / _ \\/ ___/ / _ `/ __/ -_) ");
        System.out.println("/_//_/\\__/_\\_\\\\___/_/  /_/\\_,_/\\__/\\__/ ");
        System.out.println("§6Hexoplate §71.0.0-BETA  §7Author: Bo_Wen");
        System.out.println("§7Visit §f§nhexoplate.bowen-craft.com §r§7for more information.");


    }

    @Override
    public void onDisable() {
        saveDefaultConfig();

        // messages.save();
        data.save();
        System.out.println("§aPlayers data saved successfully.");

    }


}