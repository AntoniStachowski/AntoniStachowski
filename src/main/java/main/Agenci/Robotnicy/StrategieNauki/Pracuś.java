package main.Agenci.Robotnicy.StrategieNauki;

public class Pracuś extends StrategiaNauki{

    @Override
    public String dajNazwę() {
        return "pracus";
    }

    @Override
    public boolean czyNauka() {
        return false;
    }
}
