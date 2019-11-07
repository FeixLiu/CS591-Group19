import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Regist_Controller{
    Scene login;
    Scene main;
    FancyBank bank;

    @FXML
    Button cancel;
    @FXML
    Button register;
    @FXML
    TextField name;
    @FXML
    TextField address;
    @FXML
    TextField username;
    @FXML
    TextField password;

    public void setLogin(Scene scene){
        login = scene;
    }
    public void setMain(Scene scene){
        main = scene;
    }

    public void reset(){
        name.setText("");
        address.setText("");
        username.setText("");
        password.setText("");
    }

    public void cancel_clicked(){
        System.out.println("Cancel clicked");
        Stage window = (Stage) cancel.getScene().getWindow();
        reset();
        window.setScene(login);
    }

    public void register_clicked() throws IOException {
//        System.out.println(("Register clicked"));
//        for(User user : atm.getUsers()){
//            if(user.getUsername().compareTo(username.getText())==0){
//                System.out.println("username exist");
//                return;
//            }
//        }
//        if(name.getText().length() != 0 && address.getText().length() != 0 && username.getText().length()!=0 && password.getText().length()!=0){
//            User u = atm.register(name.getText(), address.getText(), username.getText(), password.getText());
//            FXMLLoader main_loader = new FXMLLoader(getClass().getResource("main.fxml"));
//            Parent main_fxml = main_loader.load();
//            Scene main = new Scene(main_fxml, 600, 400);
//            Main_Controller main_control = (Main_Controller) main_loader.getController();
//            main_control.setLogin(login);
//            main_control.setUser(u);
//            main_control.setAtm(atm);
//            Stage window = (Stage) register.getScene().getWindow();
//            reset();
//            window.setScene(main);
//            return;
//        }

        System.out.println("Check inputs");
    }

    public void setBank(FancyBank bank) {
        this.bank = bank;
    }
}
