package dev.jake.questforplayers.manager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GUIListener implements Listener {

    private Map<UUID, Quest> questCompleted = new HashMap<>();
    
    // Создаём слушатель события для кликов в инвенторе с заданиями
    @EventHandler
    public void clickInQuestMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Quest quest = questCompleted.get(player.getUniqueId());
        if (event.getView().getTitle().equals("Квесты")) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                case EMERALD:
                    player.closeInventory();
                case ARROW:
                    player.closeInventory();
                case BARRIER:
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Задание отменено!");
                    quest.isCompleted(false);
            }
        }
    }
}
