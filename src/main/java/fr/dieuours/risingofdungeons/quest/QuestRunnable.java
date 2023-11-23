package fr.dieuours.risingofdungeons.quest;

import fr.dieuours.risingofdungeons.BukkitRunnable;

import java.time.LocalDateTime;

/**
 * The type Quest runnable.
 */
//todo:extends BukkitRunnable
public class QuestRunnable extends BukkitRunnable {

    private final QuestStatement quest;
    private long passedSeconds = 0;

    /**
     * Instantiates a new Quest runnable.
     *
     * @param quest the quest
     */
    public QuestRunnable(QuestStatement quest) {
        this.quest = quest;
    }

    /**
     * Run.
     */
    public void run() {
        this.passedSeconds += 1;
    }

    /**
     * Gets passed time.
     *
     * @return the passed time
     */
    public LocalDateTime getPassedTime() {
        return quest.getStartDate().plusSeconds(this.passedSeconds);
    }
}
