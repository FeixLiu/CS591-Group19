public class Stock {
    private double price;
    private int available;
    private String companyName;
    private int haveSold;
    private int currentSold;

    public Stock(double price, int available, String companyName) {
        this.price = price;
        this.available = available;
        this.companyName = companyName;
        this.haveSold = 0;
        this.currentSold = 0;
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
