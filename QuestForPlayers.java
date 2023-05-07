package dev.jake.questforplayers;

import dev.jake.questforplayers.manager.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class QuestForPlayers extends JavaPlugin {
    private static QuestForPlayers instance;

    public static QuestForPlayers getInstance() {
        return instance;
    }

    private QuestsManager questsManager;

    public QuestsManager getQuestsManager() {
        return questsManager;
    }

    private QuestGUI questGUI;

    @Override
    public void onEnable() {
        instance = this;
        questsManager = new QuestsManager(Arrays.asList(
                new Quest("Рыбалка", Quest.QuestType.FISHING, 5),
                new Quest("Лесной убийца", Quest.QuestType.KILLING_MOBS, 10),
                new Quest("Настойящий шахтёр", Quest.QuestType.MINING, 20)));

        // регистрируем команды
        getServer().getPluginManager().registerEvents(new CheckCompleted(), this);
        getCommand("quest").setExecutor(new OpenQuestGUI());
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getLogger().info("Hello! Plugin for quests is enabled!");

    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye! Plugin for quests is disable");
    }
}
