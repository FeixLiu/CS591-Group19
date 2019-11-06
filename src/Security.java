import java.util.HashMap;

public class Security extends Account{
    private HashMap<Stock, HashMap<Double, Integer>> stocks;
    private Account save;

    public Security(Id accountId, Password accountPassword, double serviceFee, Account save) {
        super(accountId, accountPassword, serviceFee, "Security");
        stocks = new HashMap<>();
        this.save = save;
    }

    public void setBought(HashMap<Stock, HashMap<Double, Integer>> s) {
        stocks = s;
    }

    public Account getSave() {
        return save;
    }

    public double sellAllStock() {
        double temp = 0.;
        for (Stock stock: stocks.keySet()) {
            HashMap<Double, Integer> share = stocks.get(stock);
            double price = stock.getPrice();
            int a = 0;
            for (double pre: share.keySet()) {
                temp += (price - pre) * share.get(pre);
                a += share.get(pre);
            }
            stock.sellStock(a);
        }
        stocks.clear();
        save.addMoney(temp, "Dollar");
        return temp;
    }

    public void addStocks(Stock stock, int newBuy) {
        if (!stocks.containsKey(stock))
            stocks.put(stock, new HashMap<>());
        double price = stock.getPrice();
        HashMap<Double, Integer> pair = stocks.get(stock);
        pair.put(price, pair.getOrDefault(price, 0) + newBuy);
        stocks.put(stock, pair);
        stock.buyStock(newBuy);
    }

    public String viewStocks() {
        StringBuilder temp = new StringBuilder();
        for (Stock s: stocks.keySet()) {
            temp.append("You have ").append(s.getCompanyName()).append("'s stock.\n");
            temp.append("The current price is ").append(s.getPrice()).append("\n");
            HashMap<Double, Integer> share = stocks.get(s);
            for (double pre: share.keySet()) {
                temp.append("You have ").append(share.get(pre)).append(" shares with ").append(pre).append(" price.\n");
            }
        }
        if (temp.length() == 0)
            temp.append("You don't have stocks.");
        return temp.toString();
    }

    public Stock getOneStock(String companyName) {
        for (Stock s: stocks.keySet()) {
            if (s.getCompanyName().equals(companyName))
                return s;
        }
        return null;
    }

    public double sellStock(Stock stock) {
        HashMap<Double, Integer> share = stocks.remove(stock);
        double price = stock.getPrice();
        int a = 0;
        double earn = 0.;
        for (double pre: share.keySet()) {
            earn += price * share.get(pre);
            a += share.get(pre);
        }
        stock.sellStock(a);
        return earn;
    }
}
