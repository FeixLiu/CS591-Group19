import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login_Controller{
    Scene reg_scene;
    Scene main_scene;
    FancyBank bank;
    @FXML
    Button register;

    @FXML
    Button login;

    @FXML
    TextField username;
    @FXML
    TextField userId;

    @FXML
    TextField password;

    public void setReg_scene(Scene scene){
        reg_scene = scene;
    }

    public void setMain_scene(Scene scene){
        main_scene = scene;
    }
    public void setBank(FancyBank bank){
        this.bank = bank;
    }

    public void reset(){
        userId.setText("");
        username.setText("");
        password.setText("");
    }

    public void registerClicked(){
        System.out.println("register clicked");
        Stage window = (Stage)register.getScene().getWindow();
        reset();
        window.setScene(reg_scene);
    }

    public void loginClicked() throws IOException {
        System.out.println("login clicked");

        if(bank.customerLogin(userId.getText(), username.getText(), password.getText())){
            FXMLLoader main_loader = new FXMLLoader(getClass().getResource("CustomerMain.fxml"));
            Parent main_fxml = main_loader.load();
            Scene main = new Scene(main_fxml, 1024, 768);
            CustomerMainCtl main_control = (CustomerMainCtl) main_loader.getController();
            main_control.setLogin(login.getScene());

            main_control.setBank(bank);
            Stage window = (Stage) register.getScene().getWindow();
            window.setScene(main);
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Input\n\nPlease try again.");

            alert.showAndWait();
        }
    }

}
