package main.Agenci.Robotnicy.StrategieZmianyŚcieżki;

public class Konserwatysta extends StrategiaZmianyŚcieżki{

    @Override
    public String dajNazwę() {
        return "konserwatysta";
    }

    @Override
    public int zmianaŚcieżki() {
        return -1;
    }


}
