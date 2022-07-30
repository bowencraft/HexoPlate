package com.bowencraft.hexoplate.utils;

import com.bowencraft.hexoplate.HexoPlate;
import com.bowencraft.hexoplate.files.data;
import com.bowencraft.hexoplate.files.messages;
import com.fastasyncworldedit.core.FaweAPI;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class Create {
    private static HexoPlate plugin;
    private static String pluginprefix;

    public Create(HexoPlate plugin) {
        this.plugin = plugin;
        pluginprefix = plugin.getConfig().getString("plugin-prefix");
    }

    private static final HashMap<UUID, Boolean> firstPlate = new HashMap<>();
    private static final HashMap<UUID, Integer> plateX = new HashMap<>();
    private static final HashMap<UUID, Integer> plateZ = new HashMap<>();

    private static final HashMap<String, UUID> centralHex = new HashMap<>();
    // private static final HashMap<String, ArrayList<UUID>> centralHex = new HashMap<>();

    private static final HashMap<UUID, HashMap<String, Plate>> playerPlate = new HashMap<>();




    public static HashMap<UUID, Boolean> getFirstPlate() {
        return firstPlate;
    }

    public static String getSchemFromList(String path) {
        List<String> schem = plugin.getConfig().getStringList(path);

        int size = schem.size();

        int random = (int) Math.floor(Math.random() * size);
        return schem.get(random);

    }

    // HashMap<UUID, HashMap<String, Plate>>
    public static HashMap<String, Plate> addPlayerplate(UUID uuid, String coord, Plate plate) {
        HashMap<String, Plate> player_platelist = new HashMap<String, Plate>();
        if (playerPlate.get(uuid) != null) {
            player_platelist = playerPlate.get(uuid);
        }
        if (!hasPlayerplate(uuid, coord)) {
            player_platelist.put(coord, plate);
        }
        return player_platelist;
    }

    // HashMap<UUID, HashMap<String, Plate>>
    public static Plate getAPlayerPlate(Player player, String coord) {
        return playerPlate.get(player.getUniqueId()).get(coord);
    }

    public static HashMap<String, Plate> removePlayerplate(UUID uuid, String coord) {
        HashMap<String, Plate> player_platelist = new HashMap<String, Plate>();
        if (playerPlate.get(uuid) != null) {
            player_platelist = playerPlate.get(uuid);
        }
        if (hasPlayerplate(uuid, coord)) {
            player_platelist.remove(coord);
        }
        return player_platelist;
    }


    public static boolean hasPlayerplate(UUID uuid, String coord) {
        HashMap<String, Plate> player_platelist = new HashMap<String, Plate>();

        if (playerPlate.get(uuid) != null) {
            player_platelist = playerPlate.get(uuid);
        }
        boolean status = player_platelist.containsKey(coord);
        return status;
    }


    public static void loadplayerplate() {
        try {
            for(String key : data.get().getConfigurationSection("Player").getKeys(false))
            {
                HashMap<String, Plate> plates = new HashMap<String, Plate>();
                // System.out.println("Key: " + key);
                // System.out.println(data.get().getConfigurationSection( ("Player." + key + ".Plate") ).getKeys(false));
                for(String plate: data.get().getConfigurationSection( ("Player." + key + ".Plate") ).getKeys(false)) {
                    Player player = Bukkit.getPlayer(UUID.fromString(key));
                    int polarX = data.get().getInt("Player." + key + ".Plate." + plate + ".polarX");
                    int polarZ = data.get().getInt("Player." + key + ".Plate." + plate + ".polarZ");
                    String type = data.get().getString("Player." + key + ".Plate." + plate + ".type");
                    int level = data.get().getInt("Player." + key + ".Plate." + plate + ".level");
                    plates.put(plate, new Plate(player, polarX,polarZ, type, level));
                    // System.out.println(player.getName() +  "'s Plate " + polarX + "," + polarZ + " loaded.");
                }
                playerPlate.put(UUID.fromString(key), plates);

            }
        } catch (Exception e) {
            System.out.println("Player plate data is missing or imcomplete in data.yml.");
        }
    }
    public static void saveplayerplate() {
        for(Map.Entry<UUID, HashMap<String, Plate>> entry : playerPlate.entrySet()) {

            HashMap<String, Plate> plate = entry.getValue();
            for (Map.Entry<String, Plate> entry1 : plate.entrySet()) {
                data.get().set("Player." + entry.getKey() + ".Plate." + entry1.getKey() + ".polarX", entry1.getValue().getPolarX());
                data.get().set("Player." + entry.getKey() + ".Plate." + entry1.getKey() + ".polarZ", entry1.getValue().getPolarZ());
                data.get().set("Player." + entry.getKey() + ".Plate." + entry1.getKey() + ".type", entry1.getValue().getType());
                data.get().set("Player." + entry.getKey() + ".Plate." + entry1.getKey() + ".level", entry1.getValue().getLevel());

            }
        }
            //}

    }


    public static void loadcentralHex() {
        try {
            for(String key : data.get().getConfigurationSection("Grid.Plate-Grid").getKeys(false))
            {
                centralHex.put(key, UUID.fromString(data.get().getString("Grid.Plate-Grid." + key)));
            }
        } catch (Exception e) {
            System.out.println("Player CentralHex data is missing or imcomplete in data.yml.");
        }

    }
    public static void savecentralHex() {
        for(Map.Entry<String, UUID> entry : centralHex.entrySet())
        {
            data.get().set("Grid.Plate-Grid." + entry.getKey(), entry.getValue().toString());
        }
    }

    public static void loadplate() {
        try {
            for(String key : data.get().getConfigurationSection("Player").getKeys(false))
            {
                plateX.put(UUID.fromString(key), data.get().getInt("Player." + key + ".centralX"));
                plateZ.put(UUID.fromString(key), data.get().getInt("Player." + key + ".centralZ"));
            }
        } catch (Exception e) {
            System.out.println("Player central position data is missing or imcomplete in data.yml.");
        }
    }
    public static void saveplate() {
        for(Map.Entry<UUID, Integer> entry : plateX.entrySet())
        {
            data.get().set("Player." + entry.getKey() + ".centralX", entry.getValue());
        }
        for(Map.Entry<UUID, Integer> entry : plateZ.entrySet())
        {
            data.get().set("Player." + entry.getKey() + ".centralZ", entry.getValue());
        }
    }


    public static void loadcreatestatus() {
        try {
            for(String key : data.get().getConfigurationSection("Player").getKeys(false))
            {
                firstPlate.put(UUID.fromString(key), data.get().getBoolean("Player." + key + ".created"));
            }
        } catch (Exception e) {
            System.out.println("Player creation status data is missing or imcomplete in data.yml.");
        }
    }
    public static void savecreatestatus() {
        for(Map.Entry<UUID, Boolean> entry : firstPlate.entrySet())
        {
            data.get().set("Player." + entry.getKey() + ".created", entry.getValue());
        }
    }

    public static int getPlateX(UUID uuid) {
        return plateX.get(uuid);
    }

    public static int getPlateZ(UUID uuid) {
        return plateZ.get(uuid);
    }

    public static UUID getcentralHex(String loc0) {
//        String[] loc = loc0.split(",");
//        int x = Integer.parseInt(loc[0]);
//        int z = Integer.parseInt(loc[1]);
        return centralHex.get(loc0);

    }



    public static void createloc(Player player) {

        if (!firstPlate.containsKey(player.getUniqueId())){

            firstPlate.put(player.getUniqueId(), true);
            int x = plugin.getConfig().getInt("players-interval") * (firstPlate.size() - 1);
            int z = 0;

            centralHex.put(((firstPlate.size() - 1)+","+z), player.getUniqueId());

            plateX.put(player.getUniqueId(), x);
            plateZ.put(player.getUniqueId(), z);

            String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".";

            // playerPlate.put(player.getUniqueId(), addPlayerplate(player.getUniqueId(), "0,0,0"));
            data.save();


        }

    }

    public static void createbg(Player player, Integer px, Integer pz, String schem) {

            File file = new File(plugin.getDataFolder().getPath(), "/schem/" + schem);

            int relativex[] = HexAlgs.polarToCentralXY(px,pz);

            int x = relativex[0] + plateX.get(player.getUniqueId());
            int y = plugin.getConfig().getInt("height");
            int z = relativex[1] + plateZ.get(player.getUniqueId());

            Clipboard clipboard;

            ClipboardFormat format = ClipboardFormats.findByFile(file);
            try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
                clipboard = reader.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            World world = FaweAPI.getWorld(plugin.getConfig().getString("world"));

            String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".";
            boolean ignoreair = plugin.getConfig().getBoolean(path +"background.ignore-air");

            try (EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {
                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(x, y, z))
                        .ignoreAirBlocks(ignoreair)
                        .build();
                Operations.complete(operation);
            }


    }

    public static void createplate(Player player, Integer px, Integer pz, String schem, String type) {

        if(!hasPlayerplate(player.getUniqueId(),px+","+pz)){
            File file = new File(plugin.getDataFolder().getPath(), "/schem/" + schem);

            int relativex[] = HexAlgs.polarToCentralXY(px,pz);

            int x = relativex[0] + plateX.get(player.getUniqueId());
            int y = plugin.getConfig().getInt("height");
            int z = relativex[1] + plateZ.get(player.getUniqueId());

            Clipboard clipboard;

            ClipboardFormat format = ClipboardFormats.findByFile(file);
            try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
                clipboard = reader.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".type.";
            boolean ignoreair = plugin.getConfig().getBoolean(path + type + ".schem-ignore-air");


            World world = FaweAPI.getWorld(plugin.getConfig().getString("world"));

            try (EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {
                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(x, y, z))
                        .ignoreAirBlocks(ignoreair)
                        .build();
                Operations.complete(operation);
            }
            Plate plate = new Plate(player, px, pz, type);

            playerPlate.put(player.getUniqueId(), addPlayerplate(player.getUniqueId(), px + ","+ pz, plate));
            data.save();

            String createsuccessfully = messages.get().getString("Plate-Create-Success");
            String context = MessageFormat.format(createsuccessfully, px, pz, x, y, z);
            player.sendMessage(pluginprefix + context);
        } else {
            player.sendMessage(pluginprefix + messages.get().getString("Plate-Already-Created"));
        }
    }


    public static void upgradeplate(Player player, Integer px, Integer pz, Integer level) {

        if(!hasPlayerplate(player.getUniqueId(),px+","+pz)){
            player.sendMessage(pluginprefix + messages.get().getString("Plate-Locked"));
        } else {
            Plate plate = getAPlayerPlate(player, px+","+pz);
            String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".type.";

            int maxlevel = plugin.getConfig().getList(path + plate.getType() + ".upgrade").size();
            if(plate.getLevel() + level <= maxlevel){
                plate.setLevel(plate.getLevel() + level);
                addPlayerplate(player.getUniqueId(), px + ","+ pz, plate);
                data.save();
                String upgradesuccess = messages.get().getString("Plate-Upgrade-Success");
                String context = MessageFormat.format(upgradesuccess, px, pz, plate.getLevel());
                player.sendMessage(pluginprefix + context);
            } else {
                player.sendMessage(pluginprefix + messages.get().getString("Plate-Maximum-Level"));
            }
        }
    }

    public static void downgrade(Player player, Integer px, Integer pz, Integer level) {

        if(!hasPlayerplate(player.getUniqueId(),px+","+pz)){
            player.sendMessage(pluginprefix + messages.get().getString("Plate-Locked"));
        } else {
            Plate plate = getAPlayerPlate(player, px+","+pz);
            String path = "Plate-list." + plugin.getConfig().getInt("plate-length") + "*" + plugin.getConfig().getInt("plate-width") + "." + plugin.getConfig().getString("plate-schem-group") + ".type.";

            if(plate.getLevel() - level >= 0){
                plate.setLevel(plate.getLevel() - level);
                addPlayerplate(player.getUniqueId(), px + ","+ pz, plate);
                data.save();

                String downgradesuccess = messages.get().getString("Plate-Downgrade-Success");
                String context = MessageFormat.format(downgradesuccess, px, pz, plate.getLevel());
                player.sendMessage(pluginprefix + context);
            } else {
                player.sendMessage(pluginprefix + messages.get().getString("Plate-Minimum-Level"));
            }
        }
    }

    public static int[] getPlayerCentralPlate(Player player) {
        int[] loc = new int[2];

        loc[0] = plateX.get(player.getUniqueId());
        loc[1] = plateZ.get(player.getUniqueId());
        return loc;
    }
}
