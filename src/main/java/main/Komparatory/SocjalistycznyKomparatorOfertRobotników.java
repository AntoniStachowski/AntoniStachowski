package main.Komparatory;

import main.Agenci.Robotnicy.Robotnik;
import main.Oferty.OfertaRobotnika;

import java.util.Comparator;

public class SocjalistycznyKomparatorOfertRobotników implements Comparator<OfertaRobotnika> {
    @Override
    public int compare(OfertaRobotnika o1, OfertaRobotnika o2) {
        Robotnik rob1 = o1.getRobotnik();
        Robotnik rob2 = o2.getRobotnik();
        if (rob1.getDiamenty() == rob2.getDiamenty()) {
            return rob1.getId() - rob2.getId();
        }
        return (int) Math.signum(rob1.getDiamenty() - rob2.getDiamenty());
    }
}
