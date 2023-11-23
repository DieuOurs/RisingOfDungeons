package fr.dieuours.risingofdungeons.chest;

import fr.dieuours.risingofdungeons.Position;
import fr.dieuours.risingofdungeons.RisingOfDungeons;
import jakarta.persistence.*;
@Entity
public class ChestPosition extends Position {

    @OneToOne
    @Column(unique = true)
    private ChestFactory chestFactory;

    public ChestFactory getChestFactory() {
        return chestFactory;
    }

    public ChestPosition setChestFactory(ChestFactory chestFactory) {
        this.chestFactory = chestFactory;
        return this;
    }

    @Override
    public ChestPosition build() {
        RisingOfDungeons.getEntityManager().persist(this);
        RisingOfDungeons.getEntityManager().flush();
        return this;
    }

//    public ChestPosition fromLocation(Location location){
//     todo
//    }
}
