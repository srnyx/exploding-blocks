package xyz.srnyx.explodingblocks;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
    /**
     * Everything done when the plugin is enabling
     */
    @Override
    public void onEnable() {
        final PluginCommand command = Bukkit.getPluginCommand("eb");
        if (command != null) {
            command.setExecutor(new EbCommand());
            command.setTabCompleter(null);
        }

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
    }
}