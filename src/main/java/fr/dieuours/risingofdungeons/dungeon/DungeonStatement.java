package fr.dieuours.risingofdungeons.dungeon;

import fr.dieuours.risingofdungeons.Account;
import fr.dieuours.risingofdungeons.RisingOfDungeons;
import fr.dieuours.risingofdungeons.Statement;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DungeonStatement implements Statement<DungeonFactory> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne//todo verif is good all @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;
    @ManyToOne
    @JoinColumn(nullable = false)
    private DungeonFactory dungeonFactory;
    private LocalDateTime startDate, endDate;
    @Transient
    private DungeonRunnable dungeonRunnable;
    private boolean complete;

    @Override
    public void start() {
        this.startDate = LocalDateTime.now();
        this.dungeonRunnable = new DungeonRunnable(this);
        this.dungeonRunnable.run();
        RisingOfDungeons.getEntityManager().persist(this);
        RisingOfDungeons.getEntityManager().flush();
    }

    @Override
    public void complete() {
        this.getAccount().addLevel(this.getFactory().getWinLevel());
        RisingOfDungeons.getEntityManager().persist(this.getAccount());
        RisingOfDungeons.getEntityManager().flush();
        this.complete = true;
        stop();
    }

    @Override
    public void giveUp() {
        if (this.getFactory().canLoseLevel())
            this.getAccount().removeLevel(this.getFactory().getLoseLevel());
        RisingOfDungeons.getEntityManager().persist(this.getAccount());
        RisingOfDungeons.getEntityManager().flush();
        this.complete = false;
        stop();
    }

    @Override
    public void stop() {
        this.endDate = LocalDateTime.now();
        RisingOfDungeons.getEntityManager().persist(this);
        RisingOfDungeons.getEntityManager().flush();
    }

    @Override
    public void resume() {
        //TODO
    }

    @Override
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    @Override
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    @Override
    public DungeonRunnable getRunnable() {
        return this.dungeonRunnable;
    }

    @Override
    public boolean haveComplete() {
        return this.complete;
    }

    @Override
    public DungeonFactory getFactory() {
        return this.dungeonFactory;
    }

    public Account getAccount() {
        return account;
    }

    public DungeonStatement setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DungeonStatement setDungeonFactory(DungeonFactory dungeonFactory) {
        this.dungeonFactory = dungeonFactory;
        return this;
    }
}
