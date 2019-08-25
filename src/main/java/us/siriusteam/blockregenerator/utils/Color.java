package us.siriusteam.blockregenerator.utils;

import org.bukkit.ChatColor;

public class Color {
    public static String toColor(String texto){
        return ChatColor.translateAlternateColorCodes('&', texto);
    }
}
