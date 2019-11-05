import java.util.ArrayList;
import java.util.List;

public class FancyBank_ui implements Bank{
    private List<Customer> customers;
    private List<Account> accounts;
    private List<Customer> loans;
    private Manager manager;
    private InterestRate savingInterest;
    private InterestRate loanInterest;
    private Log log;
    private double serviceFee;
    private double savingLimit;
    private List<Balance> myBalance;
    private List<Stock> stocks;
    private Person currentCustomer;

    public FancyBank_ui() {
        //int[] bankStart(); return current service fee, saving interest, loan interest
        manager = new Manager();
        savingInterest = new InterestRate(0.05);
        loanInterest = new InterestRate(0.1);
        customers = new ArrayList<>();
        accounts = new ArrayList<>();
        loans = new ArrayList<>();
        log = new Log();
        serviceFee = 10;
        savingLimit = 100;
        myBalance = new ArrayList<>();
        stocks = new ArrayList<>();
        currentCustomer = null;
        //loadFromDatabase();
    }

    public String startDay() {
        return  "The default service fee is $10.\n" +
                "The default saving interest is 5%.\n" +
                "The default loan interest is 10%.\n" +
                "Saving account with more than 100 will earn interests.\n" +
                "Saving account with more than $500 can create security account.\n\n";
    }

    public void mainMenu() {
        System.out.println("1. Manager login.\n" +
                "2. Customer login.\n" +
                "3. Create customer.\n" +
                "4. End the day.");
        /*
        boolean flag = true;
        while (flag) {

            int op = Util.readInt();
            if (op == 1) {
                if (this.managerLogin(Config.MANAGERLOGIN))
                    this.managerOperations();
            } else if (op == 2) {
                Customer current = this.customerLogin();
                if (current != null)
                    this.customerOperations(current);
            } else if (op == 3) {
                newCustomer();
            } else if (op == 4) {
                if (this.managerLogin(Config.ENDDAY))
                    flag = false;
            } else {
                System.out.println("Wrong input.");
            }
        }
         */
        calculateLoan();
        calculateSave();
        //writeToDatabase()
    }

    public void customerOperations(Customer currentOperator) {
        System.out.println("Welcome " + currentOperator.getName().getName());
        System.out.println("1. Create checking.");
        System.out.println("2. Create saving.");
        System.out.println("3. Create security.");
        System.out.println("4. Buy stocks.");
        System.out.println("5. View stocks.");
        System.out.println("6. Sell stocks.");
        System.out.println("7. Add money to account.");
        System.out.println("8. Withdraw money to account.");
        System.out.println("9. Make transaction within my account.");
        System.out.println("10. Request a loan.");
        System.out.println("11. Pay for the loan.");
        System.out.println("12. View balance.");
        System.out.println("13. View account.");
        System.out.println("14. Close account.");
        System.out.println("15. Get transaction.");
        System.out.println("16. Logout.");
        /*
            int op = Util.readInt();

            switch (op) {
                case 1: createAccount(currentOperator, Config.NEWCHECKING); break;
                case 2: createAccount(currentOperator, Config.NEWSAVING); break;
                case 3: createAccount(currentOperator, Config.NEWSECURITY); break;
                case 4: buyStock(currentOperator); break;
                case 5: System.out.println(viewStocks(currentOperator)); break;
                case 6: sellStocks(currentOperator); break;
                case 7: modifyMoney(currentOperator, Config.ADD); break;
                case 8: modifyMoney(currentOperator, Config.WITHDRAW); break;
                case 9: makeTransaction(currentOperator); break;
                case 10: requestLoan(currentOperator); break;
                case 11: payForLoan(currentOperator); break;
                case 12: System.out.println(viewBalance(currentOperator)); break;
                case 13: System.out.println(viewAccount(currentOperator)); break;
                case 14: closeAccount(currentOperator); break;
                case 15: System.out.println(getTransaction(currentOperator)); break;
                case 16: flag = false; break;
                default: System.out.println("Wrong input!"); break;
            }
            */
    }

    public void managerOperations() {
        System.out.println("Welcome Manager.");
        System.out.println("1. Looking all customer.");
        System.out.println("2. Looking all money.");
        System.out.println("3. Look all the loan.");
        System.out.println("4. Looking specific customer.");
        System.out.println("5. Looking the customer with the most loan.");
        System.out.println("6. Looking all stocks.");
        System.out.println("7. Looking customer's stocks.");
        System.out.println("8. Change stock's price.");
        System.out.println("9. Change stock's available.");
        System.out.println("10. Add new stock.");
        System.out.println("11. Delete a stock.");
        System.out.println("12. Change the interest.");
        System.out.println("13. Calculate the loan interest.");
        System.out.println("14. Calculate the save interest.");
        System.out.println("15. Change the service fee.");
        System.out.println("16. Look the log.");
        System.out.println("17. View bank balance.");
        System.out.println("18. Logout.");
            /*int op = Util.readInt();

            switch (op) {
                case 1: System.out.println(lookAllCustomer()); break;
                case 2: System.out.println(lookAllMoney()); break;
                case 3: System.out.println(lookAllLoan()); break;
                case 4: lookSpecific(); break;
                case 5: System.out.println(lookCustomerWithMostLoan()); break;
                case 6: System.out.println(lookingAllStocks()); break;
                case 7: System.out.println(lookingCustomerStocks()); break;
                case 8: changeStockPrice(); break;
                case 9: modifyStockAvailable(); break;
                case 10: addNewStock(); break;
                case 11: deleteStock(); break;
                case 12: changeInterest(); break;
                case 13: calculateLoan(); break;
                case 14: calculateSave(); break;
                case 15: changeServiceFee(); break;
                case 16: System.out.println(getLog()); break;
                case 17: System.out.println(getBalance()); break;
                case 18: flag = false; break;
                default: System.out.println("Wrong input!"); break;
            }

             */
    }

