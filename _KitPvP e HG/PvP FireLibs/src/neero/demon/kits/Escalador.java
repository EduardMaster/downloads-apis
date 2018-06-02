/*     */ package neero.demon.kits;
/*     */ 
/*     */ /*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
import java.util.Map;

import neero.demon.Main;
import neero.demon.eventos.KitAPI;
import neero.demon.utils.Scoreboard;
/*     */ import net.minecraft.server.v1_7_R4.Item;
/*     */ import net.minecraft.server.v1_7_R4.Items;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;

/*     */ 
/*     */ public class Escalador
/*     */   implements Listener, CommandExecutor
/*     */ {
/*     */   public Main plugin;
/*     */   
/*     */   public Escalador(Main plugin)
/*     */   {
/*  41 */     this.plugin = plugin;
/*     */   }
/*     */   
/*  44 */   public static HashMap<Player, Entity> rod = new HashMap();
/*  45 */   public static HashMap<Player, Integer> falldmg = new HashMap();
/*  46 */   public static ArrayList<Player> escalador = new ArrayList();
/*  47 */   public static ArrayList<Entity> entity = new ArrayList();
/*     */   Map<Player, Cordinha> hooks = new java.util.HashMap();
/*     */   @EventHandler
/*     */   public void deathe(PlayerDeathEvent e) {
/*  51 */     Player p = e.getEntity();
/*     */     
/*  53 */     escalador.remove(p);
/*  54 */     falldmg.remove(p);
/*  55 */     rod.remove(p);
/*  56 */     entity.remove(p);
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*     */   {
/*  61 */     Player jogador = (Player)sender;
/*  62 */     if (label.equalsIgnoreCase("escalador")) {
/*  63 */       if (jogador.hasPermission("kit.escalador"))
/*     */       {
	/*  56 */         if (!(KitAPI.getkit(jogador) == ("Nenhum")))
/*     */         {
/*  67 */           jogador.sendMessage("�c�l�oVoce ja selecionou um kit !");
/*     */         }
/*     */         else
/*     */         {
/*  71 */           giveKit(jogador);
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/*  76 */         jogador.sendMessage(ChatColor.RED + "Voce nao possue este kit !");
/*     */       }
/*     */     }
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   public static void giveKit(Player p)
/*     */   {
/*  84 */     p.getInventory().clear();
/*  85 */     p.sendMessage(ChatColor.GREEN + "Voce escolheu o kit Escalador!");
KitAPI.setKit(p, "Grappler");
Scoreboard.iscoriboard(p);
/*  86 */     ItemStack sword = new ItemStack(Material.STONE_SWORD);
/*  87 */     p.getInventory().addItem(new ItemStack[] { sword });
/*  88 */     p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.LEASH) });
KitAPI.darsopa(p);
/*  93 */     escalador.add(p);
/*  94 */     falldmg.put(p, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSlot(PlayerItemHeldEvent e)
/*     */   {
/*  26 */     if (this.hooks.containsKey(e.getPlayer()))
/*     */     {
/*  28 */       ((Cordinha)this.hooks.get(e.getPlayer())).remove();
/*  29 */       this.hooks.remove(e.getPlayer());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void grapplerDamageNoLeash(EntityDamageEvent event)
/*     */   {
/*  36 */     if (!(event.getEntity() instanceof Player)) {
/*  37 */       return;
/*     */     }
/*  39 */     Player player = (Player)event.getEntity();
/*  40 */     if (event.getCause() != org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL) {
/*  41 */       return;
/*     */     }
/*  43 */     if ((this.hooks.containsKey(player)) && 
/*  44 */       (((Cordinha)this.hooks.get(player)).isHooked()) && 
/*  45 */       (event.getDamage() > 3.0D)) {
/*  46 */       event.setDamage(3.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onMove(PlayerMoveEvent e) {
/*  52 */     if ((this.hooks.containsKey(e.getPlayer())) && 
/*  53 */       (!e.getPlayer().getItemInHand().getType().equals(Material.LEASH))) {
/*  54 */       ((Cordinha)this.hooks.get(e.getPlayer())).remove();
/*  55 */       this.hooks.remove(e.getPlayer());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onLeash(PlayerLeashEntityEvent e)
/*     */   {
/*  62 */     Player p = e.getPlayer();
/*  63 */     if (e.getPlayer().getItemInHand().getType().equals(Material.LEASH))
/*     */     {
/*  65 */       e.setCancelled(true);
/*  66 */       e.getPlayer().updateInventory();
/*  67 */       e.setCancelled(true);
/*  68 */       if (!this.hooks.containsKey(p)) {
/*  69 */         return;
/*     */       }
/*  71 */       if (!((Cordinha)this.hooks.get(p)).isHooked()) {
/*  72 */         return;
/*     */       }
/*  74 */       double d = ((Cordinha)this.hooks.get(p)).getBukkitEntity()
/*  75 */         .getLocation().distance(p.getLocation());
/*  76 */       double t = d;
/*  77 */       double v_x = (1.0D + 0.07D * t) * (
/*  78 */         ((Cordinha)this.hooks.get(p)).getBukkitEntity()
/*  79 */         .getLocation().getX() - p.getLocation().getX()) / t;
/*  80 */       double v_y = (1.0D + 0.03D * t) * (
/*  81 */         ((Cordinha)this.hooks.get(p)).getBukkitEntity()
/*  82 */         .getLocation().getY() - p.getLocation().getY()) / t;
/*  83 */       double v_z = (1.0D + 0.07D * t) * (
/*  84 */         ((Cordinha)this.hooks.get(p)).getBukkitEntity()
/*  85 */         .getLocation().getZ() - p.getLocation().getZ()) / t;
/*     */       
/*  87 */       Vector v = p.getVelocity();
/*  88 */       v.setX(v_x);
/*  89 */       v.setY(v_y);
/*  90 */       v.setZ(v_z);
/*  91 */       p.setVelocity(v);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onClick(PlayerInteractEvent e)
/*     */   {
/*  98 */     Player p = e.getPlayer();
/*  99 */     if (e.getPlayer().getItemInHand().getType().equals(Material.LEASH))
/*     */     {
/* 101 */       e.setCancelled(true);
/* 102 */       if ((e.getAction() == Action.LEFT_CLICK_AIR) || 
/* 103 */         (e.getAction() == Action.LEFT_CLICK_BLOCK))
/*     */       {
/* 105 */         if (this.hooks.containsKey(p)) {
/* 106 */           ((Cordinha)this.hooks.get(p)).remove();
/*     */         }
/* 108 */         Cordinha nmsHook = new Cordinha(p.getWorld(), 
/* 109 */           ((CraftPlayer)p).getHandle());
/* 110 */         nmsHook.spawn(p.getEyeLocation().add(
/* 111 */           p.getLocation().getDirection().getX(), 
/* 112 */           p.getLocation().getDirection().getY(), 
/* 113 */           p.getLocation().getDirection().getZ()));
/* 114 */         nmsHook.move(p.getLocation().getDirection().getX() * 5.0D, p
/* 115 */           .getLocation().getDirection().getY() * 5.0D, p
/* 116 */           .getLocation().getDirection().getZ() * 5.0D);
/* 117 */         this.hooks.put(p, nmsHook);
/*     */       }
/*     */       else
/*     */       {
/* 121 */         if (!this.hooks.containsKey(p)) {
/* 122 */           return;
/*     */         }
/* 124 */         if (!((Cordinha)this.hooks.get(p)).isHooked()) {
/* 125 */           return;
/*     */         }
/* 127 */         double d = ((Cordinha)this.hooks.get(p))
/* 128 */           .getBukkitEntity().getLocation()
/* 129 */           .distance(p.getLocation());
/* 130 */         double t = d;
/* 131 */         double v_x = (1.0D + 0.2D * t) * (
/* 132 */           ((Cordinha)this.hooks.get(p))
/* 133 */           .getBukkitEntity().getLocation().getX() - p
/* 134 */           .getLocation().getX()) / t;
/* 135 */         double v_y = (1.0D + 0.03D * t) * (
/* 136 */           ((Cordinha)this.hooks.get(p))
/* 137 */           .getBukkitEntity().getLocation().getY() - p
/* 138 */           .getLocation().getY()) / t;
/* 139 */         double v_z = (1.0D + 0.2D * t) * (
/* 140 */           ((Cordinha)this.hooks.get(p))
/* 141 */           .getBukkitEntity().getLocation().getZ() - p
/* 142 */           .getLocation().getZ()) / t;
/*     */         
/* 144 */         Vector v = p.getVelocity();
/* 145 */         v.setX(v_x);
/* 146 */         v.setY(v_y);
/* 147 */         v.setZ(v_z);
/* 148 */         p.setVelocity(v);
/*     */       }
/*     */     }
/*     */   }
/*     */ }
