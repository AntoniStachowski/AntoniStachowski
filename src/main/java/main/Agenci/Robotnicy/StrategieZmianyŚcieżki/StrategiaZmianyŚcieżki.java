package main.Agenci.Robotnicy.StrategieZmianyŚcieżki;

import main.Agenci.Robotnicy.Robotnik;

public abstract class StrategiaZmianyŚcieżki {
    private transient Robotnik robotnik;

    public abstract String dajNazwę();

    public void setRobotnik(Robotnik robotnik) {
        this.robotnik = robotnik;
    }

    public Robotnik getRobotnik() {
        return robotnik;
    }

    public abstract int zmianaŚcieżki();
}
