package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.data.UserInput;
import sample.objects.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    @FXML private ImageView dobbelSteen;
    @FXML private ImageView dobbelSteen2;
    @FXML private ImageView dobbelSteen3;
    @FXML private ImageView dobbelSteen4;
    @FXML private ImageView dobbelSteen5;

    @FXML private TextField aces, two, three, four, five, six,
    onePair, twoPair;

    @FXML private Button acesKnop, twoKnop, threeKnop, fourKnop, fiveKnop, sixKnop, onePairKnop, twoPairKnop;

    @FXML private Button werpButton;
    @FXML private TextField userInput;
    @FXML private TextField totaalWaarde;
    @FXML private Label username;

    private Kaart speelKaart = new Kaart();

    private List<ImageView> imageViewLijst = new ArrayList<>();
    private List<Button> kaartButtonLijst = new ArrayList<>();
    private List<TextField> kaartFieldLijst = new ArrayList<>();
    private List<Dobbelsteen> dobbelstenenLijst = new ArrayList<>();
    private List<Speler> spelerLijst = new ArrayList<>();

    public void initialize() {

        addUser();
        maakDobbelstenen();
        setupDobbelstenen(50);
        dobbelsteenInteractie();
        inputInteractie();
        kaartInteractie();
    }

    private void kaartInteractie(){
        vullKaartenLijst();
        for(int i = 0; i < 6; i++){
            final int ii = i+1;
            kaartButtonLijst.get(i).setOnAction(e->{
                if(speelKaart.getValues(ii) == 0){
                    for(Dobbelsteen dobbelsteen : dobbelstenenLijst){
                        if(dobbelsteen.getHuidigeWaarde() == ii){
                            speelKaart.setInput(ii,dobbelsteen.getHuidigeWaarde());
                        }
                    }
                    kaartFieldLijst.get(ii-1).setText("" + speelKaart.getValues(ii));
                    totaalWaarde.setText("" + speelKaart.totaalWaarde());
                }else{
                    alGekozenScherm();
                }
            });
        }

        for(int i = 6; i < 8; i++){
            final int ii = i+1;
            kaartButtonLijst.get(i).setOnAction(e->{
                if(speelKaart.getValues(ii) == 0){
                    for(Dobbelsteen dobbelsteen : dobbelstenenLijst){
                        speelKaart.extraPunten(ii, dobbelsteen);
                    }
                    kaartFieldLijst.get(ii-1).setText("" + speelKaart.getValues(ii));
                    speelKaart.resetVergelijkingDobbelstenen();
                    totaalWaarde.setText("" + speelKaart.totaalWaarde());
                }else{
                    alGekozenScherm();
                }
            });
        }

    }

    private void vullKaartenLijst(){
        kaartButtonLijst.add(acesKnop);kaartButtonLijst.add(twoKnop);kaartButtonLijst.add(threeKnop);kaartButtonLijst.add(fourKnop);kaartButtonLijst.add(fiveKnop);
        kaartButtonLijst.add(sixKnop);kaartButtonLijst.add(onePairKnop);kaartButtonLijst.add(twoPairKnop);

        kaartFieldLijst.add(aces);kaartFieldLijst.add(two);kaartFieldLijst.add(three);kaartFieldLijst.add(four);kaartFieldLijst.add(five);
        kaartFieldLijst.add(six);kaartFieldLijst.add(onePair);kaartFieldLijst.add(twoPair);

    }

    @FXML
    private void reset(){
        for(int i = 0; i < dobbelstenenLijst.size(); i++){
            Dobbelsteen tempDobbelsteen = dobbelstenenLijst.get(i);
            if(tempDobbelsteen.getHold()){
                tempDobbelsteen.setHold(false);
                imageViewLijst.get(i).setStyle("-fx-opacity: 1.0");
            }
        }
    }

    @FXML
    private void resetKaart(){
        speelKaart.resetWaardes();
        aces.setText("");two.setText("");three.setText("");four.setText("");five.setText("");six.setText("");onePair.setText("");twoPair.setText("");
    }

    public void addUser(){
        spelerLijst.add(new Speler(UserInput.getInstance().getNaam()));
        username.setText("Speler: " + UserInput.getInstance().getNaam());
    }

    private void alGekozenScherm(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        alert.setContentText("U heeft deze al gekozen");
        alert.showAndWait();
    }

    private void inputInteractie(){
        userInput.addEventHandler(KeyEvent.KEY_PRESSED, e ->{
            if(e.getCode().equals(KeyCode.ENTER)){
                String[] temp = userInput.getText().trim().split("");
                for(int i = 0; i < temp.length; i++){
                    if(!NumberChecker.containsNumber(temp)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Waarschuwing");
                        alert.setHeaderText("Verkeerde input");
                        alert.setContentText("Je hebt een letter, een speciale teken of helemaal niks ingetypt." +
                                "\nToets nu de cijfers in die je wilt vasthouden zonder spatie");
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK || result.get() == ButtonType.CLOSE){
                            return;
                        }
                    }else{
                        int tempInt = Integer.parseInt(temp[i]);
                        for(int y = 0; y < dobbelstenenLijst.size(); y++){
                            if(dobbelstenenLijst.get(y).getHuidigeWaarde() == tempInt &&
                                    !dobbelstenenLijst.get(y).getHold()) {
                                dobbelstenenLijst.get(y).setHold(true);
                                imageViewLijst.get(y).setStyle("-fx-opacity: .5");
                                break;
                                //System.out.println(tempInt + " " + dobbelstenenLijst.get(y).getHuidigeWaarde());
                            }
                        }
                    }
                }
                userInput.clear();
            }
        });
    }

    private void dobbelsteenInteractie(){

        for(ImageView image : imageViewLijst){
            image.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
                Dobbelsteen temp = dobbelstenenLijst.get(imageViewLijst.indexOf(image));
                if(temp.getHold()) {
                    temp.setHold(false);
                    image.setStyle("-fx-opacity: 1.0");
                    System.out.println("unclicked");
                }
                else{
                    temp.setHold(true);
                    image.setStyle("-fx-opacity: 0.5");
                    System.out.println("clicked");
                }
            });
        }

        werpButton.setOnAction(e -> {
            for(int i = 0; i < spelerLijst.size(); i++){
                for(int y = 0; y < imageViewLijst.size(); y++){
                    imageViewLijst.get(y).setImage(dobbelstenenLijst.get(y).giveRandomImage());
                    spelerLijst.get(i).addTotaalWaarde(dobbelstenenLijst.get(y).getHuidigeWaarde());
                }

                // reset totale waarde
                //spelerLijst.get(i).resetWaarde();
            }
        });

    }

    private void maakDobbelstenen(){
        for(int i = 0; i < 5; i++){
            dobbelstenenLijst.add(new Dobbelsteen(260,
                    170,3,2));
        }
    }

    private void setupDobbelstenen(int grote){

        imageViewLijst.add(dobbelSteen);
        imageViewLijst.add(dobbelSteen2);
        imageViewLijst.add(dobbelSteen3);
        imageViewLijst.add(dobbelSteen4);
        imageViewLijst.add(dobbelSteen5);

        for(ImageView image : imageViewLijst){
            image.setFitWidth(grote);
            image.setFitHeight(grote);
        }

    }

}
