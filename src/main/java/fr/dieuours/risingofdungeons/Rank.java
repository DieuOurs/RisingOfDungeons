package fr.dieuours.risingofdungeons;

import java.io.Serializable;

/**
 * The enum Rank.
 */
public enum Rank implements Serializable {

    /**
     * Admin rank.
     */
    ADMIN("Administrateur", "Admin", 0xffffff, 0),
    /**
     * Player rank.
     */
    PLAYER("Joueur", "Joueur", 0xffffff, 1);

    private final String fullName, name;
    private final int color, priority;

    Rank(String fullName, String name, int color, int priority) {
        this.fullName = fullName;
        this.name = name;
        this.color = color;
        this.priority = priority;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "fullName='" + fullName + '\'' +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", priority=" + priority +
                "} " + super.toString();
    }
}
