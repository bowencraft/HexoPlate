package com.bowencraft.hexoplate.commands.subcommands;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.commands.SubCommand;
import com.bowencraft.hexoplate.files.messages;
import com.bowencraft.hexoplate.utils.Create;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class create extends SubCommand {


    private final HexoPlate plugin;

    public create(HexoPlate plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "admincreate";
    }

    @Override
    public String getDescription() {
        return messages.get().getString("AdminCreate-Description");
    }

    @Override
    public String getSyntax() {
        return "/hexoplate admincreate <player> [type]";
    }


    @Override
    public String getPermission() {
        return "hexoplate.commands.admin.create";
    }

    @Override
    public void perform(CommandSender executer, String[] args) {

        String pluginprefix = plugin.getConfig().getString("plugin-prefix");

        if (executer.hasPermission(getPermission())
            //        &&executer.hasPermission("hexoplate.plate.0.0")
        ) {

        if (args.length == 1) {

            executer.sendMessage(pluginprefix + messages.get().getString("Arguments-Missing"));
            String usage = messages.get().getString("Command-Usage");
            String context2 = MessageFormat.format(usage, getSyntax());
            executer.sendMessage(pluginprefix + context2);

        } else {

                Player player = Bukkit.getPlayer(args[1]);

                if (!Create.getFirstPlate().containsKey(player.getUniqueId())) {

                    if (Bukkit.getServer().getOnlinePlayers().contains(player)) {

                        Create.createloc(player);
                        String type;
                        String schem;
                        String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".type.";


                        if (args.length == 3) {
                            type = args[2];
                            schem = Create.getSchemFromList(path + type + ".schem-files-list");
                        } else {
                            type = "default";
                            schem = Create.getSchemFromList(path + "default.schem-files-list");
                        }

                        String bgpath = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".";

                        if (plugin.getConfig().getBoolean(path + "background.status")) {
                            Create.createbg(player, 0, 0, plugin.getConfig().getString(bgpath + "background.schem-file"));
                        }

                        Create.createplate(player, 0, 0, schem, type);
                        int[] playerloc = Create.getPlayerCentralPlate(player);
                        World world = Bukkit.getWorld(plugin.getConfig().getString("world"));

                        Location loc = new Location(world, playerloc[0] + 0.5, (double) plugin.getConfig().getInt("height"), playerloc[1] + 0.5);

                        player.teleport(loc);
                        executer.sendMessage(pluginprefix + messages.get().getString("Executor-Plate-Create-Success"));

                    } else {
                        executer.sendMessage(pluginprefix + messages.get().getString("Player-Not-Exist"));
                    }


                } else {
                    executer.sendMessage(pluginprefix + messages.get().getString("Executor-Plate-Already-Created"));

                }

            }

        } else {

            String nopermission = messages.get().getString("No-Permission");
            String context = MessageFormat.format(nopermission, getPermission());
            executer.sendMessage(pluginprefix + context);

        }

    }
}
