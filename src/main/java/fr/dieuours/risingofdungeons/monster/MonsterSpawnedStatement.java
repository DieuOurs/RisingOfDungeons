package fr.dieuours.risingofdungeons.monster;

import jakarta.persistence.*;

public class MonsterSpawnedStatement extends MonsterStatement {

    @OneToOne
    private MonsterSpawnPosition position;

    public MonsterSpawnPosition getPosition() {
        return position;
    }
}
