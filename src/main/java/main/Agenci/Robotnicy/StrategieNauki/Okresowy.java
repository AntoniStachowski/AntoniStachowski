package main.Agenci.Robotnicy.StrategieNauki;

import com.google.gson.annotations.SerializedName;

public class Okresowy extends StrategiaNauki{
    @SerializedName("okresowosc_nauki")
    private int okresowośćNauki;

    @Override
    public String dajNazwę() {
        return "okresowy";
    }

    public Okresowy(int okresowośćNauki) {
        super();
        this.okresowośćNauki = okresowośćNauki;
    }

    @Override
    public boolean czyNauka() {
        return getRobotnik().getGiełda().getDzień() % okresowośćNauki == 0;
    }
}
