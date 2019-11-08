import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
//        if(username.getText().length()==0){
//            System.out.println("username can not be empty");
//            return;
//        }
//        if(password.getText().length()==0){
//            System.out.println(("password can not be empty"));
//            return;
//        }

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

//        if(username.getText().compareTo(atm.manager.getPassword()) == 0 && password.getText().compareTo(atm.manager.getPassword())==0){
//            FXMLLoader manage_loader = new FXMLLoader(getClass().getResource("../../Hong_Qichao_My_Fancy_Bank/src/manage.fxml"));
//            Parent manage_fxml = manage_loader.load();
//            Scene manage = new Scene(manage_fxml, 600, 400);
//            Manage_Controller manage_control = (Manage_Controller) manage_loader.getController();
//            manage_control.setLogin(login.getScene());
//            manage_control.setManager(atm.manager);
//            manage_control.setAtm(atm);
//            Stage window = (Stage) register.getScene().getWindow();
//            window.setScene(manage);
//            return;
//        }
//        User u= atm.login(username.getText(),password.getText());
//        if (u!=null){
//            FXMLLoader main_loader = new FXMLLoader(getClass().getResource("../../Hong_Qichao_My_Fancy_Bank/src/main.fxml"));
//            Parent main_fxml = main_loader.load();
//            Scene main = new Scene(main_fxml, 600, 400);
//            Main_Controller main_control = (Main_Controller) main_loader.getController();
//            main_control.setLogin(login.getScene());
//            main_control.setUser(u);
//            main_control.setAtm(atm);
//            Stage window = (Stage) register.getScene().getWindow();
//            window.setScene(main);
//            return;
//        }
        System.out.println("No matching, sign up");
    }

}
