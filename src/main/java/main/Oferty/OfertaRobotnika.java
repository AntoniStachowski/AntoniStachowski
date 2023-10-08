package main.Oferty;

import main.Agenci.Robotnicy.Robotnik;
import main.Produkty.Produkt;

public class OfertaRobotnika extends Oferta{
    private Robotnik robotnik;

    public OfertaRobotnika (Produkt produkt, int liczba, Robotnik robotnik) {
        super(produkt, liczba);
        this.robotnik = robotnik;
    }

    public Robotnik getRobotnik() {
        return robotnik;
    }
}
