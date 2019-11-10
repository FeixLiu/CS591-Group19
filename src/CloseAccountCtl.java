import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class CloseAccountCtl {

    Scene main;
    FancyBank bank;

    public void setMain(Scene main) {
        this.main = main;
    }

    public void setBank(FancyBank bank) {
        this.bank = bank;
    }

    @FXML
    Button submit;
    @FXML
    Button cancel;

    public void cancel(){
        Stage window = (Stage) cancel.getScene().getWindow();
        window.setScene(main);
    }

    public void submit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Please Confirm");
        alert.setContentText("Do you want to close this account? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String ret = bank.closeAccount(id.getText(), password.getText());

            Alert res = new Alert(Alert.AlertType.INFORMATION);
            res.setTitle("Info");
            res.setHeaderText(null);
            res.setContentText(ret);
            res.showAndWait();
        } else {
            // ... user chose CANCEL or closed the dialog
            return;
        }

    }


    @FXML
    TextField id;
    @FXML
    TextField password;



}
