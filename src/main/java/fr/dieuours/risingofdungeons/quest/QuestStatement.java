package fr.dieuours.risingofdungeons.quest;

import fr.dieuours.risingofdungeons.Account;
import fr.dieuours.risingofdungeons.RisingOfDungeons;
import fr.dieuours.risingofdungeons.Statement;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class QuestStatement implements Statement<QuestFactory> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne//todo verif is good all @ManyToOne
    private Account account;
    @ManyToOne
    private QuestFactory quest;
    private LocalDateTime startDate, endDate;
    @Transient
    private QuestRunnable questRunnable;
    private boolean complete;

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public QuestStatement setAccount(Account account) {
        this.account = account;
        return this;
    }

    public QuestStatement setQuest(QuestFactory quest) {
        this.quest = quest;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public QuestRunnable getRunnable() {
        return this.questRunnable;
    }

    @Override
    public boolean haveComplete() {
        return this.complete;
    }

    @Override
    public QuestFactory getFactory() {
        return this.quest;
    }

    @Override
    public void start() {
        this.startDate = LocalDateTime.now();
        this.questRunnable = new QuestRunnable(this);
        this.questRunnable.run();
        RisingOfDungeons.getEntityManager().persist(this);
        RisingOfDungeons.getEntityManager().flush();
    }

    @Override
    public void complete() {
        this.account.addLevel(this.quest.getWinLevel());
        RisingOfDungeons.getEntityManager().persist(this.account);
        RisingOfDungeons.getEntityManager().flush();
        this.complete = true;
        stop();
        System.out.println(this.getFactory().getWinLevel());
    }

    @Override
    public void giveUp() {
        if (this.quest.canLoseLevel()) {
            this.account.removeLevel(this.quest.getLoseLevel());
            RisingOfDungeons.getEntityManager().persist(this.account);
            RisingOfDungeons.getEntityManager().flush();
        }
        this.complete = false;
        stop();
        System.out.println(this.getFactory().canLoseLevel() + " | " + this.getFactory().getLoseLevel());
    }

    @Override
    public void stop() {
        this.endDate = LocalDateTime.now();
        //        this.questRunnable.cancel();//TODO:uncomment
        RisingOfDungeons.getEntityManager().persist(this);
        RisingOfDungeons.getEntityManager().flush();
        System.out.println(this.questRunnable.getPassedTime());
    }

    @Override
    public void resume() {
        //TODO
    }
}
