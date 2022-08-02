package com.bowencraft.hexoplate.commands.subcommands;

import com.bowencraft.hexoplate.commands.SubCommand;
import com.bowencraft.hexoplate.files.messages;
import com.bowencraft.hexoplate.utils.HexAlgs;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class calculate extends SubCommand {

    @Override
    public String getName() {
        return "calculate";
    }

    @Override
    public String getDescription() {
        return messages.get().getString("Calculate-Description");
    }

    @Override
    public String getSyntax() {
        return "/hexoplate calculate <type> <x> <z>";
    }

    @Override
    public String getPermission() {
        return "hexoplate.commands.admin.calculate";
    }

    @Override
    public void perform(CommandSender executer, String[] args) {

        String pluginprefix = "§f[§6Hexo§ePlate§f] §a";

        if (executer.hasPermission(getPermission())) {
            if (args.length < 4) {
                executer.sendMessage(pluginprefix + messages.get().getString("Arguments-Missing"));
                String usage = messages.get().getString("Command-Usage");
                String context2 = MessageFormat.format(usage, getSyntax());
                executer.sendMessage(pluginprefix + context2);
            } else {
                if (args.length == 4) {
                    if (args[1].equalsIgnoreCase("relativeToOffset")) {
                        int[] loc = HexAlgs.relativeToOffset(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
                        executer.sendMessage(pluginprefix + "Relative:" + args[2] + ", " + args[3] + " to Offset Position: " + loc[0] + ", " + loc[1]);
                    } else if (args[1].equalsIgnoreCase("polarToOffset")) {
                        int[] loc = HexAlgs.polarToOffset(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                        executer.sendMessage(pluginprefix + "Input Polar:" + args[2] + ", " + args[3] + " to Offset Position: " + loc[0] + ", " + loc[1]);
                    } else if (args[1].equalsIgnoreCase("offsetToPolar")) {
                        int[] loc = HexAlgs.offsetToPolar(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                        executer.sendMessage(pluginprefix + "Offset:" + args[2] + ", " + args[3] + "Polar Position: " + loc[0] + ", " + loc[1]);
                    } else if (args[1].equalsIgnoreCase("polarToCentral")) {
                        int[] loc = HexAlgs.polarToCentralXY(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                        executer.sendMessage(pluginprefix + "Polar:" + args[2] + ", " + args[3] + "Central Position: " + loc[0] + ", " + loc[1]);
                    }
                } else {
                    executer.sendMessage(pluginprefix + messages.get().getString("Arguments-Missing"));
                    String usage = messages.get().getString("Command-Usage");
                    String context2 = MessageFormat.format(usage, getSyntax());
                    executer.sendMessage(pluginprefix + context2);
                }
            }
        } else {
            String nopermission = messages.get().getString("No-Permission");
            String context = MessageFormat.format(nopermission, getPermission());
            executer.sendMessage(pluginprefix + context);
        }
    }
}
