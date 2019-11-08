import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomerMainCtl {
    Scene login;
    FancyBank bank;

    public void setLogin(Scene login) {
        this.login = login;
    }

    public void setBank(FancyBank bank) {
        this.bank = bank;
    }

    @FXML
    Button logout;
    @FXML
    Button deposit;
    @FXML
    Button withdraw;
    @FXML
    Button transfer;
    @FXML
    Button loan;
    @FXML
    Button open;
    @FXML
    Button close;




    public void logout(){
        Stage window = (Stage) logout.getScene().getWindow();
        window.setScene(login);
    }
}
