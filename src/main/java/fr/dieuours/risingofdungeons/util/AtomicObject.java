package fr.dieuours.risingofdungeons.util;

public class AtomicObject<T> {

    private T t;

    public void set(T t){
        this.t = t;
    }

    public T get(){
        return this.t;
    }
}
