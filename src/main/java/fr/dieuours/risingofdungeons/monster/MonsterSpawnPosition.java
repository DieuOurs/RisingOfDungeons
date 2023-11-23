package fr.dieuours.risingofdungeons.monster;

import fr.dieuours.risingofdungeons.Position;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class MonsterSpawnPosition extends Position {

    @OneToMany
    private List<MonsterSpawnedStatement> monsterSpawnedStatement;

    @Override
    public Position build() {
        return null;
    }
}
