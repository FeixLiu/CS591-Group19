import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class LoanCtl {
    Scene main;
    FancyBank bank;

    public void setMain(Scene main) {
        this.main = main;
    }

    public void setBank(FancyBank bank) {
        this.bank = bank;
    }

    @FXML
    Button request;
    @FXML
    Button pay;
    @FXML
    Button cancel;

    public void cancel(){
        Stage window = (Stage) cancel.getScene().getWindow();
        window.setScene(main);
    }

    public void request(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Please Confirm");
        alert.setContentText("Do you want to request this account? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (bank.requestLoan(amount.getText())){
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.setContentText("Succeed!");
                success.showAndWait();

                cancel();

            }else{
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText(null);
                error.setContentText("Invalid Input\n\nTry Again");
                error.showAndWait();
            }
        } else {
            // ... user chose CANCEL or closed the dialog
            return;
        }
    }

    public void pay(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Please Confirm");
        alert.setContentText("Do you want to repay? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (bank.payForLoan(id.getText(), password.getText())){
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.setContentText("Succeed!");
                success.showAndWait();

                cancel();

            }else{
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText(null);
                error.setContentText("Invalid Input\n\nTry Again");
                error.showAndWait();
            }
        } else {
            // ... user chose CANCEL or closed the dialog
            return;
        }
    }

    @FXML
    TextField amount;
    @FXML
    TextField id;
    @FXML
    TextField password;
}
