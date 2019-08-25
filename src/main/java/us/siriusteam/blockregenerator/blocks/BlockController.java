package us.siriusteam.blockregenerator.blocks;


import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BlockController {
    public static void replaceBlock(Location location, int material, byte data){
        Bukkit.getServer().getWorld(location.getWorld().getName()).getBlockAt(location).setTypeIdAndData(material, data, false);
    }
}
