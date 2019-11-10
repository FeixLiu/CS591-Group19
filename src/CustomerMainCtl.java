import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void open() throws IOException {
        FXMLLoader open_loader = new FXMLLoader(getClass().getResource("OpenAccount.fxml"));
        Parent open_fxml = open_loader.load();
        Scene open_scene = new Scene(open_fxml, 1024, 768);
        OpenAccountCtl open_control = open_loader.getController();
        open_control.setMain(open.getScene());

        open_control.setBank(bank);
        Stage window = (Stage) open.getScene().getWindow();
        window.setScene(open_scene);
    }
    public void close(){

    }
    public void deposit() throws IOException {
        FXMLLoader deposit_loader = new FXMLLoader(getClass().getResource("Deposit.fxml"));
        Parent deposit_fxml = deposit_loader.load();
        Scene deposit_scene = new Scene(deposit_fxml, 1024, 768);
        DepositCtl deposit_control = deposit_loader.getController();
        deposit_control.setMain(deposit.getScene());

        deposit_control.setBank(bank);
        Stage window = (Stage) deposit.getScene().getWindow();
        window.setScene(deposit_scene);
    }
    public void withdraw() throws IOException {

    }
    public void transfer() throws IOException {

    }
    public void loan() throws IOException {

    }
    public void security() throws IOException {

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
        alert.setTitle("Transactions");
        alert.setHeaderText(null);
        alert.setContentText(bank.getTransaction());

        alert.showAndWait();
    }
}
