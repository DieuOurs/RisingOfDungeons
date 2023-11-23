package fr.dieuours.risingofdungeons.monster;

import fr.dieuours.risingofdungeons.RisingOfDungeons;
import fr.dieuours.risingofdungeons.dungeon.DungeonFactory;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class MonsterStatement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private DungeonFactory dungeonFactory;
    @OneToOne
    private MonsterFactory monsterFactory;
    private boolean hasSpawn,isDead;

    public void spawn(){
        this.hasSpawn = true;
        this.isDead = false;
        RisingOfDungeons.getEntityManager().persist(this);
        RisingOfDungeons.getEntityManager().flush();
    }

    public boolean isSpawn(){
        return (hasSpawn && !isDead);
    }
}
