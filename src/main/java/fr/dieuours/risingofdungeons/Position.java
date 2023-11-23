package fr.dieuours.risingofdungeons;

import fr.dieuours.risingofdungeons.chest.ChestPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public abstract class Position implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int x,y,z;
    private float position;

    public Long getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public Position setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Position setY(int y) {
        this.y = y;
        return this;
    }

    public int getZ() {
        return z;
    }

    public Position setZ(int z) {
        this.z = z;
        return this;
    }

    public float getPosition() {
        return position;
    }

    public Position setPosition(float position) {
        this.position = position;
        return this;
    }

    public abstract Position build();
}
