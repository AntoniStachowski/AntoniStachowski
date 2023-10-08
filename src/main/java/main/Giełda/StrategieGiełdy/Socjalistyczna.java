package main.Giełda.StrategieGiełdy;

import main.Komparatory.SocjalistycznyKomparatorOfertRobotników;
import main.Oferty.OfertaRobotnika;

import java.util.Comparator;

public class Socjalistyczna implements StrategiaGiełdy {

    @Override
    public Comparator<OfertaRobotnika> dajKomparator(int dzień) {
        return new SocjalistycznyKomparatorOfertRobotników();
    }
}
