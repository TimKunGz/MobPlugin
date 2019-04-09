package nukkitcoders.mobplugin;

import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.Config;
import nukkitcoders.mobplugin.entities.BaseEntity;
import nukkitcoders.mobplugin.entities.animal.flying.*;
import nukkitcoders.mobplugin.entities.animal.jumping.Rabbit;
import nukkitcoders.mobplugin.entities.animal.swimming.*;
import nukkitcoders.mobplugin.entities.animal.walking.*;
import nukkitcoders.mobplugin.entities.autospawn.IEntitySpawner;
import nukkitcoders.mobplugin.entities.monster.flying.*;
import nukkitcoders.mobplugin.entities.monster.jumping.MagmaCube;
import nukkitcoders.mobplugin.entities.monster.jumping.Slime;
import nukkitcoders.mobplugin.entities.monster.walking.*;
import nukkitcoders.mobplugin.entities.spawners.*;
import nukkitcoders.mobplugin.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoSpawnTask extends Thread {

    private Map<Integer, Integer> maxSpawns = new HashMap<>();

    private List<IEntitySpawner> entitySpawners = new ArrayList<>();

    private Config pluginConfig = null;

    private MobPlugin plugin = null;

    public AutoSpawnTask(MobPlugin plugin) {
        this.pluginConfig = plugin.getConfig();
        this.plugin = plugin;

        prepareMaxSpawns();
        prepareSpawnerClasses();
    }

    @Override
    public void run() {
        if (plugin.getServer().getOnlinePlayers().size() > 0) {
            for (IEntitySpawner spawner : entitySpawners) {
                spawner.spawn(plugin.getServer().getOnlinePlayers().values());
            }
        }
    }

    private void prepareSpawnerClasses() {
        entitySpawners.add(new BatSpawner(this, this.pluginConfig));
        entitySpawners.add(new BlazeSpawner(this, this.pluginConfig));
        entitySpawners.add(new ChickenSpawner(this, this.pluginConfig));
        entitySpawners.add(new CodSpawner(this, this.pluginConfig));
        entitySpawners.add(new CowSpawner(this, this.pluginConfig));
        entitySpawners.add(new CreeperSpawner(this, this.pluginConfig));
        entitySpawners.add(new DolphinSpawner(this, this.pluginConfig));
        entitySpawners.add(new DonkeySpawner(this, this.pluginConfig));
        entitySpawners.add(new EndermanSpawner(this, this.pluginConfig));
        entitySpawners.add(new GhastSpawner(this, this.pluginConfig));
        entitySpawners.add(new HorseSpawner(this, this.pluginConfig));
        entitySpawners.add(new HuskSpawner(this, this.pluginConfig));
        entitySpawners.add(new MagmaCubeSpawner(this, this.pluginConfig));
        entitySpawners.add(new MooshroomSpawner(this, this.pluginConfig));
        entitySpawners.add(new OcelotSpawner(this, this.pluginConfig));
        entitySpawners.add(new ParrotSpawner(this, this.pluginConfig));
        entitySpawners.add(new PigSpawner(this, this.pluginConfig));
        entitySpawners.add(new PolarBearSpawner(this, this.pluginConfig));
        entitySpawners.add(new PufferfishSpawner(this, this.pluginConfig));
        entitySpawners.add(new RabbitSpawner(this, this.pluginConfig));
        entitySpawners.add(new SalmonSpawner(this, this.pluginConfig));
        entitySpawners.add(new SheepSpawner(this, this.pluginConfig));
        entitySpawners.add(new SkeletonSpawner(this, this.pluginConfig));
        entitySpawners.add(new SlimeSpawner(this, this.pluginConfig));
        entitySpawners.add(new SpiderSpawner(this, this.pluginConfig));
        entitySpawners.add(new StraySpawner(this, this.pluginConfig));
        entitySpawners.add(new SquidSpawner(this, this.pluginConfig));
        entitySpawners.add(new TropicalFishSpawner(this, this.pluginConfig));
        entitySpawners.add(new TurtleSpawner(this, this.pluginConfig));
        entitySpawners.add(new WitchSpawner(this, this.pluginConfig));
        entitySpawners.add(new WitherSkeletonSpawner(this, this.pluginConfig));
        entitySpawners.add(new WolfSpawner(this, this.pluginConfig));
        entitySpawners.add(new ZombieSpawner(this, this.pluginConfig));
        entitySpawners.add(new ZombiePigmanSpawner(this, this.pluginConfig));
    }

    private void prepareMaxSpawns() {
        maxSpawns.put(Bat.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.bat") ? 1 : 0);
        maxSpawns.put(Blaze.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.blaze") ? 1 : 0);
        maxSpawns.put(Chicken.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.chicken") ? 1 : 0);
        maxSpawns.put(Cod.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.cod") ? 1 : 0);
        maxSpawns.put(Cow.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.cow") ? 1 : 0);
        maxSpawns.put(Creeper.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.creeper") ? 1 : 0);
        maxSpawns.put(Dolphin.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.dolphin") ? 1 : 0);
        maxSpawns.put(Donkey.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.donkey") ? 1 : 0);
        maxSpawns.put(Enderman.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.enderman") ? 1 : 0);
        maxSpawns.put(Ghast.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.ghast") ? 1 : 0);
        maxSpawns.put(Horse.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.horse") ? 1 : 0);
        maxSpawns.put(MagmaCube.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.magmacube") ? 1 : 0);
        maxSpawns.put(Mooshroom.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.mooshroom") ? 1 : 0);
        maxSpawns.put(Ocelot.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.ocelot") ? 1 : 0);
        maxSpawns.put(Parrot.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.parrot") ? 1 : 0);
        maxSpawns.put(Pig.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.pig") ? 1 : 0);
        maxSpawns.put(PolarBear.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.polarbear") ? 1 : 0);
        maxSpawns.put(Pufferfish.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.pufferfish") ? 1 : 0);
        maxSpawns.put(Rabbit.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.rabbit") ? 1 : 0);
        maxSpawns.put(Salmon.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.salmon") ? 1 : 0);
        maxSpawns.put(Sheep.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.sheep") ? 1 : 0);
        maxSpawns.put(Skeleton.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.skeleton") ? 1 : 0);
        maxSpawns.put(Slime.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.slime") ? 1 : 0);
        maxSpawns.put(Spider.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.spider") ? 1 : 0);
        maxSpawns.put(Squid.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.squid") ? 1 : 0);
        maxSpawns.put(Stray.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.stray") ? 1 : 0);
        maxSpawns.put(TropicalFish.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.tropicalfish") ? 1 : 0);
        maxSpawns.put(Turtle.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.turtle") ? 1 : 0);
        maxSpawns.put(Witch.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.witch") ? 1 : 0);
        maxSpawns.put(WitherSkeleton.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.witherskeleton") ? 1 : 0);
        maxSpawns.put(Wolf.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.wolf") ? 1 : 0);
        maxSpawns.put(Zombie.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.zombie") ? 1 : 0);
        maxSpawns.put(ZombiePigman.NETWORK_ID, this.pluginConfig.getBoolean("autospawn.zombiepigman") ? 1 : 0);
    }

    public boolean entitySpawnAllowed(Level level, int networkId) {
        return countEntity(level, networkId) < maxSpawns.getOrDefault(networkId, 0);
    }

    private int countEntity(Level level, int networkId) {
        int count = 0;
        for (Entity entity : level.getEntities()) {
            if (entity.isAlive() && entity.getNetworkId() == networkId) {
                count++;
            }
        }
        return count;
    }

    public BaseEntity createEntity(Object type, Position pos) {
        BaseEntity entity = (BaseEntity) MobPlugin.create(type, pos);
        if (entity != null) {
            entity.spawnToAll();
        }
        return entity;
    }

    public int getRandomSafeXZCoord(int degree, int safeDegree, int correctionDegree) {
        int addX = Utils.rand(degree / 2 * -1, degree / 2);
        if (addX >= 0) {
            if (degree < safeDegree) {
                addX = safeDegree;
                addX += Utils.rand(correctionDegree / 2 * -1, correctionDegree / 2);
            }
        } else {
            if (degree > safeDegree) {
                addX = -safeDegree;
                addX += Utils.rand(correctionDegree / 2 * -1, correctionDegree / 2);
            }
        }
        return addX;
    }

    public int getSafeYCoord(Level level, Position pos, int needDegree) {
        int x = (int) pos.x;
        int y = (int) pos.y;
        int z = (int) pos.z;

        if (level.getBlockIdAt(x, y, z) == Block.AIR) {
            while (true) {
                y--;
                if (y > 255) {
                    y = 256;
                    break;
                }
                if (y < 1) {
                    y = 0;
                    break;
                }
                if (level.getBlockIdAt(x, y, z) != Block.AIR) {
                    int checkNeedDegree = needDegree;
                    int checkY = y;
                    while (true) {
                        checkY++;
                        checkNeedDegree--;
                        if (checkY > 255 || checkY < 1 || level.getBlockIdAt(x, checkY, z) != Block.AIR) {
                            break;
                        }

                        if (checkNeedDegree <= 0) {
                            return y;
                        }
                    }
                }
            }
        } else {
            while (true) {
                y++;
                if (y > 255) {
                    y = 256;
                    break;
                }

                if (y < 1) {
                    y = 0;
                    break;
                }

                if (level.getBlockIdAt(x, y, z) != Block.AIR) {
                    int checkNeedDegree = needDegree;
                    int checkY = y;
                    while (true) {
                        checkY--;
                        checkNeedDegree--;
                        if (checkY > 255 || checkY < 1 || level.getBlockIdAt(x, checkY, z) != Block.AIR) {
                            break;
                        }

                        if (checkNeedDegree <= 0) {
                            return y;
                        }
                    }
                }
            }
        }
        return y;
    }
}
