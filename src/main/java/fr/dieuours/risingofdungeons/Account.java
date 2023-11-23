package fr.dieuours.risingofdungeons;

import fr.dieuours.risingofdungeons.quest.QuestFactory;
import fr.dieuours.risingofdungeons.quest.QuestStatement;
import fr.dieuours.risingofdungeons.service.persistence.EnumToMigrate;
import fr.dieuours.risingofdungeons.util.AtomicObject;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * The type Account.
 */
@Entity
@EnumToMigrate
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private UUID uuid;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rank rank;
    private String ip;
    private LocalDateTime registerDate, lastConnection;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @OneToMany
    private Set<QuestStatement> questsOfPlayer;
    private int level;

    /**
     * Instantiates a new Account.
     */
    public Account() {
        //todo:delete this line
        this.uuid = UUID.randomUUID();

        this.registerDate = LocalDateTime.now();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets uuid.
     *
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Sets uuid.
     *
     * @param uuid the uuid
     * @return the uuid
     */
    public Account setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * Gets rank.
     *
     * @return the rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets rank.
     *
     * @param rank the rank
     * @return the rank
     */
    public Account setRank(Rank rank) {
        this.rank = rank;
        return this;
    }

    /**
     * Gets register date.
     *
     * @return the register date
     */
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    /**
     * Sets register date.
     *
     * @param registerDate the register date
     * @return the register date
     */
    public Account setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    /**
     * Gets last connection.
     *
     * @return the last connection
     */
    public LocalDateTime getLastConnection() {
        return lastConnection;
    }

    /**
     * Sets last connection.
     *
     * @param lastConnection the last connection
     * @return the last connection
     */
    public Account setLastConnection(LocalDateTime lastConnection) {
        this.lastConnection = lastConnection;
        return this;
    }

    /**
     * Gets punitions.
     *
     * @return the punitions
     */
    public List<Punition> getPunitions() {
        return RisingOfDungeons.getEntityManager().createQuery(
                        "SELECT p " +
                                "FROM Punition as p " +
                                "INNER JOIN Account ON p.punished = :account"
                        , Punition.class)
                .setParameter("account", this)
                .getResultList();
    }

    /**
     * Gets ip.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets ip.
     *
     * @param ip the ip
     * @return the ip
     */
    public Account setIp(String ip) {
        this.ip = ip;
        return this;
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Sets grade.
     *
     * @param grade the grade
     * @return the grade
     */
    public Account setGrade(Grade grade) {
        this.grade = grade;
        return this;
    }

    public boolean haveQuest(QuestFactory quest) {
        return getNumberPlayedQuest(quest) > 0;
    }

    public boolean haveCompleteQuest(QuestFactory quest){
        QuestStatement questStatement = getQueryQuestStatement(quest).getSingleResult();
        return questStatement.haveComplete();
    }

    public QuestStatement getLastQuestStatement(QuestFactory quest){
        assert haveQuest(quest) : "Le compte n'a pas effectué cette quête...";
        AtomicObject<QuestStatement> atomicObject = new AtomicObject<>();
        getQueryQuestStatement(quest).getResultList().stream().filter(x->x.getEndDate()==null).findFirst().ifPresent(atomicObject::set);
        if(atomicObject.get() == null){
            atomicObject.set(RisingOfDungeons.getEntityManager().createQuery(
                            "SELECT qs FROM QuestStatement as qs WHERE qs.quest.id=:quest_id ORDER BY endDate ASC", QuestStatement.class)
                    .setParameter("quest_id", quest.getId()).getSingleResult());
        }
        return atomicObject.get();
    }

    public int getNumberPlayedQuest(QuestFactory quest){
        return getQueryQuestStatement(quest).getResultList().size();
    }

    private TypedQuery<QuestStatement> getQueryQuestStatement(QuestFactory quest){
        return RisingOfDungeons.getEntityManager().createQuery(
                        "SELECT qs FROM QuestStatement as qs WHERE qs.quest.id=:quest_id", QuestStatement.class)
                .setParameter("quest_id", quest.getId());
    }

    public void addLevel(int level) {
        this.level += level;
        if (this.level >= grade.getMinLevelToUpgrade()) {
            this.grade = this.grade.getNextGrade();
            this.level = 0;
        }
    }

    public void removeLevel(int level) {
        this.level -= level;
    }
}