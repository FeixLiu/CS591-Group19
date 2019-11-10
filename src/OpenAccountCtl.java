import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

public class OpenAccountCtl {

    Scene main;
    FancyBank bank;

    @FXML
    Button submit;
    @FXML
    Button cancel;

    @FXML
    RadioButton typeChecking;
    @FXML
    RadioButton typeSaving;
    @FXML
    RadioButton typeSecurity;

    @FXML
    TextField password;
    @FXML
    TextField repeat;
    @FXML
    TextField savingId;
    @FXML
    TextField savingPassword;


    public void setMain(Scene main) {
        this.main = main;
        savingId.setVisible(false);
        savingPassword.setVisible(false);
    }

    public void setBank(FancyBank bank) {
        this.bank = bank;
    }

    public void cancel(){
        Stage window = (Stage) cancel.getScene().getWindow();
        window.setScene(main);
    }

    public void submit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Please Confirm");
        alert.setContentText("Do you want to open an account? ");

        Optional<ButtonType> result = alert.showAndWait();
        Id newId = bank.newId(Config.NEWACCOUNT);
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            if(typeChecking.isSelected()){
                if (bank.createAccount(newId, password.getText(), repeat.getText(),Config.NEWCHECKING,null, null)){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed! Please remember your account ID: " + newId.getId());
                    success.showAndWait();

                    cancel();

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
            }
            Id accId = bank.newId(Config.NEWACCOUNT);
            if(typeSaving.isSelected()){
                if (bank.createAccount(newId, password.getText(), repeat.getText(),Config.NEWSAVING,null, null)){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed! Please remember your account ID: " + newId.getId());
                    success.showAndWait();

                    cancel();

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
            }
            if(typeSecurity.isSelected()){
                if (bank.createAccount(newId, password.getText(), repeat.getText(),Config.NEWSECURITY,savingId.getText(), savingPassword.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed! Please remember your account ID: " + newId.getId());
                    success.showAndWait();

                    cancel();

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
            }

        } else {
            // ... user chose CANCEL or closed the dialog
            return;
        }
    }

    public void typeChecking(){
        typeChecking.setSelected(true);
        typeSaving.setSelected(false);
        typeSecurity.setSelected(false);

        savingId.setVisible(false);
        savingPassword.setVisible(false);
    }
    public void typeSaving(){
        typeChecking.setSelected(false);
        typeSaving.setSelected(true);
        typeSecurity.setSelected(false);

        savingId.setVisible(false);
        savingPassword.setVisible(false);
    }
    public void typeSecurity(){
        typeChecking.setSelected(false);
        typeSaving.setSelected(false);
        typeSecurity.setSelected(true);

        savingId.setVisible(true);
        savingPassword.setVisible(true);
    }

}
