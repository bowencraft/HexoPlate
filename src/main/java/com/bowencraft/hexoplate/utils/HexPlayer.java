package com.bowencraft.hexoplate.utils;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.files.data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class HexPlayer {

    private static HexoPlate plugin;

    // private final String pluginprefix = plugin.getConfig().getString("plugin-prefix");

    public HexPlayer(HexoPlate plugin) {
        this.plugin = plugin;
    }

    public static Player GetOwner(Player player) {
        int locx = (int)Math.floor(player.getLocation().getX());
        int locz = (int)Math.floor(player.getLocation().getZ());

        Player owner = Bukkit.getPlayer(Create.getcentralHex(((locx+plugin.getConfig().getInt("players-interval")/2)/plugin.getConfig().getInt("players-interval")) + "," + ((locz+plugin.getConfig().getInt("players-interval")/2)/plugin.getConfig().getInt("players-interval"))));
        return owner;
    }

    public static Player GetOwner(int locx, int locz) {
        Player owner = Bukkit.getPlayer(Create.getcentralHex(((locx+plugin.getConfig().getInt("players-interval")/2)/plugin.getConfig().getInt("players-interval")) + "," + ((locz+plugin.getConfig().getInt("players-interval")/2)/plugin.getConfig().getInt("players-interval"))));
        return owner;
    }


    public static int RelativeLocX(Player player, Player owner) {

        int ownerlocx = (int) Math.floor(player.getLocation().getX()) - Create.getPlateX(owner.getUniqueId()); // owner相对位置
        return ownerlocx;
    }


    public static int RelativeLocX(int x, int z) {
        Player owner = GetOwner(x, z);
        int ownerlocx = x - Create.getPlateX(owner.getUniqueId()); // owner相对位置
        return ownerlocx;
    }

    public static int RelativeLocZ(Player player, Player owner) {

        int ownerlocZ = (int) Math.floor(player.getLocation().getZ()) - Create.getPlateZ(owner.getUniqueId()); // owner相对位置
        return ownerlocZ;

    }


    public static int RelativeLocZ(int x, int z) {
        Player owner = GetOwner(x, z);
        int ownerlocz = z - Create.getPlateZ(owner.getUniqueId()); // owner相对位置
        return ownerlocz;
    }

    public static int OffsetHexX(Player player, Player owner) {

        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        return offset[0];

    }

    public static int OffsetHexX(Player player) {
        Player owner = GetOwner(player);
        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        return offset[0];

    }

    public static int OffsetHexZ(Player player, Player owner) {

        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        return offset[1];

    }


    public static int OffsetHexZ(Player player) {
        Player owner = GetOwner(player);

        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        return offset[1];

    }

    public static boolean HexLocStatus(Player player, Player owner) {

        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        if (offset[2] == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean HexLocStatus(Player player) {
        Player owner = GetOwner(player);
        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        if (offset[2] == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean HexLocStatus(int x, int z) {
        Player owner = GetOwner(x,z);
        int ownerLocx = RelativeLocX(x,z);
        int ownerLocz = RelativeLocZ(x,z);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        if (offset[2] == 1) {
            return true;
        } else {
            return false;
        }

    }


    public static int PolarHexX(Player player, Player owner) {

        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        int[] polar = HexAlgs.offsetToPolar(offset[0], offset[1]);
        return polar[0];

    }

    public static int PolarHexX(Player player) {

        Player owner = GetOwner(player);
        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        int[] polar = HexAlgs.offsetToPolar(offset[0], offset[1]);
        return polar[0];

    }


    public static int PolarHexX(int x, int z) {

        Player owner = GetOwner(x,z);
        int ownerLocx = RelativeLocX(x,z);
        int ownerLocz = RelativeLocZ(x,z);
        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        // owner.sendMessage(pluginprefix + x + ", " + z + " | " +ownerLocx + ", " + ownerLocz + "||" + offset[0] + ", " + offset[1] + " ||| " + offset[2]);
        if (offset[2] == 0) {
            return -1;
        }
        int[] polar = HexAlgs.offsetToPolar(offset[0], offset[1]);
        return polar[0];

    }

    public static int PolarHexZ(int x, int z) {

        Player owner = GetOwner(x,z);
        int ownerLocx = RelativeLocX(x,z);
        int ownerLocz = RelativeLocZ(x,z);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);
        if (offset[2] == 0) {
            return -1;
        }
        int[] polar = HexAlgs.offsetToPolar(offset[0], offset[1]);
        return polar[1];

    }



    public static int PolarHexZ(Player player, Player owner) {

        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        int[] polar = HexAlgs.offsetToPolar(offset[0], offset[1]);
        return polar[1];

    }

    public static int PolarHexZ(Player player) {

        Player owner = GetOwner(player);
        int ownerLocx = RelativeLocX(player, owner);
        int ownerLocz = RelativeLocZ(player, owner);

        int[] offset = HexAlgs.relativeToOffset(ownerLocx, ownerLocz);

        int[] polar = HexAlgs.offsetToPolar(offset[0], offset[1]);
        return polar[1];

    }


    public static boolean PlateUnlocked(Player player, int px, int pz) {

        if (Create.hasPlayerplate(player.getUniqueId(),px+","+pz)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean PlateUnlocked(Player player, int px, int pz, int level) {
        if (Create.getAPlayerPlate(player, px+","+pz) == null) {
            return false;
        }
        int plevel = Create.getAPlayerPlate(player, px+","+pz).getLevel();
        if (plevel >= level) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean BlockAccess(Player player, String world, int x, int y, int z) {
        if (!player.hasPermission("hexoplate.bypass")) {

            Player owner = GetOwner(x,z);
            if (!owner.equals(player)) {
                return false;
            }

            int miny = Integer.MAX_VALUE;
            int maxy = Integer.MAX_VALUE;
            String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".";
            // System.out.println(path);
            // List<Integer> schem = plugin.getConfig().getIntegerList(path + "y-level");
            int depthsize = plugin.getConfig().getInt(path +"depth-level.maximum");
            int heightsize = plugin.getConfig().getInt(path +"height-level.maximum");
            // owner.sendMessage(pluginprefix + "Size: " + size);
            for (int i=0; i< depthsize; i++) {
                // owner.sendMessage(pluginprefix + "i: " + i);
                if (y >= plugin.getConfig().getInt(path + "depth-level."+i)) {
                    miny = i;
                    break;
                }
            }
            for (int i=heightsize-1; i>= 0; i--) {
                // owner.sendMessage(pluginprefix + "i: " + i);
                if (y <= plugin.getConfig().getInt(path + "height-level."+i)) {
                    maxy = i;
                    break;
                }
            }
            // System.out.println("detect py = " + py);
            if (y < plugin.getConfig().getInt(path + "depth-level."+(depthsize-1))) {
                // System.out.println("y lower than minimum y-level in the config.");
                return false;
            }
            if (y > plugin.getConfig().getInt(path + "height-level."+(heightsize-1))) {
                // System.out.println("y lower than minimum y-level in the config.");
                return false;
            }

            //判定y在哪一层y-level

            if (world.equals(plugin.getConfig().getString("world"))) {
                if (owner != null) {

                    // System.out.println("owner is not null.");
                    if (HexLocStatus(x,z)){ // 如果在plate内
                        if (PlateUnlocked(owner, PolarHexX(x,z), PolarHexZ(x,z))) {
                            Plate plate = Create.getAPlayerPlate(owner, PolarHexX(x,z)+","+PolarHexZ(x,z));
                            int plevel = plate.getLevel();
                            // owner.sendMessage(pluginprefix + "Plevel: " + plevel + ", miny: " + miny + ", maxy: " + maxy);
                            if (plugin.getConfig().getInt(path + "type." + plate.getType() + ".upgrade." + plevel + ".depth-level") >= miny) {
                                if (plugin.getConfig().getInt(path + "type." + plate.getType() + ".upgrade." + plevel + ".height-level") >= maxy) {
                                    return true;
                                }
                            }

                            // owner.sendMessage(pluginprefix + "Event block: "+x + ", " + y + ", " + z +", Plate " + PolarHexX(x,z) + ", " + PolarHexZ(x,z) + ", "+ py + ", status: unlocked.");

                        }

                    }
                }
            }

            if (plugin.getConfig().getBoolean("border-access")) {
                if (
                        (PlateUnlocked(owner, PolarHexX(x,z+2), PolarHexZ(x,z+2), miny) && PlateUnlocked(owner, PolarHexX(x,z-2), PolarHexZ(x,z-2), miny))
                                || (PlateUnlocked(owner, PolarHexX(x+2,z), PolarHexZ(x+2,z), miny) && PlateUnlocked(owner, PolarHexX(x-2,z), PolarHexZ(x-2,z), miny))
                ) {
                    if (
                            (PlateUnlocked(owner, PolarHexX(x,z+2), PolarHexZ(x,z+2), maxy) && PlateUnlocked(owner, PolarHexX(x,z-2), PolarHexZ(x,z-2), maxy))
                                    || (PlateUnlocked(owner, PolarHexX(x+2,z), PolarHexZ(x+2,z), maxy) && PlateUnlocked(owner, PolarHexX(x-2,z), PolarHexZ(x-2,z), maxy))
                    ) {
                        return true;

                    }

                }
                // owner.sendMessage(pluginprefix + "Z: "+ x + ", " + y + ", " + z + " | " + PolarHexX(x, z+2) + ", " + PolarHexZ(x, z + 2) + "&" + PolarHexX(x, z-2) + ", " + PolarHexZ(x, z - 2) + ", " +  py + " | "+ PlateUnlocked(owner, PolarHexX(x, z+2), PolarHexZ(x, z + 2), py) + ", " + PlateUnlocked(owner, PolarHexX(x, z-3), PolarHexZ(x, z - 2), py));
                // owner.sendMessage(pluginprefix + "X: "+ x + ", " + y + ", " + z + " | " + PolarHexX(x+2, z) + ", " + PolarHexZ(x+2, z) + "&" + PolarHexX(x-2, z) + ", " + PolarHexZ(x-2, z) + ", " +  py + " | "+ PlateUnlocked(owner, PolarHexX(x + 2, z), PolarHexZ(x+2, z), py) +", "+ PlateUnlocked(owner, PolarHexX(x -2, z), PolarHexZ(x-3, z), py));

            }


            return false;
        } else {
            return true;
        }


    }

    public static boolean BlockAccess(String world, int x, int y, int z) {

        Player owner = GetOwner(x,z);
        int miny = -1;
        int maxy = -1;
        String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".";
        // System.out.println(path);
        // List<Integer> schem = plugin.getConfig().getIntegerList(path + "y-level");
        int depthsize = plugin.getConfig().getInt(path +"depth-level.maximum");
        int heightsize = plugin.getConfig().getInt(path +"height-level.maximum");
        // owner.sendMessage(pluginprefix + "Size: " + size);
        for (int i=0; i< depthsize; i++) {
            // owner.sendMessage(pluginprefix + "i: " + i);
            if (y >= plugin.getConfig().getInt(path + "depth-level."+i)) {
                miny = i;
                break;
            }
        }
        for (int i=heightsize; i> 0; i--) {
            // owner.sendMessage(pluginprefix + "i: " + i);
            if (y <= plugin.getConfig().getInt(path + "height-level."+i)) {
                maxy = i;
                break;
            }
        }
        // System.out.println("detect py = " + py);
        if (y < plugin.getConfig().getInt(path + "depth-level."+(depthsize-1))) {
            // System.out.println("y lower than minimum y-level in the config.");
            return false;
        }
        if (y > plugin.getConfig().getInt(path + "height-level."+(heightsize-1))) {
            // System.out.println("y lower than minimum y-level in the config.");
            return false;
        }

        //判定y在哪一层y-level

        if (world.equals(plugin.getConfig().getString("world"))) {
            if (owner != null) {

                // System.out.println("owner is not null.");
                if (HexLocStatus(x,z)){ // 如果在plate内
                    if (PlateUnlocked(owner, PolarHexX(x,z), PolarHexZ(x,z))) {
                        Plate plate = Create.getAPlayerPlate(owner, PolarHexX(x,z)+","+PolarHexZ(x,z));
                        int plevel = plate.getLevel();
                        if (plugin.getConfig().getInt(path + "type." + plate.getType() + ".upgrade." + plevel + ".depth-level") >= miny) {
                            if (plugin.getConfig().getInt(path + "type." + plate.getType() + ".upgrade." + plevel + ".height-level") >= maxy) {
                                return true;
                            }
                        }

                        // owner.sendMessage(pluginprefix + "Event block: "+x + ", " + y + ", " + z +", Plate " + PolarHexX(x,z) + ", " + PolarHexZ(x,z) + ", "+ py + ", status: unlocked.");

                    }

                }
            }
        }

//        if (plugin.getConfig().getBoolean("border-access")) {
//            if (
//                    (PlateUnlocked(owner, PolarHexX(x,z+2), PolarHexZ(x,z+2), miny) && PlateUnlocked(owner, PolarHexX(x,z-2), PolarHexZ(x,z-2), miny))
//                            || (PlateUnlocked(owner, PolarHexX(x+2,z), PolarHexZ(x+2,z), miny) && PlateUnlocked(owner, PolarHexX(x-2,z), PolarHexZ(x-2,z), miny))
//            ) {
//                if (
//                        (PlateUnlocked(owner, PolarHexX(x,z+2), PolarHexZ(x,z+2), maxy) && PlateUnlocked(owner, PolarHexX(x,z-2), PolarHexZ(x,z-2), maxy))
//                                || (PlateUnlocked(owner, PolarHexX(x+2,z), PolarHexZ(x+2,z), maxy) && PlateUnlocked(owner, PolarHexX(x-2,z), PolarHexZ(x-2,z), maxy))
//                ) {
//                    return true;
//
//                }
//
//            }
//            // owner.sendMessage(pluginprefix + "Z: "+ x + ", " + y + ", " + z + " | " + PolarHexX(x, z+2) + ", " + PolarHexZ(x, z + 2) + "&" + PolarHexX(x, z-2) + ", " + PolarHexZ(x, z - 2) + ", " +  py + " | "+ PlateUnlocked(owner, PolarHexX(x, z+2), PolarHexZ(x, z + 2), py) + ", " + PlateUnlocked(owner, PolarHexX(x, z-3), PolarHexZ(x, z - 2), py));
//            // owner.sendMessage(pluginprefix + "X: "+ x + ", " + y + ", " + z + " | " + PolarHexX(x+2, z) + ", " + PolarHexZ(x+2, z) + "&" + PolarHexX(x-2, z) + ", " + PolarHexZ(x-2, z) + ", " +  py + " | "+ PlateUnlocked(owner, PolarHexX(x + 2, z), PolarHexZ(x+2, z), py) +", "+ PlateUnlocked(owner, PolarHexX(x -2, z), PolarHexZ(x-3, z), py));
//
//        }

        return false;

    }






    public static boolean OwnerPermission(Player player) {

        if (GetOwner(player).equals(player)){
            if (HexLocStatus(player, player)) {
                if(player.hasPermission("hexoplate.plate." + PolarHexX(player, player) + "." + PolarHexZ(player, player))){
                    return true;
                }

            }
        }

        return false;
    }

}
