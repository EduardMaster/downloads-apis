package Net.Hunger.Habilidades;

import Net.Hunger.Comandos.CMDAdmin;
import Net.Hunger.Comandos.CMDKit;
import Net.Hunger.Comandos.CMDListener;
import Net.Hunger.Main;
import Net.Hunger.Manager.Habilidade;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_7_R4.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Tower implements Listener {
	
  public boolean danonaterra = true;

  @EventHandler
  public void StomperFall(EntityDamageEvent event) {
    if ((event.getEntity() instanceof Player))
    {
      Player p = (Player)event.getEntity();
      if ((event.getCause() == EntityDamageEvent.DamageCause.FALL) && (Main.Partida) && (Habilidade.getAbility(p).equalsIgnoreCase("tower")))
      {
        List<Entity> entity = p.getNearbyEntities(8.0D, 5.0D, 8.0D);
        for (Entity en : entity) {
          if ((en instanceof Player))
          {
            Player stompados = (Player)en;
            if (stompados.isSneaking())
            {
              stompados.damage(4.0D);
              stompados.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
              p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
            } else {
                stompados.damage(p.getFallDistance() - 8);
              p.sendMessage("�7[�a!�7] Voc� esmagou �a" + stompados.getName());
              stompados.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
              p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
              if (stompados.isDead()) {
                CMDListener.setStreaks(p);
              }
            }
          }
        }
        if (event.getDamage() > 4.0D) {
          event.setDamage(4.0D);
        }
      }
    }
  }
  
  @EventHandler
  public void onDamage(BlockDamageEvent event)
  {
    Player p = event.getPlayer();
    if((Habilidade.getAbility(p).equalsIgnoreCase("tower")) && (event.getBlock().getType() == Material.DIRT))
    {
      double dist = event.getBlock().getLocation().distance(p.getWorld().getSpawnLocation());
      boolean drop = true;
      if (((CraftPlayer)p).getHealth() < 20.0D)
      {
        double hp = ((CraftPlayer)p).getHealth() + 1.0D;
        if (hp > 20.0D) {
          hp = 20.0D;
        }
        p.setHealth((int)hp);
        drop = false;
      }
      else if (p.getFoodLevel() < 20)
      {
        p.setFoodLevel(p.getFoodLevel() + 1);
        drop = false;
      }
      else
      {
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 1), false);
      }
      event.getBlock().getWorld().playEffect(event.getBlock().getLocation(), Effect.STEP_SOUND, Material.DIRT.getId());
      event.getBlock().setType(Material.AIR);
      if (drop) {
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation().add(0.5D, 0.0D, 0.5D), new ItemStack(Material.DIRT));
      }
    }
  }
  
  @EventHandler
  public void onDamage(EntityDamageEvent event) {
	 if(!(event.getEntity() instanceof Player)) {
     return;
	}
    if ((this.danonaterra) && (event.getCause() == EntityDamageEvent.DamageCause.FALL) && ((event.getEntity() instanceof Player)))
    {
      Player p = (Player)event.getEntity();
      if (Habilidade.getAbility(p).equals("tower"))
      {
        Location loc = event.getEntity().getLocation();
        boolean dirt = false;
        for (float x = -0.35F; (x <= 0.35F) && (!dirt); x += 0.35F) {
          for (float z = -0.35F; (z <= 0.35F) && (!dirt); z += 0.35F)
          {
            Block b = loc.clone().add(x, -1.0D, z).getBlock();
            if (b.getType() == Material.DIRT) {
              dirt = true;
            }
          }
        }
        if (dirt) {
          event.setCancelled(true);
        }
      }
    }
  }
}
