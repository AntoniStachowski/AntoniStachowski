package main.Agenci.Robotnicy.StrategieProdukcji;

import main.Agenci.Robotnicy.Robotnik;
import main.Agenci.Robotnicy.Zawody.Zawód;
import main.Giełda.Giełda;
import main.Giełda.TransakcjeProduktu;
import main.Produkty.Produkt;

public class Chciwy extends StrategiaProdukcji{

    @Override
    public String dajNazwę() {
        return "chciwy";
    }

    @Override
    public int typProdukcji() {
        Robotnik robotnik = getRobotnik();
        Zawód zawód = robotnik.getObecnyZawód();
        Giełda giełda = robotnik.getGiełda();
        double najwyższyZysk = robotnik.getProduktywność(-1);
        int typProduktu = -1;

        for (int i = 0; i < 4; i++) {
            TransakcjeProduktu transakcjeProduktu =
                    giełda.getTransakcjeProduktu(Produkt.dajProdukt(i, zawód.dajPoziomProduktu(i)), i);
            double średniaCena = transakcjeProduktu.getSumaCen() / transakcjeProduktu.getIleSprzedano();
            if (robotnik.getProduktywność(i) * średniaCena >= najwyższyZysk) {
                najwyższyZysk = robotnik.getProduktywność(i) * średniaCena;
                typProduktu = i;
            }
        }

        return typProduktu;
    }
}
