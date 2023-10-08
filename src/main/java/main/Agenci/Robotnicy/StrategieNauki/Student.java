package main.Agenci.Robotnicy.StrategieNauki;

import main.Giełda.Giełda;
import main.Giełda.TransakcjeProduktu;

import static java.lang.Math.min;

public class Student extends StrategiaNauki{
    private int zapas;
    private int okres;

    public Student(int zapas, int okres) {
        super();
        this.zapas = zapas;
        this.okres = okres;
    }

    @Override
    public String dajNazwę() {
        return "student";
    }

    @Override
    public boolean czyNauka() {
        int sprzedaneJedzenie = 0;
        double łącznaCena = 0;
        Giełda giełda = getRobotnik().getGiełda();
        int dzień = giełda.getDzień();
        for (int i = 0; i < min(dzień, okres); i++) {
            TransakcjeProduktu transakcjeJedzenia = giełda.getTransakcjeDnia(i).getTransakcjeTypu(0);
            sprzedaneJedzenie += transakcjeJedzenia.getIleSprzedano();
            łącznaCena += transakcjeJedzenia.getSumaCen();
        }
        double średniaCena = łącznaCena/sprzedaneJedzenie;
        return getRobotnik().getDiamenty() >= średniaCena * 100 * zapas;
    }
}
