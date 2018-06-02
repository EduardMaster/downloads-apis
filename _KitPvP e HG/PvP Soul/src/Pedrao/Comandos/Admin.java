package Pedrao.Comandos;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Pedrao.Main;

public class Admin
  implements Listener, CommandExecutor
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static ArrayList<String> admin = new ArrayList();
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<String, ItemStack[]> salvarinv = new HashMap();
  
  public Admin(Main main) {
}

@EventHandler
  public void onAdminAbririnv(PlayerInteractEntityEvent event)
  {
    if (!(event.getRightClicked() instanceof Player)) {
      return;
    }
    Player p1 = event.getPlayer();
    Player r = (Player)event.getRightClicked();
    if ((admin.contains(p1.getName())) && (p1.getItemInHand().getType() == Material.AIR)) {
      p1.openInventory(r.getInventory());
    }
  }
  
  @EventHandler
  public void onAdminFF(PlayerInteractEntityEvent event)
  {
    if (!(event.getRightClicked() instanceof Player)) {
      return;
    }
    Player p1 = event.getPlayer();
    Player r = (Player)event.getRightClicked();
    if ((admin.contains(p1.getName())) && (p1.getItemInHand().getType() == Material.ANVIL)) {
      p1.chat("/crash " + r.getName());
    }
  }
  
  @EventHandler
  public void onAdminCrash(PlayerInteractEntityEvent event)
  {
    if (!(event.getRightClicked() instanceof Player)) {
      return;
    }
    Player p1 = event.getPlayer();
    Player r = (Player)event.getRightClicked();
    if ((admin.contains(p1.getName())) && (p1.getItemInHand().getType() == Material.MUSHROOM_SOUP)) {
      p1.chat("/autosoup " + r.getName());
    }
  }
  
  @EventHandler
  public void onAdminNoFall(PlayerInteractEntityEvent event)
  {
    Player p = event.getPlayer();
    if ((admin.contains(p.getName())) && (p.getItemInHand().getType() == Material.FEATHER))
    {
      Player p1 = (Player)event.getRightClicked();
      if (p1 != null)
      {
        p1.getLocation().add(0.0D, 40.0D, 0.0D).getBlock().setType(Material.AIR);
        p1.getLocation().add(0.0D, 40.0D, 1.0D).getBlock().setType(Material.AIR);
        p1.getLocation().add(1.0D, 40.0D, 0.0D).getBlock().setType(Material.AIR);
        p1.getLocation().add(0.0D, 40.0D, -1.0D).getBlock().setType(Material.AIR);
        p1.getLocation().add(-1.0D, 40.0D, 0.0D).getBlock().setType(Material.AIR);
        p1.getLocation().add(0.0D, 40.0D, 0.0D).getBlock().setType(Material.AIR);
        p1.teleport(p1.getLocation().add(0.0D, 11.0D, -0.05D));
      }
    }
  }
  
  @EventHandler
  public void onAdminArena(PlayerInteractEntityEvent event)
  {
    Player p = event.getPlayer();
    if ((admin.contains(p.getName())) && (p.getItemInHand().getType() == Material.IRON_FENCE))
    {
      Player p1 = (Player)event.getRightClicked();
      if (p1 != null)
      {
        p1.getLocation().add(0.0D, 13.0D, 0.0D).getBlock().setType(Material.BEDROCK);
        p1.getLocation().add(0.0D, 11.0D, 1.0D).getBlock().setType(Material.BEDROCK);
        p1.getLocation().add(1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
        p1.getLocation().add(0.0D, 11.0D, -1.0D).getBlock().setType(Material.BEDROCK);
        p1.getLocation().add(-1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
        p1.getLocation().add(0.0D, 10.0D, 0.0D).getBlock().setType(Material.BEDROCK);
        p1.teleport(p1.getLocation().add(0.0D, 11.0D, -0.05D));
      }
    }
  }
  
  @EventHandler
  public void onPlayerInfo(PlayerInteractEntityEvent e)
  {
    Player p = e.getPlayer();
    if ((admin.contains(p.getName())) && (p.getItemInHand().getType() == Material.BOOK))
    {
      Player p1 = (Player)e.getRightClicked();
      Damageable hp = p1;
      p.sendMessage("�aInforma�oes do player �c�l" + p1.getName());
      p.sendMessage("�aVida: �c�l" + (int)hp.getHealth());
      p.sendMessage("�aSopas: �c�l" + getAmount(p1, Material.MUSHROOM_SOUP));
      p.sendMessage("�aGamemode: �c�l" + p1.getGameMode());
      @SuppressWarnings("unused")
	Player target = null;
      p.sendMessage("�aIp: �c�l" + p1.getAddress().getHostName());
    }
  }
  
  public static int getAmount(Player p, Material m)
  {
    int amount = 0;
    for (ItemStack item : p.getInventory().getContents()) {
      if ((item != null) && (item.getType() == m) && (item.getAmount() > 0)) {
        amount += item.getAmount();
      }
    }
    return amount;
  }
  
  @EventHandler
  public void aoInteragir(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if ((admin.contains(p.getName())) && (
      (e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)))
    {
      if (p.getItemInHand().getType() == Material.SLIME_BALL)
      {
        p.chat("/admin");
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
        {
          public void run()
          {
            p.chat("/admin");
          }
        }, 10L);
      }
    }
  }
  
  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage(Main.prefix + "�bVoce precisa estar online para dar o comando!");
      return true;
    }
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("admin"))
    {
      if (!p.hasPermission("kitpvp.admin")) {
        return true;
      }
      if (admin.contains(p.getName()))
      {
        Player[] arrayOfPlayer;
        int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
        for (int i = 0; i < j; i++)
        {
          Player all = arrayOfPlayer[i];
          
          all.showPlayer(p);
          if (all.hasPermission("kitpvp.admin")) {
          } else {
            all.sendMessage(" ");
          }
        }
        p.setGameMode(GameMode.CREATIVE);
        for (int i1 = 0; i1 < 10; i1++) {
          p.sendMessage(" ");
        }
        p.sendMessage(Main.prefix + "�7Modo Admin �c�lOFF");
        admin.remove(p.getName());
        p.getInventory().setContents((ItemStack[])salvarinv.get(p.getName()));
        p.updateInventory();
      }
      else
      {
        admin.add(p.getName());
        Player[] arrayOfPlayer;
        int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
        for (int i = 0; i < j; i++)
        {
          Player all = arrayOfPlayer[i];
          
          all.hidePlayer(p);
          if (all.hasPermission("kitpvp.admin")) {
          } else {
            all.sendMessage(" ");
          }
        }
        p.setGameMode(GameMode.CREATIVE);
        for (int i1 = 0; i1 < 10; i1++) {
          p.sendMessage("");
        }
        p.sendMessage(Main.prefix + "�7Modo Admin �a�lON");
        
        salvarinv.put(p.getName(), p.getInventory().getContents());
        p.getInventory().clear();
        darItem(p, Material.IRON_FENCE, "�7Criar ARENA", 9);
        darItem(p, Material.FEATHER, "�7Testar NOFALL", 1);
        darItem(p, Material.SLIME_BALL, "�7Troca Rapida", 5);
        darItem(p, Material.BOOK, "�7Info Player", 7);
        darItem(p, Material.MUSHROOM_SOUP, "�7Test Auto-Soup", 3);
      }
    }
    return true;
  }
  
  public void darItem(Player p, Material material, String nome, int slot)
  {
    ItemStack l = new ItemStack(material);
    ItemMeta metal = l.getItemMeta();
    metal.setDisplayName(nome);
    l.setItemMeta(metal);
    
    p.getInventory().setItem(slot - 1, l);
  }
}
