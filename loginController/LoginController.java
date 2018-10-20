package sample.loginController;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class LoginController {

    @FXML private  TextField username;
    @FXML private Label waarschuwing;

    public String getUsername(){
        return username.getText().trim();
    }

    public void geefWaarschuwing(String waarschuwingTekst){
       waarschuwing.setText(waarschuwingTekst);
    }

}
