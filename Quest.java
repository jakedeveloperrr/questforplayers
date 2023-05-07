package dev.jake.questforplayers.manager;

public class Quest {

    public String name;
    public String description;
    public int maxProgress;
    public int progress;
    public boolean completed = false;

    public QuestType type;

    public Quest(String name, QuestType type, int maxProgress) {
        this(name, "", maxProgress, 0, false, type);
    }


    public Quest(String name, String description, int maxProgress, int progress, boolean completed, QuestType type) {
        this.name = name;
        this.description = description;
        this.maxProgress = maxProgress;
        this.progress = progress;
        this.completed = completed;
        this.type = type;
    }

    public QuestType getType() {
        return type;
    }

    public void setType(QuestType type) {
        this.type = type;
    }

    public boolean isCompleted(boolean completed) {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public enum QuestType {
        FISHING, KILLING_MOBS, MINING
    }
}
