package me.evanskistudios.events;

import me.evanskistudios.eatingtheskullsofmyenemies;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class ListenerPlayerDeath implements Listener {

    enum DeathType {
        Normal,
        Player
    }
    public DeathType DeathCause = DeathType.Normal;
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;

        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;

            if (event.getFinalDamage() >= player.getHealth()){
                Entity killer = event.getDamager();

                DeathCause = DeathType.Normal;

                if (killer instanceof Player) {
                    DeathCause = DeathType.Player;
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();

        Plugin plugin = eatingtheskullsofmyenemies.getPlugin(eatingtheskullsofmyenemies.class);
        String DropSkulls = "" + plugin.getConfig().get("Drop_Skulls_On_Player_Kills_Only");
        if (DropSkulls.equalsIgnoreCase("True")) {

            //Only on player kills
            if (DeathCause == DeathType.Player) {

                //Player head
                ItemStack PlayerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta PlayerHead_meta = (SkullMeta) PlayerHead.getItemMeta();
                PlayerHead_meta.setOwner(player.getName());

                PlayerHead.setItemMeta(PlayerHead_meta);

                player.getWorld().dropItem(player.getLocation().add(0, 1, 0), PlayerHead);
            }

            //not a player kill

        }else{
            //Config is false, so always drop head
            ItemStack PlayerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta PlayerHead_meta = (SkullMeta) PlayerHead.getItemMeta();
            PlayerHead_meta.setOwner(player.getName());

            PlayerHead.setItemMeta(PlayerHead_meta);

            player.getWorld().dropItem(player.getLocation().add(0, 1, 0), PlayerHead);
        }

        //reset so we are not stuck with last one cause
        DeathCause = DeathType.Normal;
    }
}
