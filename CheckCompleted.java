package dev.jake.questforplayers.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CheckCompleted implements Listener {
    private Map<UUID, Quest> questCompleted = new HashMap<>();
    
    // Создаём предмет награды, которую будем выдавать
    public ItemStack reward = new ItemStack(Material.EMERALD_BLOCK, 5);

    // Проверяем выполнение заданий
    @EventHandler
    public void checkQuestFishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        Quest quest = questCompleted.get(player.getUniqueId());
        if (quest != null && event.getCaught().getType() == EntityType.SALMON && quest.getType() == Quest.QuestType.FISHING) {
            int progress = quest.getProgress();
            if (progress < 5) {
                quest.setProgress(progress + 1);
                player.sendMessage(ChatColor.GREEN + "Круть! Вы поймали лосося, осталось ещё: " + quest.getMaxProgress());
            }
            else if (progress == 5){
                quest.isCompleted(true);
                player.sendMessage(ChatColor.GREEN + "Вы выполнили задание: " + quest.getName());
                player.getInventory().addItem(reward);
            }
        }
        else if (event.getCaught().getType() == EntityType.SALMON) {
            player.sendMessage(ChatColor.WHITE + "Мне нужен лосось!");
        }
    }

    @EventHandler
    public void checkQuestMining(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material block = event.getBlock().getType();
        Quest quest = questCompleted.get(player.getUniqueId());
        if (quest != null && block == Material.IRON_ORE && quest.getType() == Quest.QuestType.MINING) {
            int progress = quest.getProgress();
            if (progress < 20) {
                quest.setProgress(progress + 1);
                player.sendMessage(ChatColor.GREEN + "Мне требуется больше угля!");
            }
            else if (progress == 20) {
                quest.isCompleted(true);
                player.sendMessage(ChatColor.GREEN + "Ты выкопал нужно количество угля! Задание - " + quest.getName() + " выполнено!");
                player.getInventory().addItem(reward);
            }
        } else if (block != Material.IRON_ORE){
            player.sendMessage(ChatColor.RED + "Мне кажется, что стоит копать уголь..");
        }
    }
}
