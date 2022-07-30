package com.bowencraft.hexoplate.listeners;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.files.messages;
import com.bowencraft.hexoplate.utils.HexPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

import java.text.MessageFormat;

public class BlockInteraction implements Listener {

    private static HexoPlate plugin;
    private String pluginprefix;

    public BlockInteraction(HexoPlate plugin) {
        this.plugin = plugin;
        pluginprefix = plugin.getConfig().getString("plugin-prefix");

    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        String world = e.getBlock().getWorld().getName().toString();

        if (e.getBlock().getWorld().getName().toString().equals(plugin.getConfig().getString("world"))) {

            int x = (int) e.getBlock().getX();
            int y = (int) e.getBlock().getY();
            int z = (int) e.getBlock().getZ();

            if (HexPlayer.BlockAccess(e.getPlayer(), world, x, y, z)==false) {
                e.getPlayer().sendMessage(pluginprefix + messages.get().getString("Break-No-Permission"));

                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        String world = e.getBlock().getWorld().getName().toString();

        if (e.getBlock().getWorld().getName().toString().equals(plugin.getConfig().getString("world"))) {

            int x = (int) e.getBlock().getX();
            int y = (int) e.getBlock().getY();
            int z = (int) e.getBlock().getZ();

            if (!HexPlayer.BlockAccess(e.getPlayer(), e.getBlock().getWorld().getName().toString(), x, y, z)) {

                e.getPlayer().sendMessage(pluginprefix + messages.get().getString("Place-No-Permission"));
                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {

        if (e.getBlock().getWorld().getName().toString().equals(plugin.getConfig().getString("world"))) {

            int x = (int) e.getBlock().getX();
            int y = (int) e.getBlock().getY();
            int z = (int) e.getBlock().getZ();

            if (!HexPlayer.BlockAccess(e.getBlock().getWorld().getName().toString(), x, y, z)) {
                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent e) {

        if (e.getBlock().getWorld().getName().toString().equals(plugin.getConfig().getString("world"))) {

            int x = (int) e.getBlock().getX();
            int y = (int) e.getBlock().getY();
            int z = (int) e.getBlock().getZ();

            if (!HexPlayer.BlockAccess(e.getBlock().getWorld().getName().toString(), x, y, z)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void OnBlockPhysic(BlockPhysicsEvent e) {

        if (e.getBlock().getWorld().getName().toString().equals(plugin.getConfig().getString("world"))) {

            int x = (int) e.getBlock().getX();
            int y = (int) e.getBlock().getY();
            int z = (int) e.getBlock().getZ();

            if (!HexPlayer.BlockAccess(e.getBlock().getWorld().getName().toString(), x, y, z)) {
                e.setCancelled(true);
            }
        }

    }

}
