package main.Agenci.Robotnicy.StrategieZmianyŚcieżki;

import main.Giełda.Giełda;
import main.Giełda.TransakcjeDnia;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Rewolucjonista extends StrategiaZmianyŚcieżki {

    @Override
    public String dajNazwę() {
        return "rewolucjonista";
    }

    @Override
    public int zmianaŚcieżki() {
        if(getRobotnik().getGiełda().getDzień() % 7 !=0) {
            return -1;
        }

        Giełda giełda = getRobotnik().getGiełda();
        int n = getRobotnik().getId() % 17;
        int dzień = giełda.getDzień();
        n = max(n,1);
        int[] wystawienia = new int[4];
        TransakcjeDnia transakcjeDnia;
        for(int i = 0; i < min(n,dzień); i++) {
            for(int j = 0; j < 4; j++) {
                wystawienia[j] += giełda.getTransakcjeDnia(i).getIleWystawionoTypu(j);
            }
        }
        int maxWystawień = 0;
        int idProduktu = -1;
        for(int i = 0; i < 4; i++) {
            if (wystawienia[i] > maxWystawień) {
                idProduktu = i;
                maxWystawień = wystawienia[i];
            }
        }
        return idProduktu;
    }
}
