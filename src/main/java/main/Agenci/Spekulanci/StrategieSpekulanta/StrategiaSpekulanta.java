package main.Agenci.Spekulanci.StrategieSpekulanta;

import main.Agenci.Spekulanci.Spekulant;

public abstract class StrategiaSpekulanta {
    private transient Spekulant spekulant;

    //TODO - poprawić accessy do transakcje dnia
    public abstract String dajNazwę();

    public Spekulant getSpekulant() {
        return spekulant;
    }

    public void setSpekulant(Spekulant spekulant) {
        this.spekulant = spekulant;
    }

    public abstract void handluj();
}
