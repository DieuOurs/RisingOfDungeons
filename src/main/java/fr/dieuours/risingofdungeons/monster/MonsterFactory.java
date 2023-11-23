package fr.dieuours.risingofdungeons.monster;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class MonsterFactory implements Serializable {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }
}
