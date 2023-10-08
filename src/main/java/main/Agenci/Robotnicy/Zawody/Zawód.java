package main.Agenci.Robotnicy.Zawody;

public abstract class Zawód {
    private int poziom;

    protected Zawód(){
        poziom = 1;
    }

    public int getPoziom() {
        return poziom;
    }

    public abstract String dajNazwę();

    public void setPoziom(int poziom) {
        this.poziom = poziom;
    }

    public void zwiększPoziom() {
        poziom++;
    }

    public int dajPoziomProduktu(int id) {
        if(id == 3 && dajID() == 3) {
            return poziom;
        }
        return 1;
    }

    public double premiaTypu(int id){
        if (dajID() != id) {
            return 1;
        }
        if (poziom == 1) {
            return 1.5;
        }
        if (poziom == 2) {
            return 2.5;
        }
        return 4 + 0.25 * (poziom-3);
    }

    public abstract int dajID();
}
