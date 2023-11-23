package fr.dieuours.risingofdungeons;

import java.time.LocalDateTime;

public interface Statement<T extends Factory> {

    void start();

    /**
     * Complete.
     */
    void complete();

    /**
     * Give up.
     */
    void giveUp();

    /**
     * Stop.
     */
    void stop();

    void resume();

    LocalDateTime getStartDate();

    LocalDateTime getEndDate();

    //TODO rename to BukkitRunnable
    BukkitRunnable getRunnable();

    boolean haveComplete();

    T getFactory();

    Account getAccount();
}
