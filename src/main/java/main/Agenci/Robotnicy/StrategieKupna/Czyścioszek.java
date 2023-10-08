package main.Agenci.Robotnicy.StrategieKupna;

import main.Agenci.Robotnicy.Robotnik;
import main.Oferty.OfertaRobotnika;
import main.Produkty.Produkt;
import main.Produkty.Ubranie;

import java.util.TreeMap;

public class Czyścioszek extends Technofob{

    @Override
    public String dajNazwę() {
        return "czyscioszek";
    }

    @Override
    public void kupuj() {
        super.kupuj();
        Robotnik robotnik = getRobotnik();
        int zliczDobreUbrania = 0;
        int zliczKiepskieUbrania = 0;
        TreeMap<Produkt, Integer> ubrania = getRobotnik().getProdukty(3);
        for (Produkt ubranie: ubrania.keySet()) {
            int ile = ubrania.get(ubranie);
            if (ubranie.getPoziom() == 1) {
                zliczKiepskieUbrania += ile;
            } else {
                zliczDobreUbrania += ile;
            }
            if (2 * zliczDobreUbrania + zliczKiepskieUbrania >= 200) {
                break;
            }
        }
        if (2 * zliczDobreUbrania + zliczKiepskieUbrania < 200) {
            OfertaRobotnika oferta = new OfertaRobotnika(new Ubranie(1),
                    200 - (2 * zliczDobreUbrania + zliczKiepskieUbrania), robotnik);
            robotnik.getGiełda().dodajKupnoRobotnika(oferta);
        }
    }
}
