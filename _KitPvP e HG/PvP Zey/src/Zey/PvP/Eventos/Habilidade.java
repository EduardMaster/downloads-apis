package Zey.PvP.Eventos;

import org.bukkit.event.*;

import Zey.PvP.Main.*;

import java.util.*;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.block.*;

public class Habilidade implements Listener
{
    public static Map<String, String> powerMap;
    
    static {
        Habilidade.powerMap = new HashMap<String, String>();
    }
    
    public Habilidade(final Main main) {
    }
    
    public static String NomeDoKit(final String original) {
        if (original.length() == 0) {
            return original;
        }
        return String.valueOf(original.substring(0, 1).toUpperCase()) + original.substring(1);
    }
    
    public static String getAbility(final Player player) {
        if (!Habilidade.powerMap.containsKey(player.getName())) {
            Habilidade.powerMap.put(player.getName(), "Nenhum");
        }
        return Habilidade.powerMap.get(player.getName());
    }
    
    public static void setAbility(final Player player, final String ability) {
        Habilidade.powerMap.put(player.getName(), ability);
    }
    
    public static void removeAbility(final Player p) {
        Habilidade.powerMap.remove(p.getName());
    }
    
    public static void strikeLightning(final Entity p) {
        final Location coords = p.getLocation();
        coords.getWorld().strikeLightningEffect(coords);
        final Block block = coords.getBlock();
        block.setType(Material.AIR);
    }
}
