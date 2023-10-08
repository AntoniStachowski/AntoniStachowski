package main.Produkty;

public class Ubranie extends Produkt {
    public Ubranie(int poziom) {
        super(poziom, poziom * poziom);
    }

    public Ubranie(int poziom, int wytrzymałość) {
        super(poziom, wytrzymałość);
    }

    @Override
    public int dajID() {
        return 1;
    }

    public boolean czyUżywaProgramów() {
        return true;
    }
}
