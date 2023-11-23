package fr.dieuours.risingofdungeons.dungeon;

import fr.dieuours.risingofdungeons.Factory;
import fr.dieuours.risingofdungeons.Grade;
import fr.dieuours.risingofdungeons.chest.ChestFactory;
import fr.dieuours.risingofdungeons.quest.QuestFactory;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DungeonFactory extends Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(length = 1000)
    private String caption;
    @Enumerated(EnumType.STRING)
    private Grade grade, requiredGrade;
    private int winLevel, loseLevel;
    @Column(name = "can_replay")
    private boolean unique;
    private boolean canLoseLevel;
    private int numberOfFailsToLoseLevel;
    @OneToMany
    private Set<ChestFactory> chestFactorySet;
    @OneToMany
    private Set<QuestFactory> questFactorySet;

    public DungeonFactory() {
        this.chestFactorySet = new HashSet<>();
        this.questFactorySet = new HashSet<>();
    }


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DungeonFactory setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getCaption() {
        return this.caption;
    }

    @Override
    public DungeonFactory setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    @Override
    public Grade getRequiredGrade() {
        return this.requiredGrade;
    }

    @Override
    public DungeonFactory setRequiredGrade(Grade grade) {
        this.requiredGrade = grade;
        return this;
    }

    @Override
    public Grade getGrade() {
        return this.grade;
    }

    @Override
    public DungeonFactory setGrade(Grade grade) {
        this.grade = grade;
        return this;
    }

    @Override
    public int getWinLevel() {
        return this.winLevel;
    }

    @Override
    public DungeonFactory setWinLevel(int winLevel) {
        this.winLevel = winLevel;
        return this;
    }

    @Override
    public int getLoseLevel() {
        return this.loseLevel;
    }

    @Override
    public DungeonFactory setLoseLevel(int loseLevel) {
        this.loseLevel = loseLevel;
        return this;
    }

    @Override
    public boolean isUnique() {
        return unique;
    }

    @Override
    public DungeonFactory setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }

    public boolean canLoseLevel() {
        return canLoseLevel;
    }

    @Override
    public DungeonFactory setCanLoseLevel(boolean canLoseLevel) {
        this.canLoseLevel = canLoseLevel;
        return this;
    }

    @Override
    public int getNumberOfFailsToLoseLevel() {
        return numberOfFailsToLoseLevel;
    }

    @Override
    public DungeonFactory setNumberOfFailsToLoveLevel(int number) {
        this.numberOfFailsToLoseLevel = number;
        return this;
    }

    public Set<ChestFactory> getChestSet() {
        return chestFactorySet;
    }

    public DungeonFactory addChest(ChestFactory chestFactory) {
        this.chestFactorySet.add(chestFactory);
        return this;
    }

    public DungeonFactory removeChest(ChestFactory chestFactory) {
        this.chestFactorySet.remove(chestFactory);
        return this;
    }

    public DungeonFactory addQuest(QuestFactory quest) {
        this.questFactorySet.add(quest);
        return this;
    }
}
