package main.Agenci.Robotnicy.StrategieProdukcji;

import main.Symulacja;

public class Losowy extends StrategiaProdukcji{

    @Override
    public String dajNazwę() {
        return "losowy";
    }

    @Override
    public int typProdukcji() {
        return Symulacja.losowanie.nextInt(5) - 1;
    }
}
