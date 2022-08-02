# Hexoplate
## Description

HexoPlate is a Minecraft plugin that provides hexagonal plate terrain for island-style servers. With the plugin, players are able to create, upgrade and extend plates in a hexagonal grid centered on their island.



## Server Version

Spigot 1.13-1.19 （tested in paper/purpur 1.19 Server）



## Plates

/



## Coordinate System (0,0 for the central plate)

![image-20220801232238137](https://raw.githubusercontent.com/bowencraft/HexoPlate/main/utils/image-20220801232238137.png)



## Dependency

```
FastAsyncWorldEdit
```



## Commands & Permissions

Currently operations to the plates can only be performed by the console or by the admin with permissions. Players can view their information via `/hexplate playerinfo`.

| Commands                                                   | Permissions                                         | Descriptions                                                 |
| ---------------------------------------------------------- | --------------------------------------------------- | ------------------------------------------------------------ |
| /hexoplate, /hexo or /hex                                  | hexoplate.commands.main<br/>hexoplate.commands.help | Main: Use /hexoplate related commands<br/>Help: Check the commands help list |
| /hexoplate calculate <type> <x> <z>                        | hexoplate.commands.admin.calculate                  | Calculate convertion between the coords offset/polar/relative <br/> Options at <type>：relativeToOffset, polarToOffset, offsetToPolar, polarToCentral |
| /hexoplate admincreate <player> [type]                     | hexoplate.commands.admin.create                     | Create the plate grids (and central plate) for specified player<br/>[type] - optional to select the specified type in the list (under type in config.yml). If empty, the default plate type will be selected. |
| /hexoplate extend <player name> <PolarX> <PolarZ> [type]   | hexoplate.commands.admin.extend                     | Extend(Create) a new plate for specified players<br/><PolarX><PolarZ> corresponds to the X and Z coordinates of the plate in the player's Polar coordinate grid. |
| /hexoplate reload                                          | hexoplate.commands.admin.reload                     | Reload the plugin                                            |
| /hexoplate upgrade <player name> <PolarX> <PolarZ> <level> | hexoplate.commands.admin.upgrade                    | Upgrade the plates that already exist before for the specified player<br/><level> for the increased level (can be negative) |
| /hexoplate playerinfo [player]                             | hexoplate.commands.playerinfo.*                     | View the information for the specified player (If empty, view player's own information) |
| /hexoplate playerinfo                                      | hexoplate.commands.playerinfo.me                    |                                                              |



## Configuration Files

#### config.yml

```yaml
version: 0.1

message-language: en_US
# Message file in the locale folder.
# Option: en_US, zh_CN

plugin-prefix: "§f[§6Hexo§ePlate§f] §a"

height: 64
# Y-axis that plate will be created.

maximum: 10
# Affect maximum number of the polarX (radius).
# Maximum * plate-width should not larger than player-interval.

world: bskyblock_world
# World that hexoplate will affect.

radius: 40
# Preset for max polar plate radius when generating hexoplate convert list.
# Should be larger than or equal to player-interval / plate-width.

border-access: true
# Whether detect interaction with the border.
# If true, when the event happened in the border which are between two unlocked plate, it wouldn't be cancel.

plate-length: 17
plate-width: 20
# The width and length of a plate in schem.
# Shouldn't be changed until in future.

plate-schem-group: default
# Group of the schem under length * width set up above.
# Make it possible to have multiple groups in the config at the same time.

players-interval: 500
# Interval between two players's central plate.

Plate-list:
  17*20:
    default:
      background:
        # Setting of the background. A background schem will be generated when a player create the new plate grid.
        # Sequence: 1. Generate background schem. 2. Generate plate schem. 3. Teleport player.
        status: true
        schem-file: default_bg.schem
        # When the option is true, the generating will ignore the air in the schem.
        ignore-air: true
#        border-calculation: # 2d-array of 20*4
#          L1: 5,4,4,3,3,2,2,1,1,0,0,0,1,1,2,2,3,3,4,4
#          L2: 6,6,5,5,4,4,3,3,2,2,1,2,2,3,3,4,4,5,5,6
#          L3: 0,0,1,1,2,2,3,3,4,4,5,4,4,3,3,2,2,1,1,0
#          L4: 1,2,2,3,3,4,4,5,5,6,6,6,5,5,4,4,3,3,2,2
      type:
        default:
          schem-files-list:
            # Plate schem file list.
            # You can list multiple filenames here to make generator randomly choose from them each time.
            - default.schem
          schem-ignore-air: true
          upgrade:
            0:
              # Initial level of a plate.
              depth-level: 0
              height-level: 0
            1:
              depth-level: 1
              height-level: 1
      depth-level:
        # Available block range of each level.
        # Level larger than maximum will not be effective.
        maximum: 1
        0: 64
        1: 32
        2: 0
      height-level:
        maximum: 1
        0: 96
        1: 128
        2: 160
```

Locale/en_US.yml: English messages

Data/data.yml: Player data storage



## Future Plans

- [ ] Add GUI for Player Upgrade / Extend
- [ ] Support skyblock or island-style Plugins
- [ ] Support PAPI Variables
- [ ] Add support for SQL storage
- [ ] Supports plate grid for different shape and dimension
- [ ] Members List for the plate
