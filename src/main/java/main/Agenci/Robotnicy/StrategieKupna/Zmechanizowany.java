package main.Agenci.Robotnicy.StrategieKupna;

import main.Oferty.OfertaRobotnika;
import main.Produkty.Narzędzie;
import com.google.gson.annotations.SerializedName;

public class Zmechanizowany extends Czyścioszek {
    @SerializedName("liczba_narzedzi")
    int liczbaNarzędzi;

    @Override
    public String dajNazwę() {
        return "zmechanizowany";
    }

    public int getLiczbaNarzędzi() {
        return liczbaNarzędzi;
    }

    public Zmechanizowany (int liczbaNarzędzi) {
        this.liczbaNarzędzi = liczbaNarzędzi;
    }

    @Override
    public void kupuj() {
        super.kupuj();
        OfertaRobotnika oferta = new OfertaRobotnika(new Narzędzie(1), liczbaNarzędzi, getRobotnik());
        getRobotnik().getGiełda().dodajKupnoRobotnika(oferta);
    }
}
