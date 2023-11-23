package fr.dieuours.risingofdungeons.chest;

import fr.dieuours.risingofdungeons.Account;
import fr.dieuours.risingofdungeons.RisingOfDungeons;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class ChestStatement implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ChestFactory chestFactory;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;
    private LocalDateTime openDate;
    private LocalDateTime afterDateCanReopen;
    private int openNumber;

    public void open() {
        if (openDate == null || chestFactory.canReopen()) {
            this.openNumber+=1;
            this.openDate = LocalDateTime.now();
            RisingOfDungeons.getEntityManager().persist(this);
            RisingOfDungeons.getEntityManager().flush();
            if(openNumber == 1 || (openNumber > 1 && chestFactory.canWinLevelWhenReopen())) {
                this.account.addLevel(chestFactory.getWinLevelOnOpen());
                RisingOfDungeons.getEntityManager().persist(this.account);
                RisingOfDungeons.getEntityManager().flush();
            }
        }
    }

    public void close() {
        RisingOfDungeons.getEntityManager().persist(this);
        RisingOfDungeons.getEntityManager().flush();
    }

    public Long getId() {
        return id;
    }

    public ChestFactory getChest() {
        return chestFactory;
    }

    public ChestStatement setChest(ChestFactory chestFactory) {
        this.chestFactory = chestFactory;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public ChestStatement setAccount(Account account) {
        this.account = account;
        return this;
    }

    public LocalDateTime getOpenDate() {
        if (this.chestFactory.canReopen()) this.afterDateCanReopen = LocalDateTime.now().plusDays(3);
        return openDate;
    }

    public LocalDateTime getAfterDateCanReopen() {
        return afterDateCanReopen;
    }

    public ChestStatement setAfterChestDateCanReopen(LocalDateTime localDateTime) {
        this.afterDateCanReopen = localDateTime;
        return this;
    }

    public int getOpenNumber() {
        return openNumber;
    }
}
