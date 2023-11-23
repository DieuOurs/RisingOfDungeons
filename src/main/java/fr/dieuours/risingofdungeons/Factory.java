package fr.dieuours.risingofdungeons;

import java.io.Serializable;

public abstract class Factory implements Serializable {
    public abstract Long getId();

    public abstract String getName();

    public abstract Factory setName(String name);

    public abstract String getCaption();

    public abstract Factory setCaption(String caption);

    public abstract Grade getRequiredGrade();

    public abstract Factory setRequiredGrade(Grade grade);

    public abstract Grade getGrade();

    public abstract Factory setGrade(Grade grade);

    public abstract int getWinLevel();

    public abstract Factory setWinLevel(int winLevel);

    public abstract int getLoseLevel();

    public abstract Factory setLoseLevel(int loseLevel);

    public abstract boolean isUnique();

    public abstract Factory setUnique(boolean b);

    public abstract boolean canLoseLevel();

    public abstract Factory setCanLoseLevel(boolean b);

    public abstract int getNumberOfFailsToLoseLevel();

    public abstract Factory setNumberOfFailsToLoveLevel(int number);
}
