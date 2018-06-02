/*    */ package neero.demon.kits;
/*    */ 
/*    */ import java.util.ArrayList;

import neero.demon.eventos.KitAPI;
import neero.demon.utils.Scoreboard;

/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ 
/*    */ public class Neo
/*    */   implements Listener, CommandExecutor
/*    */ {
/* 26 */   public static ArrayList<Player> neo = new ArrayList();
/*    */   
/*    */   @EventHandler
/*    */   public void deathe(PlayerDeathEvent e) {
/* 30 */     Player p = e.getEntity();
/*    */     
/* 32 */     neo.remove(p);
/*    */   }
/*    */   
/*    */   @EventHandler
/* 36 */   public void ent(PlayerJoinEvent e) { Player p = e.getPlayer();
/*    */     
/* 38 */     neo.remove(p);
/*    */   }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 43 */     Player jogador = (Player)sender;
/* 44 */     if (label.equalsIgnoreCase("neo")) {
/* 45 */       if (jogador.hasPermission("kit.neo"))
/*    */       {
	/*  56 */         if (!(KitAPI.getkit(jogador) == ("Nenhum")))
/*    */         {
/* 49 */           jogador.sendMessage("�4�lKIT �cVoce ja selecionou um kit !");
/*    */         }
/*    */         else
/*    */         {
/* 53 */           giveKit(jogador);
/*    */         }
/*    */         
/*    */       }
/*    */       else {
/* 58 */         jogador.sendMessage(ChatColor.RED + "�4�lKIT �cVoce nao possue este kit !");
/*    */       }
/*    */     }
/* 61 */     return false;
/*    */   }
/*    */   
/*    */   public void giveKit(Player p)
/*    */   {
/* 66 */     p.getInventory().clear();
/* 67 */     p.sendMessage(ChatColor.GREEN + "�4�lKIT �7Voce escolheu �cNeo!");
KitAPI.setKit(p, "Neo");
Scoreboard.iscoriboard(p);
/* 68 */     ItemStack sword = new ItemStack(Material.STONE_SWORD);
/* 69 */     p.getInventory().addItem(new ItemStack[] { sword });
KitAPI.darsopa(p);
/* 74 */     neo.add(p);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void snowball(EntityDamageByEntityEvent e)
/*    */   {
/* 80 */     if (((e.getDamager() instanceof Projectile)) && (neo.contains(e.getEntity()))) {
/* 81 */       e.setCancelled(true);
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onNeoFall(EntityDamageEvent e)
/*    */   {
/* 88 */     if ((e.getEntity() instanceof Player))
/*    */     {
/* 90 */       if (!neo.contains(e.getEntity())) {
/* 91 */         return;
/*    */       }
/* 93 */       if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
/* 94 */         return;
/*    */       }
/* 96 */       e.setDamage(e.getDamage() / 2.0D);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Infinite Sea\Desktop\Meus plugins\JaapaPvP.jar!\com\Nerio\net\kits\Neo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */