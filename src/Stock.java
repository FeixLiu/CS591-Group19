import java.util.ArrayList;
import java.util.List;

public class Stock {
    private double price;
    private int available;
    private String companyName;
    private int haveSold;
    private int currentSold;
    private List<Double> historyPrice;

    public Stock(double price, int available, String companyName) {
        this.price = price;
        this.available = available;
        this.companyName = companyName;
        this.haveSold = 0;
        this.currentSold = 0;
        this.historyPrice = new ArrayList<>();
        historyPrice.add(price);
    }

    public List<Double> getHistoryPrice() {
        return historyPrice;
    }

    public int getAvailable() {
        return available;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public void modifyAvailable(int delta) {
        available += delta;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void changePrice(double price) {
        this.price = price;
        this.historyPrice.add(price);
    }

    public void buyStock(int delta) {
        this.currentSold += delta;
        this.haveSold += delta;
        modifyAvailable(-delta);
    }

    public void sellStock(int delta) {
        this.currentSold -= delta;
        modifyAvailable(delta);
    }

    public int getHaveSold() {
        return haveSold;
    }

    public int getCurrentSold() {
        return currentSold;
    }
}
