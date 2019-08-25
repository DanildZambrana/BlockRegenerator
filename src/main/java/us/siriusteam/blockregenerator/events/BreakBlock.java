package us.siriusteam.blockregenerator.events;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import us.siriusteam.blockregenerator.BlockRegenerator;
import us.siriusteam.blockregenerator.blocks.BlockController;
import us.siriusteam.blockregenerator.blocks.TimerBlock;
import us.siriusteam.blockregenerator.utils.Color;
import us.siriusteam.blockregenerator.utils.RegionsUtil;


public class BreakBlock implements Listener {
    private RegionsUtil regionsUtil = new RegionsUtil();
    private BlockRegenerator plugin = BlockRegenerator.getInstance();

    @EventHandler
    public void onBreack(BlockBreakEvent e){
        FileConfiguration config = plugin.getConfig();
        Block block = e.getBlock();
        int material = block.getTypeId();
        byte data = block.getData();

        for (String reg : plugin.getConfig().getConfigurationSection("Regions").getKeys(false)) { //obtiene la region
            String region = config.getString("Regions."+reg+".region");

            if (regionsUtil.isWithinRegion(block.getLocation(), region)) {//Comprueba si esta dentro de una region permitida

                for (String bNum : plugin.getConfig().getConfigurationSection("Regions."+reg+".blocks").getKeys(false)){ //obtiene la lista de bloques a reemplasar
                    int bID = Integer.parseInt(config.getString("Regions." + reg + ".blocks."+bNum+".block.id"));
                    byte bData = Byte.parseByte(config.getString("Regions." + reg + ".blocks."+bNum+".block.data"));

                    if ((material == bID) && (data == bData)) {
                        BlockController.replaceBlock(block.getLocation(),
                                Integer.parseInt(config.getString("Regions." + reg + ".blocks."+bNum+".replace.id"))
                                , Byte.parseByte(config.getString("Regions." + reg + ".blocks."+bNum+".replace.data")));
                        break;
                    }
                }

                long time;
                try{
                    time = Long.parseLong(config.getString("TimeInSeconds"));
                }catch (Exception e1){
                    plugin.getLogger().severe(e1.getCause().getMessage());
                    e.getPlayer().sendMessage(Color.toColor("&c Ocurrio un error. informa al administrador: " + e1.getCause().getMessage()));
                    break;
                }
                //inicia el scheduller
                TimerBlock.waitToset(block.getLocation(), data, material, time);
                //fin del scheduller
                break;
            }
        }
    }
}