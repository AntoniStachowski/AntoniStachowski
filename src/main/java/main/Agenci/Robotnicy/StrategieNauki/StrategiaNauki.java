package main.Agenci.Robotnicy.StrategieNauki;

import main.Agenci.Robotnicy.Robotnik;

public abstract class StrategiaNauki {
    private transient Robotnik robotnik;

    public abstract String dajNazwę();

    public void setRobotnik(Robotnik robotnik) {
        this.robotnik = robotnik;
    }

    public Robotnik getRobotnik() {
        return robotnik;
    }

    public abstract boolean czyNauka();
}
