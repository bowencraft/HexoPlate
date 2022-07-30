package com.bowencraft.hexoplate.commands.subcommands;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.commands.SubCommand;
import com.bowencraft.hexoplate.files.messages;
import com.bowencraft.hexoplate.utils.Create;
import com.bowencraft.hexoplate.utils.HexPlayer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class extend extends SubCommand {


    private final HexoPlate plugin;

    public extend(HexoPlate plugin) {
        this.plugin = plugin;
    }


    @Override
    public String getName() {
        return "adminextend";
    }

    @Override
    public String getDescription() {
        return messages.get().getString("AdminExtend-Description");
    }

    @Override
    public String getSyntax() {
        return "/hexoplate extend <player name> <PolarX> <PolarZ> [type]";
    }

    @Override
    public String getPermission() {
        return "hexoplate.commands.admin.extend";
    }

    @Override
    public void perform(CommandSender executer, String[] args) {
        
        String pluginprefix = plugin.getConfig().getString("plugin-prefix");

        if (executer.hasPermission(getPermission())) {
            if (args.length < 4) {

                executer.sendMessage(pluginprefix + messages.get().getString("Arguments-Missing"));
                String usage = messages.get().getString("Command-Usage");
                String context2 = MessageFormat.format(usage, getSyntax());
                executer.sendMessage(pluginprefix + context2);

            } else {

                Player player = Bukkit.getPlayer(args[1]);


                if (Bukkit.getServer().getOnlinePlayers().contains(player)) {

                    // if (executer.hasPermission("hexoplate.plate." + args[2] + "." + args[3])) {
                    if (!HexPlayer.PlateUnlocked(player, Integer.parseInt(args[2]), Integer.parseInt(args[3]))) {

                        String type;
                        String schem;
                        String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".type.";


                        if (args.length == 5) {
                            type = args[4];
                            schem = Create.getSchemFromList(path + type + ".schem-files-list");
                        } else {
                            type = "default";
                            schem = Create.getSchemFromList(path + "default.schem-files-list");
                        }
                        Create.createplate(player, Integer.parseInt(args[2]), Integer.parseInt(args[3]), schem, type);

                        int[] playerloc = Create.getPlayerCentralPlate(player);
                        World world = Bukkit.getWorld(plugin.getConfig().getString("world"));

                    } else {
                        executer.sendMessage(pluginprefix + messages.get().getString("Player-Already-Unlocked"));
                    }

                } else {

                    executer.sendMessage(pluginprefix + messages.get().getString("Player-Not-Exist"));
                }
            }



            // }

//
//                        Location loc = new Location(world, playerloc[0]+0.5, (double)plugin.getConfig().getInt("height"), playerloc[1]+0.5);
//                        player.teleport(loc);
//                        player.sendMessage(pluginprefix + ChatColor.GREEN + "板块已创建。");
        } else {
            String nopermission = messages.get().getString("No-Permission");
            String context = MessageFormat.format(nopermission, getPermission());
            executer.sendMessage(pluginprefix + context);
        }
    }
}
