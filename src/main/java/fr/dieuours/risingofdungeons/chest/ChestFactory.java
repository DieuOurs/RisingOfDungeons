package fr.dieuours.risingofdungeons.chest;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class ChestFactory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean canReopen;
    private boolean uniqueChest;
    private LocalDateTime poseDate;
    @OneToOne
    @Column(unique = true)
    private ChestPosition chestPosition;
    private int winLevelOnOpen;
    private boolean canWinLevelWhenReopen;

    public Long getId() {
        return id;
    }

    public LocalDateTime getPoseDate() {
        return poseDate;
    }

    public boolean canReopen() {
        return canReopen;
    }

    public ChestFactory setCanReopen(boolean canReopen) {
        this.canReopen = canReopen;
        return this;
    }

    public boolean isUniqueChest() {
        return uniqueChest;
    }

    public ChestFactory setUniqueChest(boolean uniqueChest) {
        this.uniqueChest = uniqueChest;
        return this;
    }

    public ChestPosition getChestPosition() {
        return chestPosition;
    }

    public ChestFactory setChestPosition(ChestPosition chestPosition) {
        this.chestPosition = chestPosition;
        return this;
    }

    public int getWinLevelOnOpen() {
        return winLevelOnOpen;
    }

    public ChestFactory setWinLevelOnOpen(int winLevelOnOpen) {
        this.winLevelOnOpen = winLevelOnOpen;
        return this;
    }

    public boolean canWinLevelWhenReopen() {
        return canWinLevelWhenReopen;
    }

    public ChestFactory setCanWinLevelWhenReopen(boolean canWinLevelWhenReopen) {
        this.canWinLevelWhenReopen = canWinLevelWhenReopen;
        return this;
    }

    public enum Rare {

    }
}
