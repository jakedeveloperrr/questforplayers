package dev.jake.questforplayers.manager;

import dev.jake.questforplayers.QuestForPlayers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenQuestGUI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.openInventory(QuestGUI.instance.questMenu);
        }

        return false;
    }
}
