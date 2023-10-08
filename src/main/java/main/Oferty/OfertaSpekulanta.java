package main.Oferty;

import main.Agenci.Spekulanci.Spekulant;
import main.Produkty.Produkt;

public class OfertaSpekulanta extends Oferta{
    private double cena;
    private Spekulant spekulant;

    public OfertaSpekulanta(Produkt produkt, int liczba, double cena, Spekulant spekulant) {
        super(produkt, liczba);
        this.cena = cena;
        this.spekulant = spekulant;
    }

    public double getCena() {
        return cena;
    }

    public Spekulant getSpekulant() {
        return spekulant;
    }
}

