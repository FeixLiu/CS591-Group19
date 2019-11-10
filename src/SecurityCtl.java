import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class SecurityCtl {

    Scene main;
    FancyBank bank;

    public void setMain(Scene main) {
        this.main = main;
        number.setVisible(false);
    }

    public void setBank(FancyBank bank) {
        this.bank = bank;
    }

    @FXML
    Button submit;
    @FXML
    Button ret;

    public void ret(){
        Stage window = (Stage) ret.getScene().getWindow();
        window.setScene(main);
    }

    public void submit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Please Confirm");
        alert.setContentText("Do you want to do this? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(buy.isSelected()){
                if (bank.buyStock(name.getText(), number.getText(), id.getText(), password.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();


                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                return;
            }
            if(sell.isSelected()){
                String str = bank.sellStocks(id.getText(), password.getText(), name.getText());
                Alert res = new Alert(Alert.AlertType.INFORMATION);
                res.setTitle("Info");
                res.setHeaderText(null);
                res.setContentText(str);
                res.showAndWait();
                return;
            }
        } else {
            // ... user chose CANCEL or closed the dialog
            return;
        }
    }

    @FXML
    RadioButton buy;
    @FXML
    RadioButton sell;

    @FXML
    TextField name;
    @FXML
    TextField number;
    @FXML
    TextField id;
    @FXML
    TextField password;

    public void buy(){
        buy.setSelected(true);
        sell.setSelected(false);
        number.setVisible(true);
    }
    public void sell(){
        buy.setSelected(false);
        sell.setSelected(true);
        number.setVisible(false);
    }
}
