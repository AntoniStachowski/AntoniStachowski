package main.Agenci.Robotnicy.StrategieNauki;

import main.Symulacja;

public class Rozkładowy extends StrategiaNauki{


    @Override
    public String dajNazwę() {
        return "rozkladowy";
    }

    @Override
    public boolean czyNauka() {
        int dzień = getRobotnik().getGiełda().getDzień();
        return Symulacja.losowanie.nextDouble() < (double) 1 / (dzień + 3);
    }
}
