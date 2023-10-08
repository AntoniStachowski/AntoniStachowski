package main.Agenci.Robotnicy.StrategieNauki;

import com.google.gson.annotations.SerializedName;

public class Oszczędny extends StrategiaNauki {
    @SerializedName("limit_diamentow")
    private double limitDiamentów;

    @Override
    public String dajNazwę() {
        return "oszczedny";
    }

    public Oszczędny(double limitDiamentów) {
        super();
        this.limitDiamentów = limitDiamentów;
    }

    @Override
    public boolean czyNauka() {
        return getRobotnik().getDiamenty() > limitDiamentów;
    }
}
