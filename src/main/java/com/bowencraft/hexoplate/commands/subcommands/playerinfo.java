package com.bowencraft.hexoplate.commands.subcommands;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.commands.SubCommand;
import com.bowencraft.hexoplate.files.messages;
import com.bowencraft.hexoplate.utils.Create;
import com.bowencraft.hexoplate.utils.HexPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class playerinfo extends SubCommand {

    private final HexoPlate plugin;

    public playerinfo(HexoPlate plugin) {
        this.plugin = plugin;
    }


    @Override
    public String getName() {
        return "playerinfo";
    }

    @Override
    public String getDescription() {
        return messages.get().getString("Playerinfo-Description");
    }

    @Override
    public String getSyntax() {
        return "/hexoplate playerinfo [player]";
    }

    @Override
    public String getPermission() {
        return "hexoplate.commands.playerinfo.me";
    }

    @Override
    public void perform(CommandSender executer, String[] args) {

        String pluginprefix = plugin.getConfig().getString("plugin-prefix");

        Player player;
        if (args.length == 2) {
            player = Bukkit.getPlayer(args[1]);

            if (executer.hasPermission("hexoplate.commands.playerinfo.*")) {

                if (Bukkit.getServer().getOnlinePlayers().contains(player)) {

                    int locx = (int)Math.floor(player.getLocation().getX());
                    int locz = (int)Math.floor(player.getLocation().getZ());

                    Player owner = HexPlayer.GetOwner(player);
//
//            int ownerlocx = locx - Create.getPlateX(owner.getUniqueId()); // owner相对位置
//            int ownerlocz = locz - Create.getPlateZ(owner.getUniqueId());
//
//            int[] offset = hexoalgorithm.relativeToOffset(ownerlocx, ownerlocz);
//            boolean status = PosCheck.positioncheck(owner, offset);
//
//            int[] polar = hexoalgorithm.offsetToPolar(offset[0], offset[1]);


                    executer.sendMessage(pluginprefix + messages.get().getString("Info-Separator"));
                    executer.sendMessage(pluginprefix + messages.get().getString("Info-Player")+" "+ player.getDisplayName());
                    if (Create.getFirstPlate().containsKey(player.getUniqueId())) {
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-LockStatus") + " "+ messages.get().getString("Info-Unlock"));
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-CentralCoord") + " " + Create.getPlateX(player.getUniqueId()) + " " + Create.getPlateZ(player.getUniqueId()));
                    } else {
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-LockStatus") + " "+ messages.get().getString("Info-Lock"));
                    }
                    executer.sendMessage(pluginprefix + messages.get().getString("Info-CurrentCoord") + " " + locx + " " + locz);
                    if (owner !=null) {
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-RegionOwner") + " " + owner.getDisplayName());
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-isInhex") + " " + (HexPlayer.HexLocStatus(player, owner)?messages.get().getString("Info-true"):messages.get().getString("Info-false")));
                        if (HexPlayer.HexLocStatus(player, owner)) {
                            executer.sendMessage(pluginprefix + messages.get().getString("Info-OffsetCoord") + " " + HexPlayer.OffsetHexX(player, owner) + " " + HexPlayer.OffsetHexZ(player, owner));
                            executer.sendMessage(pluginprefix + messages.get().getString("Info-PolarCoord") + " " + HexPlayer.PolarHexX(player, owner) + " " + HexPlayer.PolarHexZ(player, owner));
                        }
                    } else {
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-RegionOwner") + messages.get().getString("Info-null"));
                    }
                    executer.sendMessage(pluginprefix + messages.get().getString("Info-Separator"));

                } else {
                    executer.sendMessage(pluginprefix + messages.get().getString("Player-Not-Exist"));
                }
            } else {
                String nopermission = messages.get().getString("No-Permission");
                String context = MessageFormat.format(nopermission, getPermission());
                executer.sendMessage(pluginprefix + context);
            }
        } else {
            if (executer instanceof Player) {
                player = (Player) executer;

                if (executer.hasPermission(getPermission())) {

                    if (Bukkit.getServer().getOnlinePlayers().contains(player)) {

                        int locx = (int)Math.floor(player.getLocation().getX());
                        int locz = (int)Math.floor(player.getLocation().getZ());

                        Player owner = HexPlayer.GetOwner(player);
//
//            int ownerlocx = locx - Create.getPlateX(owner.getUniqueId()); // owner相对位置
//            int ownerlocz = locz - Create.getPlateZ(owner.getUniqueId());
//
//            int[] offset = hexoalgorithm.relativeToOffset(ownerlocx, ownerlocz);
//            boolean status = PosCheck.positioncheck(owner, offset);
//
//            int[] polar = hexoalgorithm.offsetToPolar(offset[0], offset[1]);


                        executer.sendMessage(pluginprefix + messages.get().getString("Info-Separator"));
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-Player")+" "+ player.getDisplayName());
                        if (Create.getFirstPlate().containsKey(player.getUniqueId())) {
                            executer.sendMessage(pluginprefix + messages.get().getString("Info-LockStatus") + " "+ messages.get().getString("Info-Unlock"));
                            executer.sendMessage(pluginprefix + messages.get().getString("Info-CentralCoord") + " " + Create.getPlateX(player.getUniqueId()) + " " + Create.getPlateZ(player.getUniqueId()));
                        } else {
                            executer.sendMessage(pluginprefix + messages.get().getString("Info-LockStatus") + " "+ messages.get().getString("Info-Lock"));
                        }
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-CurrentCoord") + " " + locx + " " + locz);
                        if (owner !=null) {
                            executer.sendMessage(pluginprefix + messages.get().getString("Info-RegionOwner") + " " + owner.getDisplayName());
                            executer.sendMessage(pluginprefix + messages.get().getString("Info-isInhex") + " " + (HexPlayer.HexLocStatus(player, owner)?messages.get().getString("Info-true"):messages.get().getString("Info-false")));
                            if (HexPlayer.HexLocStatus(player, owner)) {
                                executer.sendMessage(pluginprefix + messages.get().getString("Info-OffsetCoord") + " " + HexPlayer.OffsetHexX(player, owner) + " " + HexPlayer.OffsetHexZ(player, owner));
                                executer.sendMessage(pluginprefix + messages.get().getString("Info-PolarCoord") + " " + HexPlayer.PolarHexX(player, owner) + " " + HexPlayer.PolarHexZ(player, owner));
                            }
                        } else {
                            executer.sendMessage(pluginprefix + messages.get().getString("Info-RegionOwner") + messages.get().getString("Info-null"));
                        }
                        executer.sendMessage(pluginprefix + messages.get().getString("Info-Separator"));

                    } else {
                        executer.sendMessage(pluginprefix + messages.get().getString("Player-Not-Exist"));
                    }
                } else {
                    String nopermission = messages.get().getString("No-Permission");
                    String context = MessageFormat.format(nopermission, getPermission());
                    executer.sendMessage(pluginprefix + context);
                }
            } else {

                executer.sendMessage(pluginprefix + messages.get().getString("Arguments-Missing"));
                String usage = messages.get().getString("Command-Usage");
                String context2 = MessageFormat.format(usage, getSyntax());
                executer.sendMessage(pluginprefix + context2);

            }
        }

    }


}
