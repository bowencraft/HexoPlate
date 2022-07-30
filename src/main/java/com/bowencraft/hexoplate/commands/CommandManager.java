package com.bowencraft.hexoplate.commands;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.commands.subcommands.*;
import com.bowencraft.hexoplate.files.messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final HexoPlate plugin;


    private ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager(HexoPlate plugin){
        this.plugin = plugin;
        subcommands.add(new calculate());
        subcommands.add(new create(this.plugin));
        subcommands.add(new extend(this.plugin));
        subcommands.add(new playerinfo(this.plugin));
        subcommands.add(new reload(this.plugin));
        subcommands.add(new upgrade(this.plugin));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // if (sender instanceof Player){
//            Player p = (Player) sender;
            boolean cmdexist = false;
            if (sender.hasPermission("hexoplate.commands.help")) {
                if (args.length > 0){
                    for (int i = 0; i < getSubcommands().size(); i++){
                        if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                            getSubcommands().get(i).perform(sender, args);
                            cmdexist = true;
                        }
                    }
                    if (!cmdexist) {
                        sender.sendMessage(messages.get().getString("Command-Separator-Head"));
                        for (int i = 0; i < getSubcommands().size(); i++){
                            if (sender.hasPermission(getSubcommands().get(i).getPermission())) {
                                String msg = messages.get().getString("Help-Command-Info");
                                String context = MessageFormat.format(msg, getSubcommands().get(i).getSyntax(), getSubcommands().get(i).getDescription());
                                sender.sendMessage(context);
                                // sender.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
                            }
                        }
                        sender.sendMessage(messages.get().getString("Command-Separator-Foot"));

                    }
                }else if(args.length == 0){
                    sender.sendMessage(messages.get().getString("Command-Separator-Head"));
                    for (int i = 0; i < getSubcommands().size(); i++){
                        if (sender.hasPermission(getSubcommands().get(i).getPermission())) {
                            String msg = messages.get().getString("Help-Command-Info");
                            String context = MessageFormat.format(msg, getSubcommands().get(i).getSyntax(), getSubcommands().get(i).getDescription());
                            sender.sendMessage(context);
                            // sender.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
                        }
                    }
                    sender.sendMessage(messages.get().getString("Command-Separator-Foot"));
                }

            } else {
                sender.sendMessage(messages.get().getString("No-Permission-Help"));
            }

//        } else {
//            if (args.length > 0){
//                for (int i = 0; i < getSubcommands().size(); i++){
//                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
//                        getSubcommands().get(i).perform((Player)Bukkit.getServer().getConsoleSender() , args);
//                    }
//                }
//            }else if(args.length == 0){
//                Bukkit.getServer().getConsoleSender().sendMessage("--------------------------------");
//                for (int i = 0; i < getSubcommands().size(); i++){
//                    Bukkit.getServer().getConsoleSender().sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
//                }
//                Bukkit.getServer().getConsoleSender().sendMessage("--------------------------------");
//            }
//        }


        return true;
    }

    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }

}
