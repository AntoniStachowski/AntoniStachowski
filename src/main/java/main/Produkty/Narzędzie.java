package main.Produkty;

public class Narzędzie extends Produkt{
    public Narzędzie(int poziom) {
        super(poziom,1);
    }

    @Override
    public int dajID() {
        return 2;
    }

    public boolean czyUżywaProgramów() {
        return true;
    }
}
