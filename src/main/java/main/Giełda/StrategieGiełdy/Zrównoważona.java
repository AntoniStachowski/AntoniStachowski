package main.Giełda.StrategieGiełdy;

import main.Komparatory.KapitalistycznyKomparatorOfertRobotników;
import main.Komparatory.SocjalistycznyKomparatorOfertRobotników;
import main.Oferty.OfertaRobotnika;

import java.util.Comparator;

public class Zrównoważona implements StrategiaGiełdy{
    @Override
    public Comparator<OfertaRobotnika> dajKomparator(int dzień) {
        if (dzień % 2 == 0) {
            return new SocjalistycznyKomparatorOfertRobotników();
        } else {
            return new KapitalistycznyKomparatorOfertRobotników();
        }
    }
}
