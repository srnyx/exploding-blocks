package xyz.srnyx.explodingblocks;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class EbCommand implements TabExecutor {
    public static boolean enabled = false;

    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command cmd, @NotNull String label, String[] args) {
        final Player player = (Player) sender;

        if (args.length == 0 && player.hasPermission("eb.toggle")) {
            if (enabled) {
                enabled = false;
                player.sendMessage("Exploding blocks are now disabled.");
            } else {
                enabled = true;
                player.sendMessage("Exploding blocks are now enabled.");
            }
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start") && player.hasPermission("eb.start")) {
                enabled = true;
                player.sendMessage("Exploding blocks are now enabled.");
                return true;
            }

            if (args[0].equalsIgnoreCase("stop") && player.hasPermission("eb.stop")) {
                enabled = false;
                player.sendMessage("Exploding blocks are now disabled.");
                return true;
            }
        }

        return false;
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command cmd, @NotNull String label, String[] args) {
        final List<String> suggestions = new ArrayList<>();
        final List<String> results = new ArrayList<>();

        if (args.length == 1) {
            suggestions.add("start");
            suggestions.add("stop");
        }

        for (final String suggestion : suggestions) if (suggestion.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) results.add(suggestion);
        return results;
    }
}
