package fr.dieuours.risingofdungeons.service.persistence;

import fr.dieuours.risingofdungeons.Account;
import fr.dieuours.risingofdungeons.Grade;
import fr.dieuours.risingofdungeons.exception.PersistentEnumMigrationException;
import fr.dieuours.risingofdungeons.quest.QuestFactory;
import jakarta.persistence.EntityManager;

import java.util.Arrays;

/**
 * Persistent enum migration.
 */
@Deprecated
public class PersistentEnumMigration {

    private static void migrateQuestGrade(EntityManager entityManager, String gradeInDatabase, Grade grade) throws PersistentEnumMigrationException {
        if (Arrays.stream(Grade.values()).anyMatch(x -> x.name().equalsIgnoreCase(gradeInDatabase)))
            throw new PersistentEnumMigrationException(PersistentEnumMigrationException.Type.EXIST_IN_ENUM);
        entityManager.createQuery("UPDATE quest SET grade=:grade WHERE grade=:previous_grade")
                .setParameter("grade", grade.name())
                .setParameter("previous_grade", gradeInDatabase)
                .executeUpdate();
        entityManager.flush();
    }

    private static void migrateAccountGrade(EntityManager entityManager, String gradeInDatabase, Grade grade) throws PersistentEnumMigrationException {
        if (Arrays.stream(Grade.values()).anyMatch(x -> x.name().equalsIgnoreCase(gradeInDatabase)))
            throw new PersistentEnumMigrationException(PersistentEnumMigrationException.Type.EXIST_IN_ENUM);
        entityManager.createQuery("UPDATE Account SET grade=:grade WHERE grade=:previous_grade")
                .setParameter("grade", grade.name())
                .setParameter("previous_grade", gradeInDatabase)
                .executeUpdate();
        entityManager.flush();
    }

    /**
     * Update enums.
     *
     * @param entityManager   the entity manager
     * @param gradeInDatabase the grade in database
     * @param grade           the grade
     * @param clazz           the clazz
     * @throws PersistentEnumMigrationException the persistent enum migration exception
     */
    public static void updateEnums(EntityManager entityManager, String gradeInDatabase, Grade grade, Class<?> clazz) throws PersistentEnumMigrationException {
        if (clazz.getAnnotation(EnumToMigrate.class) == null)
            throw new PersistentEnumMigrationException(clazz.toString(), PersistentEnumMigrationException.Type.DONT_HAVE_ANNOTATION);
        if (clazz.equals(Account.class)) {
            boolean b = entityManager.createQuery("SELECT a from Account as a WHERE a.grade=:grade")
                    .setParameter("grade", gradeInDatabase)
                    .getSingleResult() != null;
            if (b) migrateAccountGrade(entityManager, gradeInDatabase, grade);
        } else if (clazz.equals(QuestFactory.class)) {
            boolean b = entityManager.createQuery("SELECT q from quest as q WHERE q.grade=:grade")
                    .setParameter("grade", gradeInDatabase)
                    .getSingleResult() != null;
            if (b) migrateQuestGrade(entityManager, gradeInDatabase, grade);
        }
    }
}
