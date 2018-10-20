package sample.objects;

import java.util.List;

public class Speler {
    private String naam;
    private int totaalWaarde;

    public Speler(String naam) {
        this.naam = naam;
    }

    public void resetWaarde(){
        totaalWaarde = 0;
    }

    public void addTotaalWaarde(int waarde){
        totaalWaarde += waarde;
    }

    public int getTotaalWaarde(){
        return totaalWaarde;
    }
}