    public void customerLogout() {
        this.currentCustomer = null;
    }

    public String lookingAllStocks() {
        //String lookingAllStocks(); return the information of all stocks (current price, current available, shares have sold, shares current sold)
        log.addLog("Manager look all stocks.\n");
        StringBuilder temp = new StringBuilder();
        for (Stock s: stocks) {
            temp.append(s.getCompanyName()).append("'s stock is ").append(s.getPrice()).
                    append(" and have ").append(s.getAvailable()).append(" available.");
            temp.append("This stock has sold ").append(s.getHaveSold()).append(" and at this time sold ").
                    append(s.getCurrentSold()).append(".\n");
        }
        if (temp.length() == 0)
            temp.append("No stocks!");
        return temp.toString();
    }

    public String lookingCustomerStocks() {
        //String lookingCustomerStocks(); return the information of customers and stocks they have bought (at which price buy how many shares)
        log.addLog("Manager look customer's bought stocks.\n");
        StringBuilder temp = new StringBuilder();
        for (Customer c: customers) {
            for (Account a: c.getAllAccount())
                if (a.getType().equals("Security"))
                    temp.append(c.getName().getName()).append(" has stocks: ").append(((Security) a).viewStocks());
        }
        if (temp.length() == 0)
            temp.append("No stocks have sold!");
        return temp.toString();
    }

    private Stock getStock(String name) {
        Stock temp = null;
        for (Stock c: stocks) {
            if (c.getCompanyName().equals(name)) {
                temp = c;
                break;
            }
        }
        return temp;
    }

    public boolean changeStockPrice(String name, String newMoney) {
        //boolean changeStockPrice(String companyName, double newPrice); change the price of a specific company, return false if no such a company
        System.out.print("Company's name: ");
        //String name = Util.readStr();
        System.out.print("New price: ");
        double price = Util.stringToDouble(newMoney);
        if (price < 0)
            return false;
        Stock stock = getStock(name);
        if (stock == null) {
            System.out.println("No such a stock");
            log.addLog("Manager change stock price fail.\n");
            return false;
        }
        else {
            log.addLog("Manager change" + stock.getCompanyName() + "'s stock price from" + stock.getPrice() + " to " + price + ".\n");
            stock.changePrice(price);
            return true;
        }
    }

    public boolean modifyStockAvailable(String name, String newAvailable) {
        //boolean modifyStockAvailable(String companyName, int newAvailable); change the available of a specific company, return false if no such a company
        System.out.print("Company's name: ");
        //String name = Util.readStr();
        System.out.print("New available: ");
        int available = Util.stringToInt(newAvailable);
        if (available < 0)
            return false;
        Stock stock = getStock(name);
        if (stock == null) {
            System.out.println("No such a stock");
            log.addLog("Manager change stock available fail.\n");
            return false;
        }
        else {
            log.addLog("Manager change" + stock.getCompanyName() + "'s stock available from" + stock.getAvailable() + " to " + available + ".\n");
            stock.setAvailable(available);
            return true;
        }
    }

    public boolean addNewStock(String name, String pri, String ava) {
        //boolean addNewStock(String companyName, int available, double price); add a new stock, return false if the company has already hold a stock
        System.out.print("Company's name: ");
        //String name = Util.readStr();
        System.out.print("New available: ");
        int available = Util.stringToInt(ava);
        if (available < 0)
            return false;
        System.out.print("New price: ");
        double price = Util.stringToDouble(pri);
        if (price < 0)
            return false;
        Stock s = getStock(name);
        if (s != null) {
            System.out.println("Already has this stock");
            log.addLog("Manager add new stock fails.\n");
            return false;
        }
        else {
            Stock news = new Stock(price, available, name);
            stocks.add(news);
            log.addLog("Manager add new stock from " + name + " with price " + price + " has " + available + " available.\n");
            return true;
        }
    }

    public boolean deleteStock(String name) {
        //boolean deleteStock(String companyName); delete a stock, return false if some customers are holding stocks of such company or no such a stock
        System.out.print("Company's name: ");
        //String name = Util.readStr();
        Stock stock = getStock(name);
        if (stock == null || stock.getCurrentSold() != 0) {
            System.out.println("No such a stock or current has have sold stocks.");
            log.addLog("Manager delete stock available fail.\n");
            return false;
        }
        else {
            log.addLog("Manager delete" + name + "'s stock.\n");
            stocks.remove(stock);
            return true;
        }
    }

