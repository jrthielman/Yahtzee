package sample.objects;

public class Kaart {

    private int[] mogelijkheden = new int[8];

    private Dobbelsteen vergelijkingDobbelsteen1;
    private Dobbelsteen vergelijkingDobbelsteen2;

    private int tempWaarde;

    public void extraPunten(int positieInLijst, Dobbelsteen dobbelsteen){
        switch (positieInLijst){
            case 7:
                if(dobbelsteen.getHold()){
                    if(vergelijkingDobbelsteen1 == null){
                        vergelijkingDobbelsteen1 = dobbelsteen;
                    } else if(dobbelsteen.getHuidigeWaarde() == vergelijkingDobbelsteen1.getHuidigeWaarde()){
                        mogelijkheden[positieInLijst-1] = (dobbelsteen.getHuidigeWaarde() * 2);
                    }
                }
                break;
            case 8:
                if(dobbelsteen.getHold()){
                    if(vergelijkingDobbelsteen1 == null){
                        vergelijkingDobbelsteen1 = dobbelsteen;
                    } else if(vergelijkingDobbelsteen2 == null && dobbelsteen.getHuidigeWaarde() != vergelijkingDobbelsteen1.getHuidigeWaarde()){
                        vergelijkingDobbelsteen2 = dobbelsteen;
                    }
                    else if(dobbelsteen.getHuidigeWaarde() == vergelijkingDobbelsteen1.getHuidigeWaarde()){
                        tempWaarde = dobbelsteen.getHuidigeWaarde() * 2;
                    }else if(dobbelsteen.getHuidigeWaarde() == vergelijkingDobbelsteen2.getHuidigeWaarde()){
                        mogelijkheden[positieInLijst-1] += (dobbelsteen.getHuidigeWaarde() * 2) + tempWaarde;
                    }
                }
                break;

        }

    }

    public void resetVergelijkingDobbelstenen(){
        vergelijkingDobbelsteen1 = null;
        vergelijkingDobbelsteen2 = null;
        tempWaarde = 0;
    }

    public int getValues(int checkNumber){
        switch(checkNumber){
            case 1:
                return mogelijkheden[0];
            case 2:
                return mogelijkheden[1];
            case 3:
                return mogelijkheden[2];
            case 4:
                return mogelijkheden[3];
            case 5:
                return mogelijkheden[4];
            case 6:
                return mogelijkheden[5];
            case 7:
                return mogelijkheden[6];
            default:
                return mogelijkheden[7];
        }
    }

    public void setInput(int checkNumber, int value){
        switch(checkNumber){
            case 1:
                mogelijkheden[0] += value;
                break;
            case 2:
                mogelijkheden[1] += value;
                break;
            case 3:
                mogelijkheden[2] += value;
                break;
            case 4:
                mogelijkheden[3] += value;
                break;
            case 5:
                mogelijkheden[4] += value;
                break;
            case 6:
                mogelijkheden[5] += value;
                break;
            case 7:
                mogelijkheden[6] += value;
                break;
            case 8:
                mogelijkheden[7] += value;
                break;
        }
    }

    public int totaalWaarde(){
        int totaleWaarde = 0;
        for(int waardes : mogelijkheden) {
          totaleWaarde += waardes;
        }
        return totaleWaarde + addBonus();
    }

    private int addBonus() {
        if (mogelijkheden[0] + mogelijkheden[1] + mogelijkheden[2] + mogelijkheden[3] + mogelijkheden[4] + mogelijkheden[5] >= 63) {
            return 50;
        }else{
            return 0;
        }
    }

    public void resetWaardes(){
        mogelijkheden[0] = mogelijkheden[1] = mogelijkheden[2] = mogelijkheden[3] = mogelijkheden[4] = mogelijkheden[5] =
                mogelijkheden[6] = mogelijkheden[7] = 0;
    }



}
