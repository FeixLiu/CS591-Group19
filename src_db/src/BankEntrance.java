/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;*/

public class BankEntrance{
    public static void main(String[] args) {
        FancyBank sd = new FancyBank();
        sd.mainMenu();
        //launch(args);
    }
    /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        FancyBank bank = new FancyBank();
        FXMLLoader login_loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent login_fxml = login_loader.load();
        Scene login = new Scene(login_fxml, 600, 400);
        Login_Controller login_control = (Login_Controller) login_loader.getController();

        FXMLLoader reg_loader = new FXMLLoader(getClass().getResource("regist.fxml"));
        Parent reg_fxml = reg_loader.load();
        Scene reg = new Scene(reg_fxml, 600, 400);
        Regist_Controller reg_control = (Regist_Controller) reg_loader.getController();

        login_control.setReg_scene(reg);
        login_control.setBank(bank);
        reg_control.setLogin(login);
        reg_control.setBank(bank);

        primaryStage.setTitle("ATM Welcome");
        primaryStage.setScene(login);
        primaryStage.show();

    }*/
}