    public boolean buyStock(String name, String shares, String id, String pass) {
        Person currentOperator = this.currentCustomer;
        System.out.println(lookingAllStocks());
        System.out.print("Company's name: ");
        //String name = Util.readStr();
        System.out.print("Buy how many share: ");
        int available = Util.stringToInt(shares);
        if (available < 0)
            return false;
        System.out.print("Security account's id: ");
        //String id = Util.readStr();
        System.out.print("Security account's pass: ");
        //String pass = Util.readStr();
        //String getAccount(String cId, String cName, String id, String pass);
        Account s = ((Customer) currentOperator).getAccount(id);
        if (s == null || !s.getType().equals("Security")) {
            System.out.println("Don't have a security account.");
            log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
            ((Customer) currentOperator).addLog("Fail to buy stock.\n");
            return false;
        }
        else if (!s.checkPassword(pass)) {
            System.out.println("Wrong password for security account.");
            log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
            ((Customer) currentOperator).addLog("Fail to buy stock.\n");
            return false;
        }
        else {
            Stock a = getStock(name);
            //int[] getStock(String name); check whether has a stock, if has return the available and price, if not return [-1, -1]
            if (a == null) {
                System.out.println("Don't have such stock.");
                log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
                ((Customer) currentOperator).addLog("Fail to buy stock.\n");
                return false;
            }
            else if (a.getAvailable() < available) {
                System.out.println("Don't have enough available.");
                log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
                ((Customer) currentOperator).addLog("Fail to buy stock.\n");
                return false;
            }
            else {
                double totalPrice = a.getPrice() * available;
                //String getSavingIdForSecurity(String cId, String cName, String id); return the saving account's id relating with the security account
                //boolean withDrawMoney(String cId, String cName, String id, double money, String type); with draw one
                // kind on money from one account, if sufficient money, modify the balance, if not reutrn false
                if (!((Security) s).getSave().withdrawMoney(totalPrice, "Dollar")) {
                    System.out.println("Don't have enough money.");
                    log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
                    ((Customer) currentOperator).addLog("Fail to buy stock.\n");
                    return false;
                }
                else {
                    //void buyStock(String cId, String cName, String id, String companyName, String howMany, double price);
                    // buy howMany shares stocks with price, modify the available, currentSold, haveSold information of stocks
                    // modify the information of the security account
                    ((Security) s).addStocks(a, available);
                    log.addLog(currentOperator.getName().getName() +"to buy stock.\n");
                    ((Customer) currentOperator).addLog("Buy stock.\n");
                    ((Security) s).getSave().addMoney(-serviceFee, "Dollar");
                    increseBalance("Dollar");
                }
            }
        }
        return true;
    }

    public String viewStocks() {
        Person currentOperator = this.currentCustomer;
        //String vewStocks(String cId, String cName); view all stocks the customer has bought (at which price buy how many)
        log.addLog(currentOperator.getName().getName() + "view stocks.\n");
        ((Customer) currentOperator).addLog("View stocks.\n");
        return ((Customer) currentOperator).getStocks();
    }

    public String sellStocks(String id, String pass, String name) {
        Person currentOperator = this.currentCustomer;
        System.out.print("Security account's id: ");
        //String id = Util.readStr();
        System.out.print("Security account's pass: ");
        //String pass = Util.readStr();
        Account s = ((Customer) currentOperator).getAccount(id);
        System.out.print("Company's name: ");
        //String name = Util.readStr();
        //String getAccount(String cId, String cName, String id, String pass);
        if (s == null || !s.getType().equals("Security")) {
            System.out.println("Don't have a security account.");
            log.addLog(currentOperator.getName().getName() +"Fail to sell stock.\n");
            ((Customer) currentOperator).addLog("Fail to sell stock.\n");
            return "Fail to sell stock.\n";
        }
        else if (!s.checkPassword(pass)) {
            System.out.println("Wrong password for security account.");
            log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
            ((Customer) currentOperator).addLog("Fail to buy stock.\n");
            return "Fail to sell stock.\n";
        }
        else {
            Stock a = ((Security) s).getOneStock(name);
            //double sellStock(String cId, String cName, String id, String name); sell stock of company name, return -1 if don't buy
            //that stock, or else return the money earn by the selling
            if (a == null) {
                System.out.println("Don't buy such stock.");
                log.addLog(currentOperator.getName().getName() +"Fail to sell stock.\n");
                ((Customer) currentOperator).addLog("Fail to sell stock.\n");
                return "Fail to sell stock.\n";
            }
            else {
                double money = ((Security) s).sellStock(a);
                ((Security) s).getSave().addMoney(money - serviceFee, "Dollar");
                log.addLog(currentOperator.getName().getName() + "Sell stock.\n");
                ((Customer) currentOperator).addLog("Sell stock.\n");
                increseBalance("Dollar");
                return "Earn " + money + ".\n";
            }
        }
    }

