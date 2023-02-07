package me.evanskistudios.events;

import me.evanskistudios.eatingtheskullsofmyenemies;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class ListenerPlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();
        LivingEntity LivingPlayer = (LivingEntity) player;
        Entity killer = LivingPlayer.getKiller();

        Plugin plugin = eatingtheskullsofmyenemies.getPlugin(eatingtheskullsofmyenemies.class);
        String DropSkulls = "" + plugin.getConfig().get("Drop_Skulls_On_Player_Kills_Only");
        if (DropSkulls.equalsIgnoreCase("True")) {


            //Only on player kills
            if (killer != null) {

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
    }
}
