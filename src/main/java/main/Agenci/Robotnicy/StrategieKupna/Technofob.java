package main.Agenci.Robotnicy.StrategieKupna;

import main.Oferty.OfertaRobotnika;
import main.Produkty.Jedzenie;

public class Technofob extends StrategiaKupna{

    @Override
    public String dajNazwę() {
        return "technofob";
    }

    @Override
    public void kupuj() {
        OfertaRobotnika oferta = new OfertaRobotnika(new Jedzenie(), 100,  getRobotnik());
        getRobotnik().getGiełda().dodajKupnoRobotnika(oferta);
    }
}