    public String lookCustomerWithMostLoan() {
        //String lookCustomerWithMostLoan(); return the information of the customer with the mose loan and the information of the loan
        double max = customers.get(0).getLoan();
        Customer a = customers.get(0);
        for (Customer c: customers) {
            double temp = c.getLoan();
            if (temp > max) {
                max = temp;
                a = c;
            }
        }
        if (max != 0)
            return a.getName().getName() + " has total loan: " + a.getLoan();
        else
            return "Nobody holds a loan.";
    }

    public boolean makeTransaction(String from, String pass, String to, String passTo, String kind, String newMoney) {
        Person currentOperator = this.currentCustomer;
        System.out.println("From which account (input id)");
        //String from = Util.readStr();
        System.out.println("Password for this account");
        //String pass = Util.readStr();
        System.out.println("To which account (input id)");
        //String to = Util.readStr();
        System.out.println("Which kind of money do you want to transfer?\n" +
                "You can input 'Dollar', 'Euro' or 'Yuan'");
        //String kind = Util.readStr();
        System.out.println("How much money to you want to transfer?");
        double money = Util.stringToDouble(newMoney);
        if (money < 0)
            return false;
        String[] key = {from, pass, to, kind, passTo};
        Account ac2 = ((Customer) currentOperator).getAccount(key[2]);
        Account ac1 = ((Customer) currentOperator).getAccount(key[0]);
        //String getAccount(String cId, String cName, String id, String pass);
        //String getAccount(String cId, String cName, String id, String pass);
        if (ac1 == null || ac2 == null) {
            log.addLog(currentOperator.getName().getName() + " account login fail.\n");
            ((Customer) currentOperator).addLog("Log into account fail \n");
            System.out.println("Customer does not have these accounts!");
            return false;
        }
        else if (ac1.getType().equals("Saving")) {
            log.addLog(currentOperator.getName().getName() + " make transaction fail.\n");
            ((Customer) currentOperator).addLog("Make Transaction fail \n");
            System.out.println("Cannot transfer from saving!");
            return false;
        }
        else {
            if (key[1].length() > 8 || !ac1.checkPassword(key[1]) || key[4].length() > 8 || !ac2.checkPassword(key[4])) {
                System.out.println("Wrong password!");
                log.addLog(currentOperator.getName().getName() + " make transaction fail.\n");
                ((Customer) currentOperator).addLog("Make Transaction fail \n");
                return false;
            }
            else if (!key[3].equals("Dollar") && !key[3].equals("Yuan") && !key[3].equals("Euro")) {
                System.out.println( "No support such kind of money!");
                log.addLog(currentOperator.getName().getName() + " make transaction fail.\n");
                ((Customer) currentOperator).addLog("Make Transaction fail \n");
                return false;
            }
            else {
                log.addLog(currentOperator.getName().getName() + " make transaction from account: "
                        + ac1.getAccountId().getId() + "to account:" + ac2.getAccountId().getId() + "\n");
                ((Customer) currentOperator).addLog("Log into account " + ac1.getAccountId().getId() + ".\n");
                if (money <= 0) {
                    System.out.println("Input money bigger than 0!");
                    log.addLog(currentOperator.getName().getName() + " make transaction fail.\n");
                    ((Customer) currentOperator).addLog("Make Transaction fail \n");
                    return false;
                }
                //boolean withDrawMoney(String cId, String cName, String id, double money, String type);
                if (!ac1.withdrawMoney(money+serviceFee, key[3])) {
                    System.out.println("No enough money!");
                    log.addLog(currentOperator.getName().getName() + " withdraw money fail.\n");
                    ((Customer) currentOperator).addLog("Fail to withdraw money account.\n");
                    return false;
                }
                //void addMoney(String cId, String cName, String id, double money, String type);
                else {
                    log.addLog(currentOperator.getName().getName() + " withdraw " + key[3] + " " + money +
                            "from account: " + ac1.getAccountId().getId() +"\n");
                    ((Customer) currentOperator).addLog("Withdraw " + key[3] + " " + money +
                            "from account: " + ac1.getAccountId().getId() +"\n");
                    log.addLog(currentOperator.getName().getName() + " add "  + key[3] + " " + money +
                            "into account: " + ac2.getAccountId().getId() +"\n");
                    ((Customer) currentOperator).addLog("Add " + key[3] + " " + + money + " into account " +
                            ac2.getAccountId().getId() +".\n");
                    ac2.addMoney(money-serviceFee, key[3]);
                    increseBalance(key[3]);
                    increseBalance(key[3]);
                    return true;
                }
            }
        }
    }

    public boolean customerLogin(String id, String name, String pass) {
        System.out.print("Customer's name: ");
        //String name = Util.readStr();
        System.out.print("Customer's id: ");
        //String id = Util.readStr();
        System.out.print("Password: ");
        //String pass = Util.readStr();

        String[] key = {name, pass, id};
        if (key[1].length() <= 8 && key[2].length() <= 4) {
            //boolean customerLogin(String id, String name, String pass); check whether there is a customer with name and id and check the password
            for (Customer c : customers) {
                if (c.getId().getId().equals(key[2]) && c.getName().getName().equals(key[0]) && c.login(key[1])) {
                    log.addLog("Customer " + key[0] + " login.\n");
                    System.out.println("On every create account," +
                            " close account," +
                            " withdraw money \n" +
                            "or get/make transaction a service fee of\n" +
                            " 10 of that kind of money will be charged.");
                    this.currentCustomer = c;
                    return true;
                }
            }
        }
        System.out.println("Wrong input format or wrong information.");
        log.addLog("Customer " + key[0] + " login fail.\n");
        return false;
    }

