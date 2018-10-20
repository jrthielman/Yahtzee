package sample.objects;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dobbelsteen {

    private int imageWidth;
    private int imageHeight;
    private int firstRow;
    private int firstCollumn;

    private boolean hold = false;

    private List<WritableImage> dobbelsteenImages = new ArrayList<>();
    private WritableImage huidigeImage;
    private int huidigeWaarde;

    public Dobbelsteen(int imageWidth, int imageHeight, int firstRow, int firstCollumn){
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.firstRow = firstRow;
        this.firstCollumn = firstCollumn;
        maakDobbelstenen();
    }

    private void maakDobbelstenen(){
        int offsetX = imageWidth/firstRow;
        int offsetY = imageHeight/firstCollumn;

        Image dobbelsteenSheet = new Image("sample/img/dobbelstenen.png");
        PixelReader cropImage = dobbelsteenSheet.getPixelReader();

        for(int y = 0; y < imageHeight; y += offsetY){
            //System.out.println("y = " + y);
            for(int x = 0; x < imageWidth-offsetX; x += offsetX){
                //System.out.println("x = " + x);
                dobbelsteenImages.add(new WritableImage(cropImage,
                        x,y, offsetX,offsetY));

            }
        }
    }

    public WritableImage giveRandomImage(){
        if(!hold){
            int randomWaarde = new Random().nextInt(6);
            huidigeImage = dobbelsteenImages.get(randomWaarde);
            huidigeWaarde = randomWaarde+1;
            return huidigeImage;
        }else{
            return huidigeImage;
        }

    }

    public int getHuidigeWaarde() {
        return huidigeWaarde;
    }

    public boolean getHold(){
        return hold;
    }

    public boolean setHold(boolean value){
        if(hold == value){
            return false;
        }
        hold = value;
        return true;
    }
}
