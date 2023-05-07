package dev.jake.questforplayers.manager;

import dev.jake.questforplayers.QuestForPlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class QuestGUI {
    private Player player;
    public Inventory questMenu = Bukkit.createInventory(null, 27, "Квесты");
    private Quest currentQuest;
    private Quest nextQuest;
    public static QuestGUI instance;

    public QuestGUI(Player player) {
        this.player = player;
        updateQuests();
        createGUI();
    }
    
    // создаём метод обновления заданий, который будет рабоать за счёт метода рандомных заданий
    private void updateQuests() {
        currentQuest = QuestForPlayers.getInstance().getQuestsManager().getRandomQuest();
        nextQuest = QuestForPlayers.getInstance().getQuestsManager().getRandomQuest();
    }
    
    // создаём меню
    private void createGUI() {
        ItemStack quest = new ItemStack(Material.EMERALD);
        ItemMeta qMeta = quest.getItemMeta();
        qMeta.setDisplayName("Текущее задание: " + currentQuest.getName());
        qMeta.setLore(Arrays.asList(
                ChatColor.WHITE + "Задача: " + currentQuest.getDescription(),
                ChatColor.GREEN + "Прогресс: " + currentQuest.getProgress() + " из " + currentQuest.maxProgress
        ));
        quest.setItemMeta(qMeta);

        ItemStack next = new ItemStack(Material.ARROW);
        ItemMeta metaNext = next.getItemMeta();
        metaNext.setDisplayName("Следующее задание");
        metaNext.setLore(Arrays.asList(
                ChatColor.WHITE + "Название следующего задания: " + nextQuest.getName(),
                ChatColor.WHITE + "Задача следующего задания: " + nextQuest.getDescription(),
                ChatColor.WHITE + "Максимальный прогресс: " + nextQuest.getMaxProgress()
        ));
        next.setItemMeta(metaNext);

        ItemStack cancel = new ItemStack(Material.BARRIER);
        ItemMeta metaCancel = cancel.getItemMeta();
        metaCancel.setDisplayName(ChatColor.RED + "Отменить задание");
        metaCancel.setLore(Arrays.asList(
                ChatColor.GRAY + "Нажав на эту кнопку ты отменишь задание: " + currentQuest.getName()
        ));
        cancel.setItemMeta(metaCancel);

        questMenu.setItem(11, quest);
        questMenu.setItem(22, cancel);
        questMenu.setItem(15, next);
    }
}