    public boolean managerLogin(String pass, int which) {
        System.out.print("Password: ");
        //String pass = Util.readStr();
        //boolean managerLogin(String password); check whether the password for manger correct or not
        if (pass.length() <=6 && manager.login(pass)) {
            log.addLog("Manager login.\n");
            if (which == Config.ENDDAY) {
                log.addLog("Manager end the day.");
                System.out.println("Bye-Bye, have a day.");
            }
            return true;
        }
        System.out.println("Wrong password for manager.");
        log.addLog("Manager login fail.\n");
        return false;
    }

    public boolean newCustomer(Id id, String name, String p1, String p2) {
        //Id id = newId(Config.NEWCUSTOMER);
        System.out.println("Customer's id:" + id.getId());
        System.out.println("Customer's name:");
        //String name = Util.readStr();
        System.out.println("Create password less than 8 characters");
        //String p1 = Util.readStr();
        System.out.println("Input password again less than 8 characters");
        //String p2 = Util.readStr();
        String[] key = {name, p1, p2};
        if (key[1].length() > 8 || key[2].length() > 8)
            System.out.println("Passwords too long!");
        else if (!key[1].equals(key[2]))
            System.out.println("Passwords don't match!");
        else {
            //void newCustomer(String id, String passWord, String name); add a new customer
            Password pass = new Password(key[1]);
            Customer c = new Customer(id, pass, new Name(key[0]));
            customers.add(c);
            log.addLog("New customer: " + c.getName().getName() + ".\n");
            System.out.println( "Done!");
            return true;
        }
        return false;
    }

    private boolean testDuplicate(String a, int which) {
        //int[] getAllId(int which); return all ids of account (which == Config.NEWACCOUNT) or all ids of all customer (which == Config.NEWCUSTOMER)
        if (which == Config.NEWACCOUNT) {
            for (Account c : accounts)
                if (c.getAccountId().getId().equals(a))
                    return true;
        }
        else {
            for (Customer c : customers)
                if (c.getId().getId().equals(a))
                    return true;
        }
        return false;
    }

    public Id newId(int which) {
        int a = (int)(Math.random() * 1000);
        a = Math.abs(a);
        while (testDuplicate(String.valueOf(a), which))
            a++;
        return new Id(String.valueOf(a));
    }

    public String viewAccount() {
        Person currentOperator = this.currentCustomer;
        //String viewAccount(String cId, String cName); return the information of all account
        log.addLog(currentOperator.getName().getName() + "view account.\n");
        ((Customer) currentOperator).addLog("View account.\n");
        return ((Customer) currentOperator).viewAccount();
    }

    public boolean createAccount(Id id, String p1, String p2, int which, String save, String pa) {
        //Id id = newId(Config.NEWACCOUNT);

        Person currentOperator = this.currentCustomer;

        System.out.println("The account id: " + id.getId());
        System.out.println("Create password less than 8 characters");
        //String p1 = Util.readStr();
        System.out.println("Input password again less than 8 characters");
        //String p2 = Util.readStr();
        String[] key = {p1, p2};
        if (key[1].length() > 8 || key[0].length() > 8) {
            System.out.println("Passwords too long!");
            return false;
        }
        else if (!key[1].equals(key[0])) {
            System.out.println("Passwords don't match!");
            return false;
        }
        else {
            Password pass = new Password(key[1]);
            if (which == Config.NEWCHECKING) {
                //void createChecking(String cId, String cName, String id, String pass, double initialBalance); create a checking account
                Checking c = new Checking(id, pass, -serviceFee);
                accounts.add(c);
                ((Customer) currentOperator).addAccount(c);
                log.addLog("New checking account for " + currentOperator.getName().getName() + ".\n");
                ((Customer) currentOperator).addLog("Creating checking account.\n");
            }
            else if (which == Config.NEWSAVING){
                //void createSaving(String cId, String cName, String id, String pass, double savingInterest, double initialBalance); create a saving account
                Saving s = new Saving(id, pass, savingInterest, -serviceFee);
                accounts.add(s);
                ((Customer) currentOperator).addAccount(s);
                log.addLog("New saving account for " + currentOperator.getName().getName() + ".\n");
                ((Customer) currentOperator).addLog("Creating saving account.\n");
            }
            else {
                System.out.println("Which saving account do you want to connect?");
                //String save = Util.readStr();
                System.out.println("Password for that account.");
                //String pa = Util.readStr();
                //String getAccount(String cId, String cName, String id, String pass); check whether has this account and the correctness of the password
                Account ac = ((Customer) currentOperator).getAccount(save);
                if (ac == null || !ac.checkPassword(pa)) {
                    System.out.println("Wrong password or don't have this account.");
                    log.addLog("Fail to create security account.\n");
                    ((Customer) currentOperator).addLog("Fail to create security account.\n");
                    return false;
                }
                else {
                    double money = ac.getMoney("Dollar");
                    //double getMoney(String cId, String cName, String id, String type); get one kind of money from the account
                    if (money < 500) {
                        System.out.println("Do not have enough money in saving.");
                        log.addLog("Fail to create security account.\n");
                        ((Customer) currentOperator).addLog("Fail to create security account.\n");
                        return false;
                    } else {
                        //void addMoney(String cId, String cName, double money, String type); add a one kind of money to the account
                        //void createSecurity(String cId, String cName, String id, String pass, String associateSavingId); create a security account
                        Security news = new Security(id, pass, 0, ac);
                        ac.addMoney(-serviceFee, "Dollar");
                        accounts.add(news);
                        ((Customer) currentOperator).addAccount(news);
                        log.addLog("New security account for " + currentOperator.getName().getName() + ".\n");
                        ((Customer) currentOperator).addLog("Creating security account.\n");
                    }
                }
            }
            increseBalance("Dollar");
            return true;
        }
    }

