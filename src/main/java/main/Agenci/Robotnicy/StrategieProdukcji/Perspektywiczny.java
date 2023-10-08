package main.Agenci.Robotnicy.StrategieProdukcji;

import main.Agenci.Robotnicy.Zawody.Zawód;
import main.Giełda.Giełda;
import main.Produkty.Produkt;

public class Perspektywiczny extends StrategiaProdukcji{
    private int historiaPerspektywy;

    public Perspektywiczny(int historiaPerspektywy) {
        super();
        this.historiaPerspektywy = historiaPerspektywy;
    }

    @Override
    public String dajNazwę() {
        return "perspektywiczny";
    }

    @Override
    public int typProdukcji() {
        Giełda giełda = getRobotnik().getGiełda();
        Zawód zawód = getRobotnik().getObecnyZawód();
        double najwyższaRóznicaCen = 0;
        int typProduktu = -1;
        for (int i = 0; i < 4; i++) {
            Produkt produkt = Produkt.dajProdukt(i, zawód.dajPoziomProduktu(i));
            double średniaCenaWczoraj = giełda.getŚredniaProduktuZDnia(produkt,0);

            double średniaCenaDawniej = giełda.getŚredniaProduktuZDnia(produkt, historiaPerspektywy);

            double różnicaCen = średniaCenaDawniej - średniaCenaWczoraj;

            if (różnicaCen >= najwyższaRóznicaCen) {
                najwyższaRóznicaCen = różnicaCen;
                typProduktu = i;
            }
        }
        return typProduktu;
    }
}
