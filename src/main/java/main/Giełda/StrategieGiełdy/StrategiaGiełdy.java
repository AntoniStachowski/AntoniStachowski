package main.Giełda.StrategieGiełdy;

import main.Oferty.OfertaRobotnika;

import java.util.Comparator;

public interface StrategiaGiełdy {

    public Comparator<OfertaRobotnika> dajKomparator(int dzień);

}
