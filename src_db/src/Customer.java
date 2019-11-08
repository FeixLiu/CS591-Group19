import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Account> accounts;
    private List<Loan> loans;
    private Log log;
    private Password pass;
    private Id id;

    public Customer(Id id, Password pass, Name name) {
        super(name);
        accounts = new ArrayList<>();
        loans = new ArrayList<>();
        log = new Log();
        this.pass = pass;
        this.id = id;
    }

    public String viewAccount() {
        StringBuilder log = new StringBuilder();
        for (Account a : accounts) {
            log.append("Account id: ").append(a.getAccountId().getId()).append(" ").append(a.getType()).append(" ");
            if (!a.getType().equals("Security")) {
                List<Balance> balance = a.getAccountBalance();
                for (Balance b : balance)
                    log.append(b.getType()).append(": ").append(b.getMoney()).append("\n");
            }
            else {
                log.append(((Security) a).viewStocks());
            }
        }
        if (log.length() == 0)
            return "No account.";
        else
            return log.toString();
    }

    public Id getId() {
        return id;
    }

    public String getStocks() {
        StringBuilder sb = new StringBuilder();
        for (Account a: accounts)
            if (a.getType().equals("Security"))
                sb.append(((Security) a).viewStocks());
        if (sb.length() == 0)
            sb.append("You don't have a security account");
        return sb.toString();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public List<Loan> getAllLoan() {
        return loans;
    }

    public void removeAllLoan() {
        loans.clear();
    }

    public Account getAccount(String id) {
        for (Account ac : accounts) {
            if (ac.getAccountId().getId().equals(id))
                return ac;
        }
        return null;
    }

    public void calculateLoan() {
        for (Loan loan : loans)
            loan.calculateInterest();
    }

    public void calculateSave(double limit) {
        for (Account ac : accounts)
            if (ac.getType().equals("Saving"))
                ((Saving) ac).updateBalance(limit);
    }

    public void viewTransaction(double fee) {
        for (Account ac : accounts)
            ac.addMoney(-fee, "all");
    }

    public boolean login(String key) {
        return pass.checkPassword(key);
    }

    public float getDollar() {
        float money = 0;
        for (Account ac: accounts) {
            for (Balance b: ac.getAccountBalance()) {
                if (b.getType().equals("Dollar"))
                    money += b.getMoney();
            }
        }
        return money;
    }

    public void removeAccount(Account ac) {
        accounts.remove(ac);
    }

    public String getLog() {
        return log.getLog();
    }

    public String getBalance() {
        StringBuilder checkingBalance = new StringBuilder();
        StringBuilder savingBalance = new StringBuilder();
        StringBuilder stockBalance = new StringBuilder();
        float loanBalance = (float) 0.0;
        for (Loan loan : loans)
            loanBalance += loan.getLoan();
        for (Account acc : accounts) {
            if (acc.getType().equals("Checking")) {
                for (Balance b: acc.getAccountBalance())
                    checkingBalance.append(b.getType()).append(": ").append(b.getMoney()).append("\n");
            }
            else if (acc.getType().equals("Saving")) {
                for (Balance b : acc.getAccountBalance())
                    savingBalance.append(b.getType()).append(": ").append(b.getMoney()).append("\n");
            }
            else {
                stockBalance.append(((Security) acc).viewStocks());
            }
        }
        String log = "";
        if (checkingBalance.length() != 0)
            log += "Checking balance: " + checkingBalance.toString() + "\n";
        if (savingBalance.length() != 0)
            log += "Saving balance: " + savingBalance.toString() + "\n";
        if (stockBalance.length() != 0)
            log += "Saving balance: " + stockBalance.toString() + "\n";
        if (loanBalance != 0)
            log += "Loan balance: " + loanBalance + "\n" ;
        return log;
    }

    public double getLoan() {
        double loan = 0.0;
        for (Loan l : loans)
            loan += l.getLoan();
        return loan;
    }

    public void addLog(String log) {
        this.log.addLog(log);
    }

    public List<String> getMoneyTyep() {
        List<String> type = new ArrayList<>();
        for (Account ac: accounts) {
            for (Balance b: ac.getAccountBalance()) {
                if (!type.contains(b.getType()))
                    type.add(b.getType());
            }
        }
        return type;
    }

    public List<Account> getAllAccount() {
        return accounts;
    }
}
