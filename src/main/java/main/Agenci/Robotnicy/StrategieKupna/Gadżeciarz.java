package main.Agenci.Robotnicy.StrategieKupna;

import main.Agenci.Robotnicy.Robotnik;
import main.Oferty.OfertaRobotnika;
import main.Produkty.Produkt;
import main.Produkty.ProgramKomputerowy;

import java.util.TreeMap;

public class Gadżeciarz extends Zmechanizowany{
    public Gadżeciarz(int liczbaNarzędzi) {
        super(liczbaNarzędzi);
    }

    @Override
    public String dajNazwę() {
        return "gadzeciarz";
    }

    @Override
    public void kupuj() {
        super.kupuj();
        Robotnik robotnik = getRobotnik();
        TreeMap<Produkt, Integer> programy = robotnik.getProdukty(2);
        int zliczProgramy = 0;

        for (Produkt program: programy.keySet()) {
            zliczProgramy += programy.get(program);
        }

        int ileKupić = robotnik.getDzisiejszaProdukcja() - zliczProgramy;

        if (ileKupić > 0) {
            OfertaRobotnika oferta = new OfertaRobotnika(new ProgramKomputerowy(1), ileKupić, robotnik);
            robotnik.getGiełda().dodajKupnoRobotnika(oferta);
        }
    }
}
