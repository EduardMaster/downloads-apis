/*    */ package nobody.kit;
/*    */ 
/*    */ import java.util.Random;

import nobody.eventos.KitAPI;

/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ public class Critical implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void dano(EntityDamageByEntityEvent e)
/*    */   {
/* 15 */     if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
/* 16 */       Player p = (Player)e.getEntity();
/* 17 */       Player d = (Player)e.getDamager();
/* 18 */       if (KitAPI.getkit(d) == "Critical") {
/* 19 */         Random r = new Random();
/* 20 */         int c = r.nextInt(100);
/* 21 */         if (c <= 30) {
/* 22 */           e.setDamage(e.getDamage() + 4.0D);
/* 23 */           p.getWorld().playEffect(p.getLocation(), org.bukkit.Effect.STEP_SOUND, Material.LAVA, 100);
/* 24 */           p.sendMessage("�7[�6�l!�7] �cAi... Voc� recebeu um golpe critical de " + d.getName() + "�c, deve ter doido");
/* 25 */           d.sendMessage("�7[�6�l!�7] �cVoc� deu um golpe critical em " + p.getName());
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Infinite Sea\Desktop\Meus plugins\MasterKits.jar!\Kits\Critical.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */