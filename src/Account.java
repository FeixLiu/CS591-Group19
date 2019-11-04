import java.util.ArrayList;
import java.util.List;

class Account {
    private Id accountId;
    private List<Balance> accountBalance;
    private Password accountPassword;
    private String type;

    public Account(Id accountId, Password accountPassword, int serviceFee, String type) {
        this.accountId = accountId;
        this.accountPassword = accountPassword;
        accountBalance = new ArrayList<>();
        accountBalance.add(new Balance(serviceFee, "Dollar"));
        this.type = type;
    }

    public Id getAccountId() {
        return accountId;
    }

    public void addMoney(double money, String type) {
        if (type.equals("all")) {
            for (Balance b : accountBalance)
                b.increaseBalance(money);
            return;
        }
        for (Balance b : accountBalance) {
            if (b.getType().equals(type)) {
                b.increaseBalance(money);
                return;
            }
        }
        accountBalance.add(new Balance(money, type));
    }

    public boolean withdrawMoney(double money, String type) {
        for (Balance b : accountBalance) {
            if (b.getType().equals(type)) {
                return b.decreaseBalance(money);
            }
        }
        return false;
    }

    public String getType() {
        return type;
    }

    public List<Balance> getAccountBalance() {
        return accountBalance;
    }

    public double getMoney(String type) {
        double temp = 0.;
        for (Balance b : accountBalance) {
            if (b.getType().equals(type))
                temp += b.getMoney();
        }
        return temp;
    }

    public boolean checkPassword(String key) {
        return accountPassword.checkPassword(key);
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Account info:\n");
        str.append("Account ID: ").append(accountId.getId()).append("\n");
        for (Balance b : accountBalance) {
            str.append(type).append(" account Balance: ").append(b.getMoney()).append("\n");
        }
        return str.toString();
    }
}
