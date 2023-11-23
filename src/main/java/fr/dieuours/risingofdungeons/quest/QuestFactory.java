package fr.dieuours.risingofdungeons.quest;

import fr.dieuours.risingofdungeons.Factory;
import fr.dieuours.risingofdungeons.Grade;
import fr.dieuours.risingofdungeons.service.persistence.EnumToMigrate;
import jakarta.persistence.*;

/**
 * The type Quest factory.
 */
@Entity(name = "Quest")
@EnumToMigrate
public class QuestFactory extends Factory {
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

    public QuestFactory() {
        this.unique = false;
        this.canLoseLevel = true;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public QuestFactory setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public QuestFactory setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    @Override
    public Grade getRequiredGrade() {
        return requiredGrade;
    }

    @Override
    public QuestFactory setRequiredGrade(Grade requiredGrade) {
        this.requiredGrade = requiredGrade;
        return this;
    }

    @Override
    public Grade getGrade() {
        return grade;
    }

    @Override
    public QuestFactory setGrade(Grade grade) {
        this.grade = grade;
        return this;
    }

    @Override
    public int getWinLevel() {
        return winLevel;
    }

    @Override
    public QuestFactory setWinLevel(int winLevel) {
        this.winLevel = winLevel;
        return this;
    }

    @Override
    public int getLoseLevel() {
        return loseLevel;
    }

    @Override
    public QuestFactory setLoseLevel(int loseLevel) {
        this.loseLevel = loseLevel;
        return this;
    }

    @Override
    public boolean isUnique() {
        return this.unique;
    }

    @Override
    public QuestFactory setUnique(boolean b) {
        this.unique = b;
        return this;
    }

    @Override
    public boolean canLoseLevel() {
        return this.canLoseLevel;
    }

    @Override
    public QuestFactory setCanLoseLevel(boolean b) {
        this.canLoseLevel = b;
        return this;
    }

    @Override
    public int getNumberOfFailsToLoseLevel() {
        return this.numberOfFailsToLoseLevel;
    }

    @Override
    public QuestFactory setNumberOfFailsToLoveLevel(int number) {
        this.numberOfFailsToLoseLevel = number;
        return this;
    }
}
