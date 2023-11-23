package fr.dieuours.risingofdungeons;

/**
 * The enum Grade.
 */
public enum Grade {
    /**
     * Ss grade.
     */
    SS(0x000000, null, 350),
    /**
     * S grade.
     */
    S(0x000000, SS, 300),
    /**
     * A grade.
     */
    A(0x000000, S, 200),
    /**
     * B grade.
     */
    B(0x000000, A, 150),
    /**
     * C grade.
     */
    C(0x000000, B, 150),
    /**
     * D grade.
     */
    D(0x000000, C, 150),
    /**
     * E grade.
     */
    E(0x000000, D, 100),
    /**
     * F grade.
     */
    F(0x000000, E);

    private final int color, minLevelToUpgrade;
    private final Grade nextGrade;

    Grade(int color, Grade nextGrade) {
        this.color = color;
        this.nextGrade = nextGrade;
        this.minLevelToUpgrade = 0;
    }

    Grade(int color, Grade nextGrade, int minLevelToUpgrade) {
        this.color = color;
        this.nextGrade = nextGrade;
        this.minLevelToUpgrade = minLevelToUpgrade;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public int getColor() {
        return this.color;
    }

    public int getMinLevelToUpgrade() {
        return this.minLevelToUpgrade;
    }

    public Grade getNextGrade() {
        return this.nextGrade;
    }
}
