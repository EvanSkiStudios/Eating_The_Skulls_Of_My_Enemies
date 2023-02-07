package me.evanskistudios;

import me.evanskistudios.events.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class eatingtheskullsofmyenemies extends JavaPlugin {

    private static eatingtheskullsofmyenemies plugin;
    //Getter for plugin instance
    public static eatingtheskullsofmyenemies getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //Player Death Event
        ListenerPlayerDeath PlayerDeathEvent = new ListenerPlayerDeath();
        this.getServer().getPluginManager().registerEvents(PlayerDeathEvent, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
