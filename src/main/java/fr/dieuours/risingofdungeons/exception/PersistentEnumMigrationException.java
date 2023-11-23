package fr.dieuours.risingofdungeons.exception;

/**
 * The type Persistent enum migration exception.
 */
@Deprecated
public class PersistentEnumMigrationException extends Exception {

    private final Type type;

    /**
     * Instantiates a new Persistent enum migration exception.
     *
     * @param type the type
     */
    public PersistentEnumMigrationException(Type type) {
        super("PersistentEnumMigrationException >> " + type.getMessage());
        this.type = type;
    }

    /**
     * Instantiates a new Persistent enum migration exception.
     *
     * @param s    the s
     * @param type the type
     */
    public PersistentEnumMigrationException(String s, Type type) {
        super("PersistentEnumMigrationException >> " + s + " " + type.getMessage());
        this.type = type;
    }

    /**
     * Get type type.
     *
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * The Exist in enum.
         */
        EXIST_IN_ENUM("gradeInDatabase exist in enum"),
        /**
         * The Dont have annotation.
         */
        DONT_HAVE_ANNOTATION("hasn't annotation EnumMigrate.class");

        /**
         * The S.
         */
        final String s;

        Type(String s) {
            this.s = s;
        }

        /**
         * Get message string.
         *
         * @return the string
         */
        public String getMessage() {
            return this.s;
        }
    }
}
