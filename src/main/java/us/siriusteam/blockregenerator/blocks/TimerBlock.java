package us.siriusteam.blockregenerator.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import us.siriusteam.blockregenerator.BlockRegenerator;

public class TimerBlock {
    public static Runnable waitToset(final Location location, final byte data, final int material, long delay) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(BlockRegenerator.getInstance(), new Runnable() {
            @Override
            public void run() {
                //reemplasa el bloque por el que estaba antes
                BlockController.replaceBlock(location, material, data);
            }
        }, delay * 20);
        return null;
    }
}
