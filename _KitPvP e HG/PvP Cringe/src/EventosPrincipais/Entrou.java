package EventosPrincipais;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import APIs.TitleAPI;
import Comandos.Admin;
import Main.Main;
import Outros.ScoreBoard;
import Utils.KitAPI;
import Utils.WarpsAPI;
import me.confuser.barapi.BarAPI;

public class Entrou implements Listener {
	
	public static ItemStack kits;
	public static ItemMeta kitsmeta;
	public static ItemStack warps;
	public static ItemMeta warpsmeta;
	public static ItemStack click;
	public static ItemMeta clickmeta;
	public static ItemStack parkour;
	public static ItemMeta parkourmeta;
	public static ItemStack loja;
	public static ItemMeta lojameta;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	  public void Entrar(PlayerJoinEvent e)
	  {
	    final Player p = e.getPlayer();
	    Player[] arrayOfPlayer;
	    if (Admin.admin.contains(p.getName()))
	    {
	      int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
	      for (int i = 0; i < j; i++)
	      {
	        Player s = arrayOfPlayer[i];
	        if (s.hasPermission("cmd.admin")) {
	          s.showPlayer(p);
	        } else {
	          s.hidePlayer(p);
	        }
	      }
	    }
	    int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
	    for (int i = 0; i < j; i++)
	    {
	      Player s = arrayOfPlayer[i];
	      ScoreBoard.UpdateScore(s);
	    }
		p.setGameMode(GameMode.SURVIVAL);
		WarpsAPI.ir(p, "spawn");
		if (!Main.getInstace().stats.contains(p.getUniqueId() + "")) {
			Main.getInstace().stats.set(p.getUniqueId() + ".Kills", Integer.valueOf(0));
			Main.getInstace().stats.set(p.getUniqueId() + ".Deaths", Integer.valueOf(0));
			Main.getInstace().stats.set(p.getUniqueId() + ".Money", Integer.valueOf(100));
			Main.getInstace().loja.contains(p.getUniqueId() + "" );
			Main.getInstace().loja.set(p.getUniqueId() + ".Stomper", "false");
			Main.getInstace().loja.set(p.getUniqueId() + ".Phantom", "false");
			Main.getInstace().loja.set(p.getUniqueId() + ".Urgal", "false");
		    Main.getInstace().loja.set(p.getUniqueId() + ".Ninja", "false");
			Main.getInstace().loja.set(p.getUniqueId() + ".Poseidon", "false");
			Main.getInstace().save();
		}
 		ScoreBoard.UpdateScore(p);
        KitAPI.KitDelay.remove(p.getName());
		e.setJoinMessage(null);
		p.getInventory().clear();
		p.getInventory().setHelmet(new ItemStack(Material.AIR));
		p.getInventory().setChestplate(new ItemStack(Material.AIR));
		p.getInventory().setLeggings(new ItemStack(Material.AIR));
		p.getInventory().setBoots(new ItemStack(Material.AIR));
		p.getActivePotionEffects().clear();
		p.setLevel(0);
		TitleAPI.sendFullTitle(p, Integer.valueOf(25), Integer.valueOf(25), Integer.valueOf(25), "§e§lCRINGED§7§lKITS", "§fSeja Bem Vindo §f" + p.getName());
	   	p.sendMessage("§7Bem vindo §6§l" + p.getName() + " ");
	   	p.sendMessage("§7Este e um servidor de §c§lKitPvP");
	   	p.sendMessage("§7Bugs Deverao ser reportados a §e§lSTAFF");
	   	p.sendMessage("§7Divirta-se lembrando.. report hackers usando §d§l/REPORT");
	   	for (Player s: Bukkit.getOnlinePlayers()) {
			s.sendMessage("§7§b+§7 §c" + p.getName());
		}
	    BarAPI.setMessage(p, "§7§lBem vindo §a§l" + p.getName() + " §7§lao §e§LCRINGED§7§LKITS", 5);
	    
	    kits = new ItemStack(Material.STORAGE_MINECART);
	    kitsmeta = kits.getItemMeta();
	    kitsmeta.setDisplayName("§b§lKITS");
	    kits.setItemMeta(kitsmeta);
	    
	    warps = new ItemStack(Material.COMPASS);
	    warpsmeta = warps.getItemMeta();
	    warpsmeta.setDisplayName("§a§LWARPS");
	    warps.setItemMeta(warpsmeta);
	    
	    click = new ItemStack(Material.DIAMOND_SWORD);
	    clickmeta = click.getItemMeta();
	    clickmeta.setDisplayName("§9§LCLICK-TESTE");
	    click.setItemMeta(clickmeta);
	    
	    parkour = new ItemStack(Material.NAME_TAG);
	    parkourmeta = parkour.getItemMeta();
	    parkourmeta.setDisplayName("§6§LPARKOUR");
	    parkour.setItemMeta(parkourmeta);
	    
	    loja = new ItemStack(Material.DIAMOND);
	    lojameta = parkour.getItemMeta();
	    lojameta.setDisplayName("§1§LLOJA");
	    loja.setItemMeta(lojameta);
	    
	    p.getInventory().setItem(0, kits);
	    p.getInventory().setItem(2, warps);
	    p.getInventory().setItem(6, click);
	    p.getInventory().setItem(8, loja);
	    p.updateInventory();
	}
}