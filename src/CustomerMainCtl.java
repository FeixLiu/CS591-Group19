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
    public void close() throws IOException {
        FXMLLoader close_loader = new FXMLLoader(getClass().getResource("CloseAccount.fxml"));
        Parent close_fxml = close_loader.load();
        Scene close_scene = new Scene(close_fxml, 1024, 768);
        CloseAccountCtl close_control = close_loader.getController();
        close_control.setMain(close.getScene());

        close_control.setBank(bank);
        Stage window = (Stage) close.getScene().getWindow();
        window.setScene(close_scene);

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

        FXMLLoader withdraw_loader = new FXMLLoader(getClass().getResource("Withdraw.fxml"));
        Parent withdraw_fxml = withdraw_loader.load();
        Scene withdraw_scene = new Scene(withdraw_fxml, 1024, 768);
        WithdrawCtl withdraw_control = withdraw_loader.getController();
        withdraw_control.setMain(withdraw.getScene());

        withdraw_control.setBank(bank);
        Stage window = (Stage) withdraw.getScene().getWindow();
        window.setScene(withdraw_scene);

    }
    public void transfer() throws IOException {

        FXMLLoader transfer_loader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
        Parent transfer_fxml = transfer_loader.load();
        Scene transfer_scene = new Scene(transfer_fxml, 1024, 768);
        TransferCtl transfer_control = transfer_loader.getController();
        transfer_control.setMain(transfer.getScene());

        transfer_control.setBank(bank);
        Stage window = (Stage) transfer.getScene().getWindow();
        window.setScene(transfer_scene);

    }
    public void loan() throws IOException {

        FXMLLoader loan_loader = new FXMLLoader(getClass().getResource("Loan.fxml"));
        Parent loan_fxml = loan_loader.load();
        Scene loan_scene = new Scene(loan_fxml, 1024, 768);
        LoanCtl loan_control = loan_loader.getController();
        loan_control.setMain(loan.getScene());

        loan_control.setBank(bank);
        Stage window = (Stage) loan.getScene().getWindow();
        window.setScene(loan_scene);

    }
    public void security() throws IOException {

        FXMLLoader security_loader = new FXMLLoader(getClass().getResource("Security.fxml"));
        Parent security_fxml = security_loader.load();
        Scene security_scene = new Scene(security_fxml, 1024, 768);
        SecurityCtl security_control = security_loader.getController();
        security_control.setMain(security.getScene());

        security_control.setBank(bank);
        Stage window = (Stage) security.getScene().getWindow();
        window.setScene(security_scene);

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
