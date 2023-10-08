package main.Agenci.Robotnicy.StrategieKupna;

import main.Agenci.Robotnicy.Robotnik;

public abstract class StrategiaKupna {
    private transient Robotnik robotnik;

    public void setRobotnik(Robotnik robotnik) {
        this.robotnik = robotnik;
    }

    public Robotnik getRobotnik() {
        return robotnik;
    }

    public abstract String dajNazwę();

    public abstract void kupuj();
}