    public boolean modifyMoney(String id, String pass, String type, String newMoney, int which) {
        Person currentOperator = this.currentCustomer;
        System.out.println("Account's id");
        //String id = Util.readStr();
        System.out.println("Password for account");
        //String pass = Util.readStr();
        System.out.println("Which kind of money do you want to operate? You can input 'Dollar', 'Euro' or 'Yuan'.");
        //String type = Util.readStr();
        System.out.println("How much money to you want to change?");
        double money = Util.stringToDouble(newMoney);
        if (money < 0)
            return false;
        String[] key = {id, pass, type};
        Account ac = ((Customer) currentOperator).getAccount(key[0]);
        //String getAccount(String cId, String cName, String id, String pass);
        if (ac == null) {
            log.addLog(currentOperator.getName().getName() + " account login fail.\n");
            ((Customer) currentOperator).addLog("Customer does not have this account! \n");
            System.out.println("Customer does not have this account!");
            return false;
        }
        else {
            if (key[1].length() > 8 || !ac.checkPassword(key[1])) {
                System.out.println("Wrong password!");
                log.addLog(currentOperator.getName().getName() + " account login fail.\n");
                ((Customer) currentOperator).addLog("Log into account fail \n");
                return false;
            }
            else if (!key[2].equals("Dollar") && !key[2].equals("Yuan") && !key[2].equals("Euro")) {
                System.out.println("No support such kind of money!");
                log.addLog(currentOperator.getName().getName() + " modify money fail.\n");
                ((Customer) currentOperator).addLog("Modify money fail \n");
                return false;
            }
            else {
                log.addLog(currentOperator.getName().getName() + " log into account: "
                        + ac.getAccountId().getId() + "\n");
                ((Customer) currentOperator).addLog("Log into account " + ac.getAccountId().getId() + ".\n");
                if (which == Config.ADD) {
                    //void addMoney(String cId, String cName, String id, double money, String type);
                    ac.addMoney(money, key[2]);
                    log.addLog(currentOperator.getName().getName() + " add "  + key[2] + " " +  money +
                            "into account: " + ac.getAccountId().getId() +"\n");
                    ((Customer) currentOperator).addLog("Add " + key[2] + " " +  money + " into account " +
                            ac.getAccountId().getId() +".\n");
                    return true;
                }
                else {
                    //boolean withDrawMoney(String cId, String cName, String id, double money, String type);
                    money += serviceFee;
                    if (!ac.withdrawMoney(money, key[2])) {
                        System.out.println("No enough money!");
                        log.addLog(currentOperator.getName().getName() + " withdraw money fail.\n");
                        ((Customer) currentOperator).addLog("Fail to withdraw money account.\n");
                        return false;
                    }
                    else {
                        log.addLog(currentOperator.getName().getName() + " withdraw " + key[2] + " " + money +
                                "from account: " + ac.getAccountId().getId() +"\n");
                        ((Customer) currentOperator).addLog("Withdraw " + key[2] + " " + money +
                                "from account: " + ac.getAccountId().getId() +"\n");
                    }
                    increseBalance(key[2]);
                    return true;
                }
            }
        }
    }

    public boolean requestLoan(String newMoney) {
        Person currentOperator = this.currentCustomer;
        System.out.println("How much Dollars do you want to loan");
        double money = Util.stringToDouble(newMoney);
        if (money < 0)
            return false;
        //double getMoney(String cId, String cName, String type); return all money of one kine that the customer holds
        if (money > ((Customer) currentOperator).getDollar() / 2) {
            System.out.println( "You don't have enough collateral! Must have as twice as dollars you want to loan.");
            log.addLog(currentOperator.getName().getName() + " request loan fail.\n");
            ((Customer) currentOperator).addLog("Request loan fail \n");
            return false;
        }
        else {
            //void requestALoan(String cId, String cName, double money, double loanInterest); request a loan
            Loan loan = new Loan(money, loanInterest);
            if (!loans.contains(currentOperator))
                loans.add((Customer) currentOperator);
            ((Customer) currentOperator).addLoan(loan);
            log.addLog(currentOperator.getName().getName() + " request $" + money + " loan.\n");
            ((Customer) currentOperator).addLog("Request $" + money + " money.\n");
            return true;
        }
    }

