package us.siriusteam.blockregenerator;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import us.siriusteam.blockregenerator.events.BreakBlock;
import us.siriusteam.blockregenerator.utils.Color;

import java.io.File;
import java.util.logging.Logger;

public final class BlockRegenerator extends JavaPlugin {
    private static BlockRegenerator instance;
    private Logger log = getLogger();

    @Override
    public void onLoad() {
        //Registro de archivos
        registerConfig();
        //Fin de registro de archivos
    }

    @Override
    public void onEnable() {
        //Deteccion de dependencias
        if(getServer().getPluginManager().getPlugin("WorldGuard") == null){
            log.severe(String.format("[%s] - Desabilitando debido a que no se encontro WorldGuard entre tu lista de plugins", getDescription().getPrefix()));
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        //Fin deteccion de dependencias
        instance = this;
        getServer().getPluginManager().registerEvents(new BreakBlock(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BlockRegenerator getInstance() {
        if(instance == null) instance = new BlockRegenerator();
        return instance;
    }

    //Registro de config.yml
    private void registerConfig(){
        File config = new File(this.getDataFolder(), ("config.yml"));
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            Bukkit.getConsoleSender().sendMessage(Color.toColor("&cconfig.yml &7is not found, creating file..."));
            saveConfig();
        }
    }

    //WorldGuard
    public WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) plugin;
    }
}
