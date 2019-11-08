//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class FancyBank implements Bank{
//    private List<Customer> customers;
//    private List<Account> accounts;
//    private List<Customer> loans;
//    private Manager manager;
//    private InterestRate savingInterest;
//    private InterestRate loanInterest;
//    private Log log;
//    private double serviceFee;
//    private double savingLimit;
//    private List<Balance> myBalance;
//    private List<Stock> stocks;
//    private Customer currentCustomer;
//    private DataBase db;
//    private Id id;
//    private int date;
//
//    public FancyBank() {
//        db = new DataBase();
//        //int[] bankStart(); return current service fee, saving interest, loan interest
//        //double[] information = db.bankStart();
//        manager = new Manager();
//        StartInfo startInfo = db.startInfo();
//        loadData(startInfo);
//        log = new Log();
//        savingLimit = 100;
//        id = new Id("bank");
//        //loadFromDatabase();
//    }
//
//    private void loadData(StartInfo si) {
//        savingInterest = new InterestRate(si.bankInfo.get(1));
//        loanInterest = new InterestRate(si.bankInfo.get(2));
//        serviceFee = si.bankInfo.get(0);
//        date = (int)((double) si.bankInfo.get(6));
//        myBalance = new ArrayList<>();
//        myBalance.add(new Balance(si.bankInfo.get(3), "Dollar"));
//        myBalance.add(new Balance(si.bankInfo.get(4), "Euro"));
//        myBalance.add(new Balance(si.bankInfo.get(5), "Yuan"));
//        stocks = new ArrayList<>();
//        for (HashMap<String, List<Double>> stock: si.stockInfo) {
//            for (String name: stock.keySet()) {
//                List<Double> info = stock.get(name);
//                Stock newStock = new Stock(info.get(1), (int)((double) info.get(0)), name);
//                newStock.setSold((int)((double) info.get(2)), (int)((double) info.get(3)));
//                for (int i = info.size() - 1; i > 3; i--)
//                    newStock.insertHistory(info.get(i));
//                stocks.add(newStock);
//            }
//        }
//        customers = new ArrayList<>();
//        accounts = new ArrayList<>();
//        loans = new ArrayList<>();
//        for (String id: si.customerInfo.keySet()) {
//            CustomerInfo ci = si.customerInfo.get(id);
//            Customer newCustomer = new Customer(new Id(id), new Password(ci.pass), new Name(ci.name));
//            customers.add(newCustomer);
//            for (double loan: ci.loanInfo) {
//                Loan newLoan = new Loan(loan, loanInterest);
//                newCustomer.addLoan(newLoan);
//                if (!loans.contains(newCustomer))
//                    loans.add(newCustomer);
//            }
//            for (AccountInfo ai: ci.accountInfo) {
//                Account newAccount;
//                if (ai.type.equals("Checking"))
//                    newAccount = new Checking(new Id(ai.id), new Password(ai.pass), serviceFee);
//                else
//                    newAccount = new Saving(new Id(ai.id), new Password(ai.pass), savingInterest, serviceFee);
//                for (String type: ai.balanceInfo.keySet())
//                    newAccount.addMoney(ai.balanceInfo.get(type), type);
//                newCustomer.addAccount(newAccount);
//                accounts.add(newAccount);
//            }
//            for (SecurityInfo sei: ci.securityInfo) {
//                Account save = newCustomer.getAccount(sei.saveId);
//                Security newAccount = new Security(new Id(sei.id), new Password(sei.pass), serviceFee, save);
//                HashMap<Stock, HashMap<Double, Integer>> bought = new HashMap<>();
//                for (String name: sei.stocks.keySet()) {
//                    Stock s = getStock(name);
//                    bought.put(s, sei.stocks.get(name));
//                }
//                newAccount.setBought(bought);
//                newCustomer.addAccount(newAccount);
//                accounts.add(newAccount);
//            }
//        }
//    }
//
//    public String startDay() {
//        return  "The default service fee is $" + this.serviceFee + ".\n" +
//                "The default saving interest is " + this.savingInterest.getInterest() + "%.\n" +
//                "The default loan interest is " + this.loanInterest.getInterest() + "%.\n" +
//                "Saving account with more than " + this.savingLimit + " will earn interests.\n" +
//                "Saving account with more than $500 can create security account.\n\n";
//    }
//
//    public void mainMenu() {
//        boolean flag = true;
//        while (flag) {
//            System.out.println("1. Manager login.\n" +
//                    "2. Customer login.\n" +
//                    "3. Create customer.\n" +
//                    "4. End the day.");
//            int op = Util.readInt();
//            if (op == 1) {
//                if (this.managerLogin(Config.MANAGERLOGIN))
//                    this.managerOperations();
//            } else if (op == 2) {
//                Customer current = this.customerLogin();
//                if (current != null)
//                    this.customerOperations(current);
//            } else if (op == 3) {
//                newCustomer();
//            } else if (op == 4) {
//                if (this.managerLogin(Config.ENDDAY))
//                    flag = false;
//            } else {
//                System.out.println("Wrong input.");
//            }
//        }
//        calculateLoan();
//        calculateSave();
//        db.endDay();
//        //writeToDatabase()
//    }
//
//    public void customerOperations(Customer currentOperator) {
//        boolean flag = true;
//        while (flag) {
//            System.out.println("Welcome " + currentOperator.getName().getName());
//            System.out.println("1. Create checking.");
//            System.out.println("2. Create saving.");
//            System.out.println("3. Create security.");
//            System.out.println("4. Buy stocks.");
//            System.out.println("5. View stocks.");
//            System.out.println("6. Sell stocks.");
//            System.out.println("7. Add money to account.");
//            System.out.println("8. Withdraw money to account.");
//            System.out.println("9. Make transaction within my account.");
//            System.out.println("10. Request a loan.");
//            System.out.println("11. Pay for the loan.");
//            System.out.println("12. View balance.");
//            System.out.println("13. View account.");
//            System.out.println("14. Close account.");
//            System.out.println("15. Get transaction.");
//            System.out.println("16. Logout.");
//
//            int op = Util.readInt();
//
//            switch (op) {
//                case 1: createAccount(currentOperator, Config.NEWCHECKING); break;
//                case 2: createAccount(currentOperator, Config.NEWSAVING); break;
//                case 3: createAccount(currentOperator, Config.NEWSECURITY); break;
//                case 4: buyStock(currentOperator); break;
//                case 5: System.out.println(viewStocks(currentOperator)); break;
//                case 6: sellStocks(currentOperator); break;
//                case 7: modifyMoney(currentOperator, Config.ADD); break;
//                case 8: modifyMoney(currentOperator, Config.WITHDRAW); break;
//                case 9: makeTransaction(currentOperator); break;
//                case 10: requestLoan(currentOperator); break;
//                case 11: payForLoan(currentOperator); break;
//                case 12: System.out.println(viewBalance(currentOperator)); break;
//                case 13: System.out.println(viewAccount(currentOperator)); break;
//                case 14: closeAccount(currentOperator); break;
//                case 15: System.out.println(getTransaction(currentOperator)); break;
//                case 16: flag = false; break;
//                default: System.out.println("Wrong input!"); break;
//            }
//        }
//    }
//
//    public void managerOperations() {
//        boolean flag = true;
//        while (flag) {
//            System.out.println("Welcome Manager.");
//            System.out.println("1. Looking all customer.");
//            System.out.println("2. Looking all money.");
//            System.out.println("3. Look all the loan.");
//            System.out.println("4. Looking specific customer.");
//            System.out.println("5. Looking the customer with the most loan.");
//            System.out.println("6. Looking all stocks.");
//            System.out.println("7. Looking customer's stocks.");
//            System.out.println("8. Change stock's price.");
//            System.out.println("9. Change stock's available.");
//            System.out.println("10. Add new stock.");
//            System.out.println("11. Delete a stock.");
//            System.out.println("12. Change the interest.");
//            System.out.println("13. Calculate the loan interest.");
//            System.out.println("14. Calculate the save interest.");
//            System.out.println("15. Change the service fee.");
//            System.out.println("16. Look the log.");
//            System.out.println("17. View bank balance.");
//            System.out.println("18. Logout.");
//            int op = Util.readInt();
//
//            switch (op) {
//                case 1: System.out.println(lookAllCustomer()); break;
//                case 2: System.out.println(lookAllMoney()); break;
//                case 3: System.out.println(lookAllLoan()); break;
//                case 4: lookSpecific(); break;
//                case 5: System.out.println(lookCustomerWithMostLoan()); break;
//                case 6: System.out.println(lookingAllStocks()); break;
//                case 7: System.out.println(lookingCustomerStocks()); break;
//                case 8: changeStockPrice(); break;
//                case 9: modifyStockAvailable(); break;
//                case 10: addNewStock(); break;
//                case 11: deleteStock(); break;
//                case 12: changeInterest(); break;
//                case 13: calculateLoan(); break;
//                case 14: calculateSave(); break;
//                case 15: changeServiceFee(); break;
//                case 16: System.out.println(getLog()); break;
//                case 17: System.out.println(getBalance()); break;
//                case 18: flag = false; break;
//                default: System.out.println("Wrong input!"); break;
//            }
//        }
//    }
//
//    private String lookingAllStocks() {
//        //String lookingAllStocks(); return the information of all stocks (current price, current available, shares have sold, shares current sold)
//        log.addLog("Manager look all stocks.\n");
//        db.addLog(id.getId(), "Manager look all stocks.\n", date);
//        StringBuilder temp = new StringBuilder();
//        for (Stock s: stocks) {
//            temp.append(s.getCompanyName()).append("'s stock is ").append(s.getPrice()).
//                    append(" and have ").append(s.getAvailable()).append(" available.");
//            temp.append("This stock has sold ").append(s.getHaveSold()).append(" and at this time sold ").
//                    append(s.getCurrentSold()).append(".\n");
//        }
//        if (temp.length() == 0)
//            temp.append("No stocks!");
//        return temp.toString();
//    }
//
//    private String lookingCustomerStocks() {
//        //String lookingCustomerStocks(); return the information of customers and stocks they have bought (at which price buy how many shares)
//        log.addLog("Manager look customer's bought stocks.\n");
//        StringBuilder temp = new StringBuilder();
//        for (Customer c: customers) {
//            for (Account a: c.getAllAccount())
//                if (a.getType().equals("Security"))
//                    temp.append(c.getName().getName()).append(" has stocks: ").append(((Security) a).viewStocks());
//        }
//        if (temp.length() == 0)
//            temp.append("No stocks have sold!");
//        return temp.toString();
//    }
//
//    private Stock getStock(String name) {
//        Stock temp = null;
//        for (Stock c: stocks) {
//            if (c.getCompanyName().equals(name)) {
//                temp = c;
//                break;
//            }
//        }
//        return temp;
//    }
//
//    private void changeStockPrice() {
//        //boolean changeStockPrice(String companyName, double newPrice); change the price of a specific company, return false if no such a company
//        System.out.print("Company's name: ");
//        String name = Util.readStr();
//        System.out.print("New price: ");
//        double price = Util.readDouble();
//        Stock stock = getStock(name);
//        if (stock == null) {
//            System.out.println("No such a stock");
//            log.addLog("Manager change stock price fail.\n");
//        }
//        else {
//            log.addLog("Manager change" + stock.getCompanyName() + "'s stock price from" + stock.getPrice() + " to " + price + ".\n");
//            stock.changePrice(price);
//            db.changeStockPrice(name, price);
//        }
//    }
//
//    private void modifyStockAvailable() {
//        //boolean modifyStockAvailable(String companyName, int newAvailable); change the available of a specific company, return false if no such a company
//        System.out.print("Company's name: ");
//        String name = Util.readStr();
//        System.out.print("New available: ");
//        int available = Util.readInt();
//        Stock stock = getStock(name);
//        if (stock == null) {
//            System.out.println("No such a stock");
//            log.addLog("Manager change stock available fail.\n");
//        }
//        else {
//            log.addLog("Manager change" + stock.getCompanyName() + "'s stock available from" + stock.getAvailable() + " to " + available + ".\n");
//            stock.setAvailable(available);
//            db.modifyStockAvailable(name, available);
//        }
//    }
//
//    private void addNewStock() {
//        //boolean addNewStock(String companyName, int available, double price); add a new stock, return false if the company has already hold a stock
//        System.out.print("Company's name: ");
//        String name = Util.readStr();
//        System.out.print("New available: ");
//        int available = Util.readInt();
//        System.out.print("New price: ");
//        double price = Util.readDouble();
//        Stock s = getStock(name);
//        if (s != null) {
//            System.out.println("Already has this stock");
//            log.addLog("Manager add new stock fails.\n");
//        }
//        else {
//            Stock news = new Stock(price, available, name);
//            stocks.add(news);
//            log.addLog("Manager add new stock from " + name + " with price " + price + " has " + available + " available.\n");
//            db.addNewStock(name, available, price);
//        }
//    }
//
//    private void deleteStock() {
//        //boolean deleteStock(String companyName); delete a stock, return false if some customers are holding stocks of such company or no such a stock
//        System.out.print("Company's name: ");
//        String name = Util.readStr();
//        Stock stock = getStock(name);
//        if (stock == null || stock.getCurrentSold() != 0) {
//            System.out.println("No such a stock or current has have sold stocks.");
//            log.addLog("Manager delete stock available fail.\n");
//        }
//        else {
//            log.addLog("Manager delete" + name + "'s stock.\n");
//            stocks.remove(stock);
//            db.deleteStock(name);
//        }
//    }
//
//    private void buyStock(Person currentOperator) {
//        System.out.println(lookingAllStocks());
//        System.out.print("Company's name: ");
//        String name = Util.readStr();
//        System.out.print("Buy how many share: ");
//        int available = Util.readInt();
//        System.out.print("Security account's id: ");
//        String id = Util.readStr();
//        System.out.print("Security account's pass: ");
//        String pass = Util.readStr();
//        //String getAccount(String cId, String cName, String id, String pass);
//        Account s = ((Customer) currentOperator).getAccount(id);
//        //String type = db.getAccount(((Customer) currentOperator).getId().getId(), ((Customer) currentOperator).getName().getName(), id, pass);
//        if (s == null || !s.getType().equals("Security")) {
//            System.out.println("Don't have a security account.");
//            log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
//            ((Customer) currentOperator).addLog("Fail to buy stock.\n");
//        }
//        else if (!s.checkPassword(pass)) {
//            System.out.println("Wrong password for security account.");
//            log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
//            ((Customer) currentOperator).addLog("Fail to buy stock.\n");
//        }
//        else {
//            Stock a = getStock(name);
//            //int[] getStock(String name); check whether has a stock, if has return the available and price, if not return [-1, -1]
//            if (a == null) {
//                System.out.println("Don't have such stock.");
//                log.addLog(currentOperator.getName().getName() + "Fail to buy stock.\n");
//                ((Customer) currentOperator).addLog("Fail to buy stock.\n");
//            } else if (a.getAvailable() < available) {
//                System.out.println("Don't have enough available.");
//                log.addLog(currentOperator.getName().getName() + "Fail to buy stock.\n");
//                ((Customer) currentOperator).addLog("Fail to buy stock.\n");
//            } else {
//                double totalPrice = a.getPrice() * available;
//                //String getSavingIdForSecurity(String cId, String cName, String id); return the saving account's id relating with the security account
//                //boolean withDrawMoney(String cId, String cName, String id, double money, String type); with draw one
//                // kind on money from one account, if sufficient money, modify the balance, if not reutrn false
//                if (!((Security) s).getSave().withdrawMoney(totalPrice, "Dollar")) {
//                    System.out.println("Don't have enough money.");
//                    log.addLog(currentOperator.getName().getName() + "Fail to buy stock.\n");
//                    ((Customer) currentOperator).addLog("Fail to buy stock.\n");
//                } else {
//                    //void buyStock(String cId, String cName, String id, String companyName, String howMany, double price);
//                    // buy howMany shares stocks with price, modify the available, currentSold, haveSold information of stocks
//                    // modify the information of the security account
//                    db.withDrawMoney(
//                            ((Customer) currentOperator).getId().getId(),
//                            currentOperator.getName().getName(),
//                            s.getAccountId().getId(),
//                            totalPrice,
//                            "Dollar"
//                    );
//                    db.buyStock(
//                            ((Customer) currentOperator).getId().getId(),
//                            currentOperator.getName().getName(),
//                            s.getAccountId().getId(),
//                            name, available, a.getPrice()
//                    );
//                    ((Security) s).addStocks(a, available);
//                    log.addLog(currentOperator.getName().getName() + "to buy stock.\n");
//                    ((Customer) currentOperator).addLog("Buy stock.\n");
//                    ((Security) s).getSave().addMoney(-serviceFee, "Dollar");
//                    increseBalance("Dollar");
//                }
//            }
//        }
//    }
//
//    private String viewStocks(Person currentOperator) {
//        //String vewStocks(String cId, String cName); view all stocks the customer has bought (at which price buy how many)
//        log.addLog(currentOperator.getName().getName() + "view stocks.\n");
//        ((Customer) currentOperator).addLog("View stocks.\n");
//        return ((Customer) currentOperator).getStocks();
//    }
//
//    private void sellStocks(Person currentOperator) {
//        System.out.print("Security account's id: ");
//        String id = Util.readStr();
//        System.out.print("Security account's pass: ");
//        String pass = Util.readStr();
//        Account s = ((Customer) currentOperator).getAccount(id);
//        System.out.print("Company's name: ");
//        String name = Util.readStr();
//        //String getAccount(String cId, String cName, String id, String pass);
//        if (s == null || !s.getType().equals("Security")) {
//            System.out.println("Don't have a security account.");
//            log.addLog(currentOperator.getName().getName() +"Fail to sell stock.\n");
//            ((Customer) currentOperator).addLog("Fail to sell stock.\n");
//        }
//        else if (!s.checkPassword(pass)) {
//            System.out.println("Wrong password for security account.");
//            log.addLog(currentOperator.getName().getName() +"Fail to buy stock.\n");
//            ((Customer) currentOperator).addLog("Fail to buy stock.\n");
//        }
//        else {
//            Stock a = ((Security) s).getOneStock(name);
//            //double sellStock(String cId, String cName, String id, String name); sell stock of company name, return -1 if don't buy
//            //that stock, or else return the money earn by the selling
//            if (a == null) {
//                System.out.println("Don't buy such stock.");
//                log.addLog(currentOperator.getName().getName() +"Fail to sell stock.\n");
//                ((Customer) currentOperator).addLog("Fail to sell stock.\n");
//            }
//            else {
//                double money = ((Security) s).sellStock(a);
//                db.sellStock(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), id, name);
//                ((Security) s).getSave().addMoney(money - serviceFee, "Dollar");
//                db.addMoney(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), id, money, "Dollar");
//                System.out.println("Earn " + money + ".\n");
//                log.addLog(currentOperator.getName().getName() + "Sell stock.\n");
//                ((Customer) currentOperator).addLog("Sell stock.\n");
//                increseBalance("Dollar");
//            }
//        }
//    }
//
//    private String lookCustomerWithMostLoan() {
//        //String lookCustomerWithMostLoan(); return the information of the customer with the mose loan and the information of the loan
//        double max = customers.get(0).getLoan();
//        Customer a = customers.get(0);
//        for (Customer c: customers) {
//            double temp = c.getLoan();
//            if (temp > max) {
//                max = temp;
//                a = c;
//            }
//        }
//        return a.getName().getName() + " has total loan: " + a.getLoan();
//    }
//
//    private void makeTransaction(Person currentOperator) {
//        System.out.println("From which account (input id)");
//        String from = Util.readStr();
//        System.out.println("Password for this account");
//        String pass = Util.readStr();
//        System.out.println("To which account (input id)");
//        String to = Util.readStr();
//        System.out.println("Password for this account");
//        String pass2 = Util.readStr();
//        System.out.println("Which kind of money do you want to transfer?\n" +
//                "You can input 'Dollar', 'Euro' or 'Yuan'");
//        String kind = Util.readStr();
//        System.out.println("How much money to you want to transfer?");
//        double money = Util.readDouble();
//        String[] key = {from, pass, to, kind, pass2};
//        Account ac1 = ((Customer) currentOperator).getAccount(key[0]);
//        Account ac2 = ((Customer) currentOperator).getAccount(key[2]);
//        //String getAccount(String cId, String cName, String id, String pass);
//        //String getAccount(String cId, String cName, String id, String pass);
//        if (ac1 == null || ac2 == null) {
//            log.addLog(currentOperator.getName().getName() + " account login fail.\n");
//            ((Customer) currentOperator).addLog("Log into account fail \n");
//            System.out.println("Customer does not have these accounts!");
//        }
//        else if (ac1.getType().equals("Saving")) {
//            log.addLog(currentOperator.getName().getName() + " make transaction fail.\n");
//            ((Customer) currentOperator).addLog("Make Transaction fail \n");
//            System.out.println("Cannot transfer from saving!");
//        }
//        else {
//            if (key[1].length() > 8 || !ac1.checkPassword(key[1]) || key[4].length() > 8 || !ac2.checkPassword(key[4])) {
//                System.out.println("Wrong password!");
//                log.addLog(currentOperator.getName().getName() + " make transaction fail.\n");
//                ((Customer) currentOperator).addLog("Make Transaction fail \n");
//            }
//            else if (!key[3].equals("Dollar") && !key[3].equals("Yuan") && !key[3].equals("Euro")) {
//                System.out.println( "No support such kind of money!");
//                log.addLog(currentOperator.getName().getName() + " make transaction fail.\n");
//                ((Customer) currentOperator).addLog("Make Transaction fail \n");
//            }
//            else {
//                log.addLog(currentOperator.getName().getName() + " make transaction from account: "
//                        + ac1.getAccountId().getId() + "to account:" + ac2.getAccountId().getId() + "\n");
//                ((Customer) currentOperator).addLog("Log into account " + ac1.getAccountId().getId() + ".\n");
//                if (money <= 0) {
//                    System.out.println("Input money bigger than 0!");
//                    log.addLog(currentOperator.getName().getName() + " make transaction fail.\n");
//                    ((Customer) currentOperator).addLog("Make Transaction fail \n");
//                }
//                //boolean withDrawMoney(String cId, String cName, String id, double money, String type);
//                if (!ac1.withdrawMoney(money+serviceFee, key[3])) {
//                    System.out.println("No enough money!");
//                    log.addLog(currentOperator.getName().getName() + " withdraw money fail.\n");
//                    ((Customer) currentOperator).addLog("Fail to withdraw money account.\n");
//                }
//                //void addMoney(String cId, String cName, String id, double money, String type);
//                else {
//                    db.withDrawMoney(
//                            ((Customer) currentOperator).getId().getId(),
//                            currentOperator.getName().getName(),
//                            from,
//                            money,
//                            kind
//                    );
//                    db.addMoney(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), to, money, kind);
//                    log.addLog(currentOperator.getName().getName() + " withdraw " + key[3] + " " + money +
//                            "from account: " + ac1.getAccountId().getId() +"\n");
//                    ((Customer) currentOperator).addLog("Withdraw " + key[3] + " " + money +
//                            "from account: " + ac1.getAccountId().getId() +"\n");
//                    log.addLog(currentOperator.getName().getName() + " add "  + key[3] + " " + money +
//                            "into account: " + ac2.getAccountId().getId() +"\n");
//                    ((Customer) currentOperator).addLog("Add " + key[3] + " " + + money + " into account " +
//                            ac2.getAccountId().getId() +".\n");
//                    ac2.addMoney(money-serviceFee, key[3]);
//                    increseBalance(key[3]);
//                    increseBalance(key[3]);
//                }
//            }
//        }
//    }
//
//    public Customer customerLogin() {
//        System.out.print("Customer's name: ");
//        String name = Util.readStr();
//        System.out.print("Customer's id: ");
//        String id = Util.readStr();
//        System.out.print("Password: ");
//        String pass = Util.readStr();
//
//        String[] key = {name, pass, id};
//        if (key[1].length() <= 8 && key[2].length() <= 4) {
//            //boolean customerLogin(String id, String name, String pass); check whether there is a customer with name and id and check the password
//            for (Customer c : customers) {
//                if (c.getId().getId().equals(key[2]) && c.getName().getName().equals(key[0]) && c.login(key[1])) {
//                    log.addLog("Customer " + key[0] + " login.\n");
//                    System.out.println("On every create account," +
//                            " close account," +
//                            " withdraw money \n" +
//                            "or get/make transaction a service fee of\n" +
//                            " 10 of that kind of money will be charged.");
//                    return c;
//                }
//            }
//        }
//        System.out.println("Wrong input format or wrong information.");
//        log.addLog("Customer " + key[0] + " login fail.\n");
//        return null;
//    }
//
//    public boolean managerLogin(int which) {
//        System.out.print("Password: ");
//        String pass = Util.readStr();
//        //boolean managerLogin(String password); check whether the password for manger correct or not
//        if (pass.length() <=6 && manager.login(pass)) {
//            log.addLog("Manager login.\n");
//            if (which == Config.ENDDAY) {
//                log.addLog("Manager end the day.");
//                System.out.println("Bye-Bye, have a day.");
//            }
//            return true;
//        }
//        System.out.println("Wrong password for manager.");
//        log.addLog("Manager login fail.\n");
//        return false;
//    }
//
//    public void newCustomer() {
//        Id id = newId(Config.NEWCUSTOMER);
//        System.out.println("Customer's id:" + id.getId());
//        System.out.println("Customer's name:");
//        String name = Util.readStr();
//        System.out.println("Create password less than 8 characters");
//        String p1 = Util.readStr();
//        System.out.println("Input password again less than 8 characters");
//        String p2 = Util.readStr();
//        String[] key = {name, p1, p2};
//        if (key[1].length() > 8 || key[2].length() > 8)
//            System.out.println("Passwords too long!");
//        else if (!key[1].equals(key[2]))
//            System.out.println("Passwords don't match!");
//        else {
//            //void newCustomer(String id, String passWord, String name); add a new customer
//            db.newCustomer(id, p1, name);
//            Password pass = new Password(key[1]);
//            Customer c = new Customer(id, pass, new Name(key[0]));
//            customers.add(c);
//            log.addLog("New customer: " + c.getName().getName() + ".\n");
//            System.out.println( "Done!");
//        }
//    }
//
//    private boolean testDuplicate(String a, int which) {
//        //int[] getAllId(int which); return all ids of account (which == Config.NEWACCOUNT) or all ids of all customer (which == Config.NEWCUSTOMER)
//        if (which == Config.NEWACCOUNT) {
//            for (Account c : accounts)
//                if (c.getAccountId().getId().equals(a))
//                    return true;
//        }
//        else {
//            for (Customer c : customers)
//                if (c.getId().getId().equals(a))
//                    return true;
//        }
//        return false;
//    }
//
//    public Id newId(int which) {
//        int a = (int)(Math.random() * 1000);
//        a = Math.abs(a);
//        while (testDuplicate(String.valueOf(a), which))
//            a++;
//        return new Id(String.valueOf(a));
//    }
//
//    private String viewAccount(Person currentOperator) {
//        //String viewAccount(String cId, String cName); return the information of all account
//        log.addLog(currentOperator.getName().getName() + "view account.\n");
//        ((Customer) currentOperator).addLog("View account.\n");
//        return ((Customer) currentOperator).viewAccount();
//    }
//
//    private void createAccount(Person currentOperator, int which) {
//        Id id = newId(Config.NEWACCOUNT);
//
//        System.out.println("The account id: " + id.getId());
//        System.out.println("Create password less than 8 characters");
//        String p1 = Util.readStr();
//        System.out.println("Input password again less than 8 characters");
//        String p2 = Util.readStr();
//        String[] key = {p1, p2};
//        if (key[1].length() > 8 || key[0].length() > 8)
//            System.out.println("Passwords too long!");
//        else if (!key[1].equals(key[0]))
//            System.out.println("Passwords don't match!");
//        else {
//            Password pass = new Password(key[1]);
//            if (which == Config.NEWCHECKING) {
//                //void createChecking(String cId, String cName, String id, String pass, double initialBalance); create a checking account
//                db.createAccount(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), id.getId(), p1, -serviceFee, "Checking");
//                Checking c = new Checking(id, pass, -serviceFee);
//                accounts.add(c);
//                ((Customer) currentOperator).addAccount(c);
//                log.addLog("New checking account for " + currentOperator.getName().getName() + ".\n");
//                ((Customer) currentOperator).addLog("Creating checking account.\n");
//            }
//            else if (which == Config.NEWSAVING){
//                //void createSaving(String cId, String cName, String id, String pass, double savingInterest, double initialBalance); create a saving account
//                db.createAccount(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), id.getId(), p1, -serviceFee, "Saving");
//                Saving s = new Saving(id, pass, savingInterest, -serviceFee);
//                accounts.add(s);
//                ((Customer) currentOperator).addAccount(s);
//                log.addLog("New saving account for " + currentOperator.getName().getName() + ".\n");
//                ((Customer) currentOperator).addLog("Creating saving account.\n");
//            }
//            else {
//                System.out.println("Which saving account do you want to connect?");
//                String save = Util.readStr();
//                System.out.println("Password for that account.");
//                String pa = Util.readStr();
//                //String getAccount(String cId, String cName, String id, String pass); check whether has this account and the correctness of the password
//                Account ac = ((Customer) currentOperator).getAccount(save);
//                if (ac == null || !ac.checkPassword(pa)) {
//                    System.out.println("Wrong password or don't have this account.");
//                    log.addLog("Fail to create security account.\n");
//                    ((Customer) currentOperator).addLog("Fail to create security account.\n");
//                }
//                else {
//                    double money = ac.getMoney("Dollar");
//                    //double getMoney(String cId, String cName, String id, String type); get one kind of money from the account
//                    if (money < 500) {
//                        System.out.println("Do not have enough money in saving.");
//                        log.addLog("Fail to create security account.\n");
//                        ((Customer) currentOperator).addLog("Fail to create security account.\n");
//                    } else {
//                        //void addMoney(String cId, String cName, double money, String type); add a one kind of money to the account
//                        //void createSecurity(String cId, String cName, String id, String pass, String associateSavingId); create a security account
//                        db.createSecurity(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(),
//                                id.getId(), p1, save);
//                        Security news = new Security(id, pass, 0, ac);
//                        ac.addMoney(-serviceFee, "Dollar");
//                        accounts.add(news);
//                        ((Customer) currentOperator).addAccount(news);
//                        log.addLog("New security account for " + currentOperator.getName().getName() + ".\n");
//                        ((Customer) currentOperator).addLog("Creating security account.\n");
//                    }
//                }
//            }
//            increseBalance("Dollar");
//        }
//    }
//
//    private void modifyMoney(Person currentOperator, int which) {
//        System.out.println("Account's id");
//        String id = Util.readStr();
//        System.out.println("Password for account");
//        String pass = Util.readStr();
//        System.out.println("Which kind of money do you want to operate? You can input 'Dollar', 'Euro' or 'Yuan'.");
//        String type = Util.readStr();
//        System.out.println("How much money to you want to change?");
//        double money = Util.readDouble();
//        String[] key = {id, pass, type};
//        Account ac = ((Customer) currentOperator).getAccount(key[0]);
//        //String getAccount(String cId, String cName, String id, String pass);
//        if (ac == null) {
//            log.addLog(currentOperator.getName().getName() + " account login fail.\n");
//            ((Customer) currentOperator).addLog("Customer does not have this account! \n");
//            System.out.println("Customer does not have this account!");
//        }
//        else {
//            if (key[1].length() > 8 || !ac.checkPassword(key[1])) {
//                System.out.println("Wrong password!");
//                log.addLog(currentOperator.getName().getName() + " account login fail.\n");
//                ((Customer) currentOperator).addLog("Log into account fail \n");
//            }
//            else if (!key[2].equals("Dollar") && !key[2].equals("Yuan") && !key[2].equals("Euro")) {
//                System.out.println("No support such kind of money!");
//                log.addLog(currentOperator.getName().getName() + " modify money fail.\n");
//                ((Customer) currentOperator).addLog("Modify money fail \n");
//            }
//            else {
//                log.addLog(currentOperator.getName().getName() + " log into account: "
//                        + ac.getAccountId().getId() + "\n");
//                ((Customer) currentOperator).addLog("Log into account " + ac.getAccountId().getId() + ".\n");
//                if (which == Config.ADD) {
//                    db.addMoney(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), id, money, type);
//                    //void addMoney(String cId, String cName, String id, double money, String type);
//                    ac.addMoney(money, key[2]);
//                    log.addLog(currentOperator.getName().getName() + " add "  + key[2] + " " +  money +
//                            "into account: " + ac.getAccountId().getId() +"\n");
//                    ((Customer) currentOperator).addLog("Add " + key[2] + " " +  money + " into account " +
//                            ac.getAccountId().getId() +".\n");
//                }
//                else {
//                    //boolean withDrawMoney(String cId, String cName, String id, double money, String type);
//                    money += serviceFee;
//                    if (!ac.withdrawMoney(money, key[2])) {
//                        System.out.println("No enough money!");
//                        log.addLog(currentOperator.getName().getName() + " withdraw money fail.\n");
//                        ((Customer) currentOperator).addLog("Fail to withdraw money account.\n");
//                    }
//                    else {
//                        db.withDrawMoney(
//                                ((Customer) currentOperator).getId().getId(),
//                                currentOperator.getName().getName(),
//                                id, money, type
//                        );
//                        log.addLog(currentOperator.getName().getName() + " withdraw " + key[2] + " " + money +
//                                "from account: " + ac.getAccountId().getId() +"\n");
//                        ((Customer) currentOperator).addLog("Withdraw " + key[2] + " " + money +
//                                "from account: " + ac.getAccountId().getId() +"\n");
//                    }
//                    increseBalance(key[2]);
//                }
//            }
//        }
//    }
//
//    private void requestLoan(Person currentOperator) {
//        System.out.println("How much Dollars do you want to loan");
//        double money = Util.readDouble();
//        //double getMoney(String cId, String cName, String type); return all money of one kine that the customer holds
//        if (money > ((Customer) currentOperator).getDollar() / 2) {
//            System.out.println( "You don't have enough collateral! Must have as twice as dollars you want to loan.");
//            log.addLog(currentOperator.getName().getName() + " request loan fail.\n");
//            ((Customer) currentOperator).addLog("Request loan fail \n");
//        }
//        else {
//            //void requestALoan(String cId, String cName, double money, double loanInterest); request a loan
//            db.requestALoan(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), money);
//            Loan loan = new Loan(money, loanInterest);
//            if (!loans.contains(currentOperator))
//                loans.add((Customer) currentOperator);
//            ((Customer) currentOperator).addLoan(loan);
//            log.addLog(currentOperator.getName().getName() + " request $" + money + " loan.\n");
//            ((Customer) currentOperator).addLog("Request $" + money + " money.\n");
//        }
//    }
//
//    private void payForLoan(Person currentOperator) {
//        System.out.println("From which account to pay the loan?");
//        String id = Util.readStr();
//        System.out.println("Password for account");
//        //String getAccount(String cId, String cName, String id, String pass);
//        String pass = Util.readStr();
//            String[] key = {id, pass};
//            Account ac = ((Customer) currentOperator).getAccount(key[0]);
//            if (ac == null) {
//                log.addLog(currentOperator.getName().getName() + " account login fail.\n");
//                ((Customer) currentOperator).addLog("Log into account fail \n");
//                System.out.println("Customer does not have this account!");
//            }
//            else {
//                if (key[1].length() > 8) {
//                    System.out.println("Wrong password!");
//                    log.addLog(currentOperator.getName().getName() + " account login fail.\n");
//                    ((Customer) currentOperator).addLog("Log into account fail \n");
//                }
//                else {
//                    log.addLog(currentOperator.getName().getName() + " log into account: "
//                            + ac.getAccountId().getId() + "\n");
//                    ((Customer) currentOperator).addLog("Log into account " + ac.getAccountId().getId() + ".\n");
//                    List<Balance> balance = ac.getAccountBalance();
//                    double money = 0;
//                    double loanM = ((Customer) currentOperator).getLoan();
//                    loanM = loanM + loanM * loanInterest.getInterest();
//                    //double getLoan(String cId, String cName); get all loan of the customer
//                    for (Balance b: balance) {
//                        if (b.getType().equals("Dollar")) {
//                            money = b.getMoney();
//                        }
//                    }
//                    if (money < loanM) {
//                        System.out.println("No enough money!");
//                        log.addLog(currentOperator.getName().getName() + " pay for loan fail.\n");
//                        ((Customer) currentOperator).addLog("Pay for loan fail \n");
//                    }
//                    else {
//                        //void payForLoan(String cId, String cName); pay for all loan
//                        db.payForLoan(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName());
//                        List<Loan> loan = ((Customer) currentOperator).getAllLoan();
//                        ((Customer) currentOperator).removeAllLoan();
//                        for (Loan l: loan) {
//                            loan.remove(l);
//                        }
//                        ac.withdrawMoney(loanM, "Dollar");
//                        db.withDrawMoney(
//                                ((Customer) currentOperator).getId().getId(),
//                                currentOperator.getName().getName(),
//                                id, loanM, "Dollar"
//                        );
//                    }
//                }
//            }
//    }
//
//    private String viewBalance(Person currentOperator) {
//        //String viewBalance(String cId, String cName); return the information of all balance
//        log.addLog(currentOperator.getName().getName() + " view balance.\n");
//        ((Customer) currentOperator).addLog("View balance. \n");
//        return ((Customer) currentOperator).getBalance();
//    }
//
//    private String getTransaction(Person currentOperator) {
//        //String getTransaction(String cId, String cName); return the log for the customer
//        log.addLog(currentOperator.getName().getName() + " view transaction.\n");
//        ((Customer) currentOperator).viewTransaction(serviceFee);
//        for (String type: ((Customer) currentOperator).getMoneyTyep())
//            increseBalance(type);
//        ((Customer) currentOperator).addLog("View transaction. \n");
//        return ((Customer) currentOperator).getLog();
//    }
//
//    private void closeAccount(Person currentOperator) {
//        System.out.println("Which account do you want to close?");
//        String id = Util.readStr();
//        System.out.println("Password for account");
//        String pass = Util.readStr();
//        String[] key = {id, pass};
//        //String getAccount(String cId, String cName, String id, String pass);
//        Account ac = ((Customer) currentOperator).getAccount(key[0]);
//        if (ac == null) {
//            log.addLog(currentOperator.getName().getName() + " close account fail.\n");
//            ((Customer) currentOperator).addLog("Close account fail \n");
//            System.out.println("Customer does not have this account!");
//        }
//        else {
//            if (key[1].length() > 8 || !ac.checkPassword(key[1])) {
//                System.out.println("Wrong password!");
//                log.addLog(currentOperator.getName().getName() + " account login fail.\n");
//                ((Customer) currentOperator).addLog("Log into account fail \n");
//            }
//            else {
//                log.addLog(currentOperator.getName().getName() + " log into account: "
//                        + ac.getAccountId().getId() + "\n");
//                ((Customer) currentOperator).addLog("Log into account." + ac.getAccountId().getId() + "\n");
//                if (!ac.getType().equals("Security")) {
//                    //void closeAccount(String cId, String cName, String id); close an account and return the money it left
//                    List<Balance> balance = ac.getAccountBalance();
//                    StringBuilder left = new StringBuilder();
//                    for (Balance b : balance) {
//                        left.append(b.getType()).append(" left: ").append(b.getMoney() - serviceFee);
//                        increseBalance(b.getType());
//                    }
//                    ((Customer) currentOperator).removeAccount(ac);
//                    ((Customer) currentOperator).addLog("Close account: " + ac.getAccountId().getId() + ".\n");
//                    log.addLog(currentOperator.getName().getName() + " close account"
//                            + ac.getAccountId().getId() + ".\n");
//                    accounts.remove(ac);
//                    db.closeAccount(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), id);
//                    System.out.println(left.toString());
//                }
//                else {
//                    double money = ((Security) ac).sellAllStock();
//                    //void closeSecurity(String cId, String cName, String id); sell all money in a security account and close that account
//                    db.closeSecurity(((Customer) currentOperator).getId().getId(), currentOperator.getName().getName(), id);
//                    ((Customer) currentOperator).removeAccount(ac);
//                    ((Customer) currentOperator).addLog("Close account: " + ac.getAccountId().getId() + ".\n");
//                    log.addLog(currentOperator.getName().getName() + " close account"
//                            + ac.getAccountId().getId() + ".\n");
//                    accounts.remove(ac);
//                    System.out.println("Has got " + money + " Dollar from the selling.");
//                }
//            }
//        }
//    }
//
//    private String lookAllCustomer() {
//        //String lookAllCustomer(); return the information of all customers including name, id
//        log.addLog("Manager look all customers.\n");
//        StringBuilder allMoney = new StringBuilder();
//        for (Customer c : customers) {
//            allMoney.append(c.getName().getName()).append(" ").append(c.getId().getId()).append("\n");
//        }
//        if (allMoney.length() == 0)
//            return "No Customer";
//        return allMoney.toString();
//    }
//
//    private String lookAllMoney() {
//        //String lookAllMoney(); return the information of all customers including name and the accounts info (account's balance)
//        log.addLog("Manager look all money.\n");
//        StringBuilder allMoney = new StringBuilder();
//        for (Customer c : customers) {
//            String balance = c.getBalance();
//            allMoney.append(c.getName().getName()).append(":\n").append(balance);
//        }
//        if (allMoney.length() == 0)
//            return "No Money";
//        return allMoney.toString();
//    }
//
//    private void lookSpecific() {
//        //String lookSpecific(String id, String name); return the information of a specific customer
//        System.out.println("Customer's id");
//        String id = Util.readStr();
//        System.out.println("Customer's name");
//        String name = Util.readStr();
//        log.addLog("Manager look specific customer " + id + " " + name + ".\n");
//        boolean flag = true;
//        for (Customer c : customers) {
//            if (c.getId().getId().equals(id) && c.getName().getName().equals(name)) {
//                System.out.println(c.getName().getName() + ":\n" + c.getBalance());
//                flag = false;
//            }
//        }
//        if (flag)
//            System.out.println("No such customer!");
//    }
//
//    private void changeInterest() {
//        //void changeInterest(double saving, double loan); change the interest of saving account and loan
//        System.out.print("New saving rate:");
//        double sr = Util.readDouble();
//        System.out.println("New loan rate");
//        double lr = Util.readDouble();
//        db.changeInterest(sr, lr);
//        log.addLog("Manager loan interest from " + loanInterest.getInterest() + " to " + lr + ".\n");
//        log.addLog("Manager save interest from " + savingInterest.getInterest() + " to " + sr + ".\n");
//        loanInterest.setInterest(lr);
//        savingInterest.setInterest(sr);
//    }
//
//    private String lookAllLoan() {
//        //String lookAllLoan(); return all customer's who have a loan and the information of the loan
//        log.addLog("Manager look all loan.\n");
//        StringBuilder allMoney = new StringBuilder();
//        for (Customer c : loans) {
//            allMoney.append(c.getName().getName()).append(" has a loan ").append(c.getLoan()).append(".\n");
//        }
//        if (allMoney.length() == 0)
//            return "No loan";
//        return allMoney.toString();
//    }
//
//    private void calculateLoan() {
//        //void calculateLoan(); update all loan by the loan interest
//        db.calculateLoan();
//        log.addLog("Manager calculate loan interest.\n");
//        for (Customer c : customers)
//            c.calculateLoan();
//    }
//
//    private void calculateSave() {
//        //void calculateSave(); update all save more than saving limit by the save interest
//        db.calculateSave();
//        log.addLog("Manager calculate save interest.\n");
//        for (Customer c : customers)
//            c.calculateSave(savingLimit);
//    }
//
//    private void changeServiceFee() {
//        //void changeServiceFee(int newService); change the service fee of the bank
//        System.out.print("New service fee");
//        double a = Util.readDouble();
//        db.changeServiceFee(a);
//        log.addLog("Manager change service fee from " + serviceFee + " to " + a + ".\n");
//        serviceFee = a;
//    }
//
//    private void increseBalance(String type) {
//        //void increaseBalance(String type); increase the bank's balance with one serviceFee
//        db.increaseBalance(type);
//        for (Balance b: myBalance) {
//            if (b.getType().equals(type)) {
//                b.increaseBalance(serviceFee);
//                return;
//            }
//        }
//        myBalance.add(new Balance(serviceFee, type));
//    }
//
//    private String getBalance() {
//        //String getBalance(); return the balance of the bank
//        StringBuilder balance = new StringBuilder();
//        for (Balance b: myBalance)
//            balance.append(b.getType()).append(": ").append(b.getMoney()).append("\n");
//        return balance.toString();
//    }
//
//    private String getLog() {
//        //String getLog(); return the log of the bank
//        log.addLog("Manager get log.\n");
//        return log.getLog();
//    }
//}
