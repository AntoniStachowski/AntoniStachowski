package main.Giełda.StrategieGiełdy;

import main.Komparatory.KapitalistycznyKomparatorOfertRobotników;
import main.Oferty.OfertaRobotnika;

import java.util.Comparator;

public class Kapitalistyczna implements StrategiaGiełdy{

    @Override
    public Comparator<OfertaRobotnika> dajKomparator(int dzień) {
        return new KapitalistycznyKomparatorOfertRobotników();
    }
}
