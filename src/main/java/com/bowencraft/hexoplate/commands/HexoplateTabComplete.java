package com.bowencraft.hexoplate.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HexoplateTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            List<String> arguments = new ArrayList<>();
            if (sender.hasPermission("hexoplate.admin.create"))
                arguments.add("admincreate");

            if (sender.hasPermission("hexoplate.admin.extend"))
                arguments.add("adminextend");

            if (sender.hasPermission("hexoplate.admin.calculate"))
                arguments.add("calculate");


            if (sender.hasPermission("hexoplate.playerinfo.me"))
                arguments.add("playerinfo");

            if (sender.hasPermission("hexoplate.admin.reload"))
                arguments.add("reload");

            if (sender.hasPermission("hexoplate.admin.upgrade"))
                arguments.add("adminupgrade");

            return arguments;
        }
        if (args.length == 2){
            List<String> arguments1 = new ArrayList<>();
            if (args[0].equals("admincreate") || args[0].equals("adminextend") || args[0].equals("adminupgrade")) {
                List<String> playerNames = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);
                for (Player player : players) {
                    playerNames.add(player.getName());
                }
                return playerNames;
            } else if (args[0].equals("calculate")) {
                arguments1.add("relativeToOffset");
                arguments1.add("polarToOffset");
                arguments1.add("offsetToPolar");
                arguments1.add("polarToCentral");

                return arguments1;

            }
        } else if (args.length == 3) {
            List<String> arguments2 = new ArrayList<>();
            switch (args[0]) {
                case "admincreate":
                    arguments2.add("[schem]");
                    return arguments2;

                case "adminextend":
                case "adminupgrade":
                    arguments2.add("<Px>");
                    return arguments2;
                case "calculate":
                    arguments2.add("<x>");
                    return arguments2;

            }
        } else if (args.length == 4) {
            List<String> arguments3 = new ArrayList<>();
            switch (args[0]) {
                case "adminextend":
                case "adminupgrade":
                    arguments3.add("<Pz>");
                    return arguments3;
                case "calculate":
                    arguments3.add("<z>");
                    return arguments3;
            }
        }
        return null;
    }
}
