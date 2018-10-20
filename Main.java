package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import sample.data.UserInput;
import sample.loginController.LoginController;
import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        if(setupLoginDialog()){
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            primaryStage.setTitle("Yahtzee");
            primaryStage.setScene(new Scene(root, 550, 310));
            primaryStage.show();
        }
    }

    public boolean setupLoginDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Yahtzee");

        FXMLLoader loginFxmlLoader = new FXMLLoader();

        loginFxmlLoader.setLocation(getClass().getResource("loginController/loginScherm.fxml"));

        try{
            dialog.getDialogPane().setContent(loginFxmlLoader.load());
        }catch(Exception e){
            System.out.println("Exception caught: " + e.getMessage());
            return false;
        }

        LoginController loginController = loginFxmlLoader.getController();

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        boolean quit = false;
        while(!quit){
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK &&
                    loginController.getUsername().length() > 0){
                UserInput.getInstance().setNaam(loginController.getUsername());
                quit = true;
            }else if(result.isPresent() && result.get() == ButtonType.CANCEL){
                System.exit(0);
            }else{
                loginController.geefWaarschuwing("U heeft niks ingevoerd");
            }
        }

        return true;

    }


    public static void main(String[] args) {
        launch(args);
    }
}
