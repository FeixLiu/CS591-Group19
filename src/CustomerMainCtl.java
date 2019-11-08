import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    @FXML
    Button security;
    @FXML
    Button accounts;
    @FXML
    Button balance;
    @FXML
    Button logs;


    public void logout(){
        Stage window = (Stage) logout.getScene().getWindow();
        window.setScene(login);
    }

    public void open(){

    }
    public void close(){

    }
    public void deposit(){

    }
    public void withdraw(){

    }
    public void transfer(){

    }
    public void loan(){

    }
    public void security(){

    }
    public void viewAccount(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Accounts");
        alert.setHeaderText(null);
        alert.setContentText(bank.viewAccount());

        alert.showAndWait();
    }
    public void viewBalance(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Balance");
        alert.setHeaderText(null);
        alert.setContentText(bank.viewBalance());

        alert.showAndWait();
    }
    public void logs(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(bank.getTransaction());

        alert.showAndWait();
    }
}
