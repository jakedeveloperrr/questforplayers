package dev.jake.questforplayers.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestsManager {

    private List<Quest> quests = new ArrayList<>();

    public QuestsManager(List<Quest> quests) {
        this.quests = quests;
    }
    
    // Делаем метод рандомных квестов при выполнении команды /quest

    public Quest getRandomQuest() {
        List<Quest> validQuests = new ArrayList<>();
        for (Quest quest : quests) {
            if (!quest.isCompleted(true)) {
                validQuests.add(quest);
            }
        }
        if (validQuests.isEmpty()) {
            return null;
        }
        int index = new Random().nextInt(validQuests.size());
        return validQuests.get(index);
    }
}
