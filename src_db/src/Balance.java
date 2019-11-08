public class Balance {
    private double money;
    private Currency type;

    public Balance(double money, String type) {
        this.money = money;
        this.type = new Currency(type);
    }

    public void increaseBalance(double delta) {
        money += delta;
    }

    public String getType() {
        return type.getCurrencyType();
    }

    public boolean decreaseBalance(double delta) {
        if (delta > money)
            return false;
        money -= delta;
        return true;
    }

    public double getMoney() {
        return money;
    }
}
