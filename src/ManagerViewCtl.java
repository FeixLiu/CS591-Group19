import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class ManagerViewCtl {

    Scene login;
    FancyBank bank;

    public void setLogin(Scene login) {
        this.login = login;
        inputP.setVisible(false);
    }

    public void setBank(FancyBank bank) {
        this.bank = bank;
        stockList.setText(bank.lookingAllStocks());
    }

    @FXML
    Button logout;
    public void logout(){
        Stage window = (Stage) logout.getScene().getWindow();
        window.setScene(login);
    }


    @FXML
    Button allCustomer;
    public void allCustomer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Customer");
        alert.setHeaderText(null);
        alert.setContentText(bank.lookAllCustomer());

        alert.showAndWait();
    }

    @FXML
    Button allMoney;
    public void allMoney(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Money");
        alert.setHeaderText(null);
        alert.setContentText(bank.lookAllMoney());

        alert.showAndWait();
    }

    @FXML
    Button allLoan;
    public void allLoan(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Loan");
        alert.setHeaderText(null);
        alert.setContentText(bank.lookAllLoan());

        alert.showAndWait();
    }

    @FXML
    Button allCustomerStocks;
    public void allCustomerStocks(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Costomer Stocks");
        alert.setHeaderText(null);
        alert.setContentText(bank.lookingAllStocks());

        alert.showAndWait();
    }

    @FXML
    Button bankBalance;
    public void bankBalance(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bank Balance");
        alert.setHeaderText(null);
        alert.setContentText(bank.getBalance());

        alert.showAndWait();
    }

    @FXML
    Button logs;
    public void logs(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logs");
        alert.setHeaderText(null);
        alert.setContentText(bank.getLog());

        alert.showAndWait();
    }

    @FXML
    Button checkCustomer;
    public void checkCustomer(){
        command = 1;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab2.setVisible(true);
        label2.setVisible(true);
        label1.setText("Customer ID");
        label2.setText("Customer name");
        lab3.setVisible(false);
        label3.setVisible(false);
    }

    @FXML
    Button checkCustomerMostLoan;
    public void checkCustomerMostLoan(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Most Loan");
        alert.setHeaderText(null);
        alert.setContentText(bank.lookCustomerWithMostLoan());

        alert.showAndWait();
    }

    @FXML
    Button changeStockPrice;
    public void changeStockPrice(){
        command = 2;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab2.setVisible(true);
        label2.setVisible(true);
        lab1.setText("");
        lab2.setText("");
        label1.setText("Stock Name");
        label2.setText("New Price");
        lab3.setVisible(false);
        label3.setVisible(false);
    }

    @FXML
    Button changeStockAvail;
    public void  changeStockAvail(){
        command = 3;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab2.setVisible(true);
        label2.setVisible(true);
        lab1.setText("");
        lab2.setText("");
        label1.setText("Stock Name");
        label2.setText("Available Number");
        lab3.setVisible(false);
        label3.setVisible(false);
    }

    @FXML
    Button addStock;
    public void addStock(){
        command = 4;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab2.setVisible(true);
        label2.setVisible(true);
        lab1.setText("");
        lab2.setText("");
        label1.setText("Stock Name");
        label2.setText("Stock Price");
        lab3.setVisible(true);
        label3.setVisible(true);
        label3.setText("Available Number");
    }

    @FXML
    Button deleteStock;
    public void deleteStock(){
        command = 5;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab1.setText("");
        lab2.setText("");
        label1.setText("Stock Name");
        label2.setText("");
        lab2.setVisible(false);
        label2.setVisible(false);
        lab3.setVisible(false);
        label3.setVisible(false);
    }

    @FXML
    Button changeInterest;
    public void changeInterest(){
        command = 6;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab2.setVisible(true);
        label2.setVisible(true);
        lab1.setText("");
        lab2.setText("");
        label1.setText("Saving Interest Rate");
        label2.setText("Loan Interest Rate");
        lab3.setVisible(false);
        label3.setVisible(false);
    }

    @FXML
    Button endDay;
    public void endDay(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Please Confirm");
        alert.setContentText("Do you want to end today?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            bank.endDay();
            Alert res = new Alert(Alert.AlertType.INFORMATION);
            res.setTitle("End Day");
            res.setHeaderText(null);
            res.setContentText("Today has ended.");
            res.showAndWait();
        } else {
            // ... user chose CANCEL or closed the dialog
            return;
        }
    }


    @FXML
    Button changeServiceFee;
    public void changeServiceFee(){
        command = 7;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab1.setText("");
        lab2.setText("");
        label1.setText("New Service Fee");
        label2.setText("");
        lab2.setVisible(false);
        label2.setVisible(false);
        lab3.setVisible(false);
        label3.setVisible(false);
    }

    @FXML
    Button changeSavingInterestThreshold;
    public void changeSavingInterestThreshold(){
        command = 8;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab1.setText("");
        lab2.setText("");
        label1.setText("New Threshold");
        label2.setText("");
        lab2.setVisible(false);
        label2.setVisible(false);
        lab3.setVisible(false);
        label3.setVisible(false);
    }

    @FXML
    Button changeSecurityOpeningThreshold;
    public void changeSecurityOpeningThreshold(){
        command = 9;
        inputP.setVisible(true);
        lab1.setVisible(true);
        label1.setVisible(true);
        lab1.setText("");
        lab2.setText("");
        label1.setText("New Threshold");
        label2.setText("");
        lab2.setVisible(false);
        label2.setVisible(false);
        lab3.setVisible(false);
        label3.setVisible(false);
    }

    @FXML
    Text stockList;

    @FXML
    TextField lab1;
    @FXML
    TextField lab2;
    @FXML
    TextField lab3;

    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;

    @FXML
    AnchorPane inputP;
    @FXML
    Button submit;

    int command;

    public void submit(){
        switch (command){
            case 1:
                String ret = bank.lookSpecific(lab1.getText(), lab2.getText());
                Alert res = new Alert(Alert.AlertType.INFORMATION);
                res.setTitle("Info");
                res.setHeaderText(null);
                res.setContentText(ret);
                res.showAndWait();
                inputP.setVisible(false);
                break;
            case 2:
                if (bank.changeStockPrice(lab1.getText(), lab2.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();

                    inputP.setVisible(false);

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                break;
            case 3:
                if (bank.modifyStockAvailable(lab1.getText(), lab2.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();

                    inputP.setVisible(false);

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                break;
            case 4:
                if (bank.addNewStock(lab1.getText(), lab2.getText(),lab3.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();
                    stockList.setText(bank.lookingAllStocks());
                    inputP.setVisible(false);


                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                break;

            case 5:
                if (bank.deleteStock(lab1.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();
                    stockList.setText(bank.lookingAllStocks());
                    inputP.setVisible(false);

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                break;
            case 6:
                if (bank.changeInterest(lab1.getText(), lab2.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();

                    inputP.setVisible(false);

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                break;
            case 7:
                if (bank.changeServiceFee(lab1.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();

                    inputP.setVisible(false);

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                break;
            case 8:
                if (bank.modifySavingLimit(lab1.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();

                    inputP.setVisible(false);

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                break;
            case 9:
                if (bank.modifySavingLimitToSecurity(lab1.getText())){
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Succeed!");
                    success.showAndWait();

                    inputP.setVisible(false);

                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Invalid Input\n\nTry Again");
                    error.showAndWait();
                }
                break;
            default:
                break;
        }
    }

}
