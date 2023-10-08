package main.Agenci.Robotnicy.StrategieProdukcji;

import main.Agenci.Robotnicy.Zawody.Zawód;
import main.Giełda.Giełda;
import main.Giełda.TransakcjeProduktu;
import main.Produkty.Produkt;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Średniak extends StrategiaProdukcji{
    int historiaŚredniejProdukcji;

    public Średniak(int historiaŚredniejProdukcji) {
        super();
        this.historiaŚredniejProdukcji = historiaŚredniejProdukcji;
    }

    @Override
    public String dajNazwę() {
        return "sredniak";
    }

    @Override
    public int typProdukcji() {
        Giełda giełda = getRobotnik().getGiełda();
        double[] maxŚrednia = new double[4];
        int dzień = giełda.getDzień();
        Zawód obecnyZawód = getRobotnik().getObecnyZawód();


        for (int i = 0; i < min(dzień, historiaŚredniejProdukcji); i++) {
            for (int j = 0; j < 4; j++) {
                TransakcjeProduktu transakcjeProduktu = giełda.getTransakcjeProduktu
                        (Produkt.dajProdukt(j, obecnyZawód.dajPoziomProduktu(j)), i);
                int ileSprzedano = transakcjeProduktu.getIleSprzedano();
                double sumaCen = transakcjeProduktu.getSumaCen();
                maxŚrednia[j] = max(maxŚrednia[j], sumaCen/ileSprzedano);
            }
        }

        double najwyższaŚrednia = 1;
        int typProduktu = -1;

        for (int i = 0; i < 4; i++) {
            if (maxŚrednia[i] >= najwyższaŚrednia) {
                najwyższaŚrednia = maxŚrednia[i];
                typProduktu = i;
            }
        }

        return typProduktu;
    }
}
