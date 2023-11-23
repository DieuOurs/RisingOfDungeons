package fr.dieuours.risingofdungeons.dungeon;

import fr.dieuours.risingofdungeons.BukkitRunnable;

import java.time.LocalDateTime;

/**
 * The type Quest runnable.
 */
//todo:extends BukkitRunnable
public class DungeonRunnable extends BukkitRunnable {

    private final DungeonStatement dungeon;
    private long passedSeconds = 0;

    /**
     * Instantiates a new Quest runnable.
     *
     * @param dungeon the dungeon
     */
    public DungeonRunnable(DungeonStatement dungeon) {
        this.dungeon = dungeon;
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
        return dungeon.getStartDate().plusSeconds(this.passedSeconds);
    }
}
