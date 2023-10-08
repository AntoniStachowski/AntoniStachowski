package main.Oferty;

import main.Produkty.Produkt;

public class Oferta {
    private Produkt produkt;
    private int liczba;

    protected Oferta(Produkt produkt, int liczba) {
        this.produkt = produkt;
        this.liczba = liczba;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public int getLiczba() {
        return liczba;
    }

    public void odejmij(int x) {
        liczba -= x;
    }
}
