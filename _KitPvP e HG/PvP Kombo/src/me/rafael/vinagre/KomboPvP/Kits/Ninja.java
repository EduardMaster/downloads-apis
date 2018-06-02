package me.rafael.vinagre.KomboPvP.Kits;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.rafael.vinagre.KomboPvP.Main;
import me.rafael.vinagre.KomboPvP.Listeners.Array;

public class Ninja 
implements Listener, CommandExecutor
{
	  public static List<Player> cooldownbk = new ArrayList();
		public static HashMap<Player, Player> a = new HashMap();
		
		  @EventHandler
		  public void dano(EntityDamageByEntityEvent e)
		  {
		    if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player)))
		    {
			      Player p = (Player)e.getDamager();
			      Player p2 = (Player)e.getEntity();
		    	if(Array.kit.get(p) == "Ninja"){
		        a.put(p, p2);
		        return;
		      }
		    }
		  }
		  
		  @EventHandler
		  public void shift(PlayerToggleSneakEvent e){
		    final Player p = e.getPlayer();
		    Player p2 = a.get(p);
		    if(Array.kit.get(p) == "Ninja"){
		    	if(p2.isDead()){
		    		return;
		    	}
		    	if(cooldownbk.contains(p)){
		    		return;
		    	}
		          if (p2.getLocation().distance(p.getLocation()) < 120.0D){
		            p.teleport(p2.getLocation());
		            cooldownbk.add(p);
		            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						
						@Override
						public void run() {
							cooldownbk.remove(p);
						}
					}, 7 * 20L);
		            return;
		          }else{
		        	  p.sendMessage("§cO ultimo player hitado esta muito longe !");
		        	  return;
		          }
		    }
		  }
		  public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
		  {
		    Player p = (Player)sender;
		    ItemStack dima = new ItemStack(Material.STONE_SWORD);
		    ItemMeta souperaa = dima.getItemMeta();
		    souperaa.setDisplayName("§cEspada");
		    dima.setItemMeta(souperaa);
		    ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
		    ItemMeta sopas = sopa.getItemMeta();
		    sopas.setDisplayName("§6Sopa");
		    sopa.setItemMeta(sopas);
		    
		    ItemStack capacete0 = new ItemStack(Material.AIR);
		    
		    ItemStack peitoral0 = new ItemStack(Material.AIR);
		    
		    ItemStack calca0 = new ItemStack(Material.AIR);
		    
		    ItemStack Bota0 = new ItemStack(Material.AIR);
		    
		    p.getInventory().setHelmet(capacete0);
		    p.getInventory().setChestplate(peitoral0);
		    p.getInventory().setLeggings(calca0);
		    p.getInventory().setBoots(Bota0);
		    
		    if (cmd.equalsIgnoreCase("ninja")) {
		      if (Array.used.contains(p.getName())) {
		    	  p.sendMessage("§7» §cVoce ja esta usando um kit!");
		        return true;
		      }
		      if (!p.hasPermission("kitpvp.kit.ninja")) {
			      p.sendMessage("§cVoce nao tem permissao para usar este kit !");
		        return true;
		      }
		      Array.used.add(p.getName());
		      p.sendMessage("§7» Voce escolheu o kit §cNinja §7!");
		      p.setGameMode(GameMode.ADVENTURE);
		      p.getInventory().clear();
		      Array.kit.put(p, "Ninja");
		      Main.Dj.remove(p.getName());
		      Main.stomper.remove(p.getName());
		      Scoreboard.ScoreDoBasic.iscoriboard(p);
		     
		      p.getInventory().addItem(new ItemStack[] { dima });
		      Main.give(p);
		      for (int i = 0; i <= 34; i++) {
		        p.getInventory().addItem(new ItemStack[] { sopa });
		        
		      }
		    }
		    me.rafael.vinagre.KomboPvP.Comandos.SetArena.TeleportArenaRandom(p);
		    return false;
		  }
		}