    public boolean payForLoan(String id, String pass) {
        Person currentOperator = this.currentCustomer;
        System.out.println("From which account to pay the loan?");
        //String id = Util.readStr();
        System.out.println("Password for account");
        //String getAccount(String cId, String cName, String id, String pass);
        //String pass = Util.readStr();
        String[] key = {id, pass};
        Account ac = ((Customer) currentOperator).getAccount(key[0]);
        if (ac == null) {
            log.addLog(currentOperator.getName().getName() + " account login fail.\n");
            ((Customer) currentOperator).addLog("Log into account fail \n");
            System.out.println("Customer does not have this account!");
            return false;
        }
        else {
            if (key[1].length() > 8) {
                System.out.println("Wrong password!");
                log.addLog(currentOperator.getName().getName() + " account login fail.\n");
                ((Customer) currentOperator).addLog("Log into account fail \n");
                return false;
            }
            else {
                log.addLog(currentOperator.getName().getName() + " log into account: "
                        + ac.getAccountId().getId() + "\n");
                ((Customer) currentOperator).addLog("Log into account " + ac.getAccountId().getId() + ".\n");
                List<Balance> balance = ac.getAccountBalance();
                double money = 0;
                double loanM = ((Customer) currentOperator).getLoan();
                loanM = loanM + loanM * loanInterest.getInterest();
                //double getLoan(String cId, String cName); get all loan of the customer
                for (Balance b: balance) {
                    if (b.getType().equals("Dollar")) {
                        money = b.getMoney();
                    }
                }
                if (money < loanM) {
                    System.out.println("No enough money!");
                    log.addLog(currentOperator.getName().getName() + " pay for loan fail.\n");
                    ((Customer) currentOperator).addLog("Pay for loan fail \n");
                    return false;
                }
                else {
                    //void payForLoan(String cId, String cName); pay for all loan
                    List<Loan> loan = ((Customer) currentOperator).getAllLoan();
                    ((Customer) currentOperator).removeAllLoan();
                    for (Loan l: loan) {
                        loan.remove(l);
                    }
                    ac.withdrawMoney(loanM, "Dollar");
                    return true;
                }
            }
        }
    }

    public String viewBalance() {
        Person currentOperator = this.currentCustomer;
        //String viewBalance(String cId, String cName); return the information of all balance
        log.addLog(currentOperator.getName().getName() + " view balance.\n");
        ((Customer) currentOperator).addLog("View balance. \n");
        return ((Customer) currentOperator).getBalance();
    }

    public String getTransaction() {
        Person currentOperator = this.currentCustomer;
        //String getTransaction(String cId, String cName); return the log for the customer
        log.addLog(currentOperator.getName().getName() + " view transaction.\n");
        ((Customer) currentOperator).viewTransaction(serviceFee);
        for (String type: ((Customer) currentOperator).getMoneyTyep())
            increseBalance(type);
        ((Customer) currentOperator).addLog("View transaction. \n");
        return ((Customer) currentOperator).getLog();
    }

    public String closeAccount(String id, String pass) {
        Person currentOperator = this.currentCustomer;
        System.out.println("Which account do you want to close?");
        //String id = Util.readStr();
        System.out.println("Password for account");
        //String pass = Util.readStr();
        String[] key = {id, pass};
        //String getAccount(String cId, String cName, String id, String pass);
        Account ac = ((Customer) currentOperator).getAccount(key[0]);
        if (ac == null) {
            log.addLog(currentOperator.getName().getName() + " close account fail.\n");
            ((Customer) currentOperator).addLog("Close account fail \n");
            System.out.println("Customer does not have this account!");
            return "Close account fail \n";
        }
        else {
            if (key[1].length() > 8 || !ac.checkPassword(key[1])) {
                System.out.println("Wrong password!");
                log.addLog(currentOperator.getName().getName() + " account login fail.\n");
                ((Customer) currentOperator).addLog("Log into account fail \n");
                return "Close account fail \n";
            }
            else {
                log.addLog(currentOperator.getName().getName() + " log into account: "
                        + ac.getAccountId().getId() + "\n");
                ((Customer) currentOperator).addLog("Log into account." + ac.getAccountId().getId() + "\n");
                if (!ac.getType().equals("Security")) {
                    //double closeAccount(String cId, String cName, String id); close an account and return the money it left
                    List<Balance> balance = ac.getAccountBalance();
                    StringBuilder left = new StringBuilder();
                    for (Balance b : balance) {
                        left.append(b.getType()).append(" left: ").append(b.getMoney() - serviceFee);
                        increseBalance(b.getType());
                    }
                    ((Customer) currentOperator).removeAccount(ac);
                    ((Customer) currentOperator).addLog("Close account: " + ac.getAccountId().getId() + ".\n");
                    log.addLog(currentOperator.getName().getName() + " close account"
                            + ac.getAccountId().getId() + ".\n");
                    accounts.remove(ac);
                    System.out.println(left.toString());
                    return left.toString();
                }
                else {
                    double money = ((Security) ac).sellAllStock();
                    //double sellAllStock(String cId, String cName, String id); sell all money in a security account and close that account
                    ((Customer) currentOperator).removeAccount(ac);
                    ((Customer) currentOperator).addLog("Close account: " + ac.getAccountId().getId() + ".\n");
                    log.addLog(currentOperator.getName().getName() + " close account"
                            + ac.getAccountId().getId() + ".\n");
                    accounts.remove(ac);
                    System.out.println("Has got " + money + " Dollar from the selling.");
                    return "Has got " + money + " Dollar from the selling.";
                }
            }
        }
    }

