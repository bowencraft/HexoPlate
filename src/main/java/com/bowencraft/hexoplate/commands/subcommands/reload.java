package com.bowencraft.hexoplate.commands.subcommands;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.commands.SubCommand;
import com.bowencraft.hexoplate.files.data;
import com.bowencraft.hexoplate.files.messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class reload extends SubCommand {


    private final HexoPlate plugin;

    public reload(HexoPlate plugin) {
        this.plugin = plugin;
    }


    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return messages.get().getString("Reload-Description");
    }

    @Override
    public String getSyntax() {
        return "/hexoplate reload";
    }

    @Override
    public String getPermission() {
        return "hexoplate.commands.admin.reload";
    }

    @Override
    public void perform(CommandSender player, String[] args) {

        String pluginprefix = plugin.getConfig().getString("plugin-prefix");

        if (player.hasPermission(getPermission())) {

            // data.save();
            data.setup();
            data.get().options().copyDefaults(true);
            data.save();

            plugin.getConfig().options().copyDefaults();
            plugin.saveDefaultConfig();

            String lang = plugin.getConfig().getString("message-language");
            messages.setup(lang);
            messages.get().options().copyDefaults(true);
            // messages.save();

            player.sendMessage(pluginprefix + messages.get().getString("Reload-Success"));
        } else {

            String nopermission = messages.get().getString("No-Permission");
            String context = MessageFormat.format(nopermission, getPermission());
            player.sendMessage(pluginprefix + context);
        }
    }
}
