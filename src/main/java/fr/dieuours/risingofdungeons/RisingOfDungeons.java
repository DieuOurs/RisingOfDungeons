package fr.dieuours.risingofdungeons;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dieuours.risingofdungeons.chest.ChestFactory;
import fr.dieuours.risingofdungeons.chest.ChestPosition;
import fr.dieuours.risingofdungeons.chest.ChestStatement;
import fr.dieuours.risingofdungeons.dungeon.DungeonFactory;
import fr.dieuours.risingofdungeons.dungeon.DungeonStatement;
import fr.dieuours.risingofdungeons.exception.PersistentEnumMigrationException;
import fr.dieuours.risingofdungeons.quest.QuestFactory;
import fr.dieuours.risingofdungeons.quest.QuestStatement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

/**
 * Rising of dungeons.
 */
public final class RisingOfDungeons {

    //todo:make final
    private static EntityManager em;

    public static void main(String[] args) throws PersistentEnumMigrationException {
        em = Persistence.createEntityManagerFactory("RisingOfDungeons").createEntityManager();

        Account account = new Account().setRank(Rank.PLAYER).setGrade(Grade.F);
        em.persist(account);
        em.flush();
        DungeonFactory dungeonFactory = new DungeonFactory()
                .setName("Le donjon")
                .setCaption("Hello world")
                .setGrade(Grade.F)
                .setRequiredGrade(Grade.F)
                .setUnique(false)
                .setWinLevel(1000);
        em.persist(dungeonFactory);
        em.flush();
        QuestFactory questFactory = new QuestFactory()
                .setName("La Quete")
                .setCaption("Hello another world")
                .setGrade(Grade.F)
                .setRequiredGrade(Grade.F)
                .setUnique(false)
                .setWinLevel(1000);
        em.persist(questFactory);
        em.flush();
        ChestFactory chestFactory = new ChestFactory();
        chestFactory.setChestPosition(new ChestPosition().setX(100).setY(20).setZ(30).setPosition(1.03f).build());
        em.persist(chestFactory);
        em.flush();
        dungeonFactory.addChest(chestFactory).addQuest(questFactory);
        em.persist(dungeonFactory);
        em.flush();
        DungeonStatement dungeonStatement = new DungeonStatement().setDungeonFactory(dungeonFactory).setAccount(account);
        dungeonStatement.start();
        System.out.println(dungeonFactory.getChestSet().size());
        System.out.println(dungeonStatement.getStartDate());
        QuestStatement questStatement = account.getLastQuestStatement(questFactory);
        QuestFactory questFactory1 = questStatement.getFactory();
    }

    public static EntityManager getEntityManager() {
        return em;
    }

    public void onEnable() {
        em = Persistence.createEntityManagerFactory("RisingOfDungeons").createEntityManager();
    }

    public void onDisable() {

    }
}
