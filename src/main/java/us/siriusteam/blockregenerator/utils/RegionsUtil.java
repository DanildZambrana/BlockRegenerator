package us.siriusteam.blockregenerator.utils;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import us.siriusteam.blockregenerator.BlockRegenerator;

import static com.sk89q.worldguard.bukkit.BukkitUtil.toVector;

public class RegionsUtil{
    private BlockRegenerator plugin = BlockRegenerator.getInstance();

    public boolean isWithinRegion(Player player, String region) {
        return isWithinRegion(player.getLocation(), region);
    }

    /*public boolean isWithinRegion(Player player, String region) {
        return isWithinRegion(player.getLocation(), region);
    }*/


    public boolean isWithinRegion(Location loc, String region) {
        WorldGuardPlugin guard = plugin.getWorldGuard();
        Vector v = toVector(loc);
        RegionManager manager = guard.getRegionManager(loc.getWorld());
        ApplicableRegionSet set = manager.getApplicableRegions(v);
        for (ProtectedRegion each : set)
                if (each.getId().equalsIgnoreCase(region)) {
                    return true;
                }
        return false;
    }


    /*private boolean isWithinRegion(Location loc, String region) {
        WorldGuardPlugin guard = plugin.getWorldGuard();
        Vector v = toVector(loc);
        RegionManager manager = guard.getRegionManager(loc.getWorld());
        ApplicableRegionSet set = manager.getApplicableRegions(v);
        for (ProtectedRegion each : set) {
            if (each.getId().equalsIgnoreCase(region)) {
                return true;
            }
        }
        return false;
    }*/

}
