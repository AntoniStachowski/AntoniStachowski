package main.Produkty;

public class Jedzenie extends Produkt{
    public Jedzenie() {
        super(0,1);
    }

    @Override
    public int dajID() {
        return 0;
    }

    public boolean czyUżywaProgramów() {
        return false;
    }
}
