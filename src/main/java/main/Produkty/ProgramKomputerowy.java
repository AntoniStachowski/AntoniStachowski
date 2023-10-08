package main.Produkty;

public class ProgramKomputerowy extends Produkt{
    public ProgramKomputerowy(int poziom) {
        super(poziom,1);
    }

    @Override
    public int dajID() {
        return 3;
    }

    public boolean czyUżywaProgramów() {
        return false;
    }
}
