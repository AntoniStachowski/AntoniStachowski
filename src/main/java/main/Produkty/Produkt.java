package main.Produkty;

public abstract class Produkt {
    private int poziom;
    private int wytrzymałość;

    protected Produkt(int poziom, int wytrzymałość) {
        this.poziom = poziom;
        this.wytrzymałość = wytrzymałość;
    }

    public abstract int dajID();

    public static boolean czyProgramy(int id) {
        return dajProdukt(id, 1).czyUżywaProgramów();
    }

    public abstract boolean czyUżywaProgramów();

    public int getPoziom() {
        return poziom;
    }

    public int getWytrzymałość() {
        return wytrzymałość;
    }

    public boolean jestTegoSamegoTypu (Produkt produkt) {
        return dajID() == produkt.dajID();
    }

    public boolean jestTakiSam (Produkt produkt) {
        return dajID() == produkt.dajID() && getPoziom() == produkt.getPoziom();
    }

    public static Produkt dajProdukt(int id, int poziom){
        switch (id) {
            case 0:
                return new Jedzenie();
            case 1:
                return new Ubranie(poziom);
            case 2:
                return new Narzędzie(poziom);
            default:
                return new ProgramKomputerowy(poziom);
        }
    }

}
