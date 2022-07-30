package com.bowencraft.hexoplate.commands.subcommands;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.commands.SubCommand;
import com.bowencraft.hexoplate.files.messages;
import com.bowencraft.hexoplate.utils.Create;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class upgrade extends SubCommand {
    private final HexoPlate plugin;

    public upgrade(HexoPlate plugin) {
        this.plugin = plugin;
    }


    @Override
    public String getName() {
        return "adminupgrade";
    }

    @Override
    public String getDescription() {
        return messages.get().getString("AdminUpgrade-Description");
    }

    @Override
    public String getSyntax() {
        return "/hexoplate upgrade <player name> <PolarX> <PolarZ> <py>";
    }


    @Override
    public String getPermission() {
        return "hexoplate.commands.admin.upgrade";
    }

    @Override
    public void perform(CommandSender executer, String[] args) {

        String pluginprefix = plugin.getConfig().getString("plugin-prefix");


        // Player executer = (Player) player0;

        if (executer.hasPermission(getPermission())) {
            if (args.length < 5) {

                executer.sendMessage(pluginprefix + messages.get().getString("Arguments-Missing"));
                String usage = messages.get().getString("Command-Usage");
                String context2 = MessageFormat.format(usage, getSyntax());
                executer.sendMessage(pluginprefix + context2);

            } else {

                Player player = Bukkit.getPlayer(args[1]);

                if (Bukkit.getServer().getOnlinePlayers().contains(player)) {
                    Create.upgradeplate(player, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));

                } else {
                    executer.sendMessage(pluginprefix + messages.get().getString("Player-Not-Exist"));
                }
            }

        } else {
            String nopermission = messages.get().getString("No-Permission");
            String context = MessageFormat.format(nopermission, getPermission());
            executer.sendMessage(pluginprefix + context);
        }

    }
}
