/*    */ package br.nikao.kits;
/*    */ 
/*    */ import br.nikao.outros.KitAPI;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ public class Urgal implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void hit(EntityDamageByEntityEvent e)
/*    */   {
/* 14 */     if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
/* 15 */       Player k = (Player)e.getDamager();
/* 16 */       if (KitAPI.getkit(k) == "Urgal") {
/* 17 */         int porcentage = 60;
/* 18 */         e.setDamage(e.getDamage() * porcentage / 100.0D);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Vini\Downloads\KitPvP.jar!\br\nikao\kits\Urgal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */