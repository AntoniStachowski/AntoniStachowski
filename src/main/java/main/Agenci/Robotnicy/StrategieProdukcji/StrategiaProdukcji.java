package main.Agenci.Robotnicy.StrategieProdukcji;

import main.Agenci.Robotnicy.Robotnik;

public abstract class StrategiaProdukcji {
    private transient Robotnik robotnik;

    //TODO - poprawić w strategiach acessowanie transakcji produktów.

    public abstract String dajNazwę();

    public void setRobotnik(Robotnik robotnik) {
        this.robotnik = robotnik;
    }

    public Robotnik getRobotnik() {
        return robotnik;
    }

    public abstract int typProdukcji();
}