    public String lookAllCustomer() {
        //String lookAllCustomer(); return the information of all customers including name, id
        log.addLog("Manager look all customers.\n");
        StringBuilder allMoney = new StringBuilder();
        for (Customer c : customers) {
            allMoney.append(c.getName().getName()).append(" ").append(c.getId().getId()).append("\n");
        }
        if (allMoney.length() == 0)
            return "No Customer";
        return allMoney.toString();
    }

    public String lookAllMoney() {
        //String lookAllMoney(); return the information of all customers including name and the accounts info (account's balance)
        log.addLog("Manager look all money.\n");
        StringBuilder allMoney = new StringBuilder();
        for (Customer c : customers) {
            String balance = c.getBalance();
            allMoney.append(c.getName().getName()).append(":\n").append(balance);
        }
        if (allMoney.length() == 0)
            return "No Money";
        return allMoney.toString();
    }

    public String lookSpecific(String id, String name) {
        //String lookSpecific(String id, String name); return the information of a specific customer
        System.out.println("Customer's id");
        //String id = Util.readStr();
        System.out.println("Customer's name");
        //String name = Util.readStr();
        log.addLog("Manager look specific customer " + id + " " + name + ".\n");
        boolean flag = true;
        for (Customer c : customers) {
            if (c.getId().getId().equals(id) && c.getName().getName().equals(name)) {
                return c.getName().getName() + ":\n" + c.getBalance();
            }
        }
        return "No such customer!";
    }

    public boolean changeInterest(String newSave, String newLoan){
        //void changeInterest(double saving, double loan); change the interest of saving account and loan
        System.out.print("New saving rate:");
        double sr = Util.stringToDouble(newSave);
        if (sr < 0)
            return false;
        System.out.println("New loan rate");
        double lr = Util.stringToDouble(newLoan);
        if (lr < 0)
            return false;
        log.addLog("Manager loan interest from " + loanInterest.getInterest() + " to " + lr + ".\n");
        log.addLog("Manager save interest from " + savingInterest.getInterest() + " to " + sr + ".\n");
        loanInterest.setInterest(lr);
        savingInterest.setInterest(sr);
        return true;
    }

    public String lookAllLoan() {
        //String lookAllLoan(); return all customer's who have a loan and the information of the loan
        log.addLog("Manager look all loan.\n");
        StringBuilder allMoney = new StringBuilder();
        for (Customer c : loans) {
            allMoney.append(c.getName().getName()).append(" has a loan ").append(c.getLoan()).append(".\n");
        }
        if (allMoney.length() == 0)
            return "No loan";
        return allMoney.toString();
    }

    public void calculateLoan() {
        //void calculateLoan(); update all loan by the loan interest
        log.addLog("Manager calculate loan interest.\n");
        for (Customer c : customers)
            c.calculateLoan();
    }

    public void calculateSave() {
        //void calculateSave(); update all save more than saving limit by the save interest
        log.addLog("Manager calculate save interest.\n");
        for (Customer c : customers)
            c.calculateSave(savingLimit);
    }

    public boolean changeServiceFee(String newServiceFee) {
        //void changeServiceFee(int newService); change the service fee of the bank
        System.out.print("New service fee");
        double a = Util.stringToDouble(newServiceFee);
        if (a < 0)
            return false;
        log.addLog("Manager change service fee from " + serviceFee + " to " + a + ".\n");
        serviceFee = a;
        return true;
    }

    private void increseBalance(String type) {
        //void increaseBalance(String type); increase the bank's balance with one serviceFee
        for (Balance b: myBalance) {
            if (b.getType().equals(type)) {
                b.increaseBalance(serviceFee);
                return;
            }
        }
        myBalance.add(new Balance(serviceFee, type));
    }

    public String getBalance() {
        //String getBalance(); return the balance of the bank
        StringBuilder balance = new StringBuilder();
        for (Balance b: myBalance)
            balance.append(b.getType()).append(": ").append(b.getMoney()).append("\n");
        if (balance.length() == 0)
            return "No balance";
        return balance.toString();
    }

    public String getLog() {
        //String getLog(); return the log of the bank
        log.addLog("Manager get log.\n");
        return log.getLog();
    }
}
