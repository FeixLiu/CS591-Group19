####public String startDay()
input: null<br>
calling: call at the start of the day <br>
return: some basic information of the bank

##bank operations
####public boolean managerLogin(String pass, int which) 
input: password for manager, whether manager login or end the day<br>
calling: call at the manager login or end the day <br>
return: true for success, false for all errors
####public boolean customerLogin(String id, String name, String pass)
input: customer's name, id and password<br>
calling: call at the customer login<br>
return: true for success, false for all errors
####public boolean newCustomer(Id id, String name, String pass1, String pass2)
input: customer's new id, name and 2 times input password<br>
calling: call at the create a new customer<br>
return: true for success, false for all errors
####public Id newId(int which)
input: whether to create a new customer or new account
calling: call before create a new customer or new account
return: an instance of Id (pass this instance to newCustomer or newAccount)

##manager operations
####public String lookAllCustomer()
input: null<br>
calling: call at the "1. Looking all customer." function <br>
return: all information for all customers
####public String lookAllMoney()
input: null<br>
calling: call at the "2. Looking all money." function <br>
return: all information for all money the bank has
####public String lookAllLoan()
input: null<br>
calling: call at the "3. Look all the loan." function <br>
return: all information for all loan the customers hold
####public String lookSpecific(String id, String name)
input: customer's id and name<br>
calling: call at the "4. Looking specific customer." function <br>
return: return the information for specific customer, return "no such customer" if fail to find a customer
####public String lookCustomerWithMostLoan()
input: null<br>
calling: call at the "5. Looking the customer with the most loan." function <br>
return: the information for the customer holds the most loan
####public String lookingAllStocks()
input: null<br>
calling: call at the "6. Looking all stocks." function <br>
return: the information for all the stocks in the market
####public String lookingCustomerStocks()
input: null<br>
calling: call at the "7. Looking customer's stocks." function <br>
return: the information for all the stocks that hold by customers
####public boolean changeStockPrice(String name, String newMoney)
input: the company name and the new stock price<br>
calling: call at the "8. Change stock's price." function <br>
return: true for success, false for all errors
####public boolean modifyStockAvailable(String name, String newAvailable)
input: the company name and the new stock available<br>
calling: call at the "9. Change stock's available." function <br>
return: true for success, false for all errors
####public boolean addNewStock(String name, String price, String available)
input: the company name, the stock price and the stock available<br>
calling: call at the "10. Add new stock." function <br>
return: true for success, false for all errors
####public boolean deleteStock(String name)
input: the company name<br>
calling: call at the "11. Delete a stock." function <br>
return: true for success, false for all errors
####public boolean changeInterest(String newSave, String newLoan)
input: the new interest for save account and for loan<br>
calling: call at the "12. Change the interest." function <br>
return: true for success, false for all errors
####public void calculateLoan()
input: null<br>
calling: call at the "13. Calculate the loan interest." function <br>
return: void
####public void calculateSave()
input: null<br>
calling: call at the "14. Calculate the save interest." function <br>
return: void
####public boolean changeServiceFee(String newServiceFee)
input: new service fee for the bank<br>
calling: call at the "15. Change the service fee." function <br>
return: true for success, false for all errors
####public String getLog()
input: null<br>
calling: call at the "16. Look the log." function <br>
return: return the log of the bank
####public String getBalance()
input: null<br>
calling: call at the "17. View bank balance." function <br>
return: return the balance of the bank

##customer operations
####public boolean createAccount(Id id, String pass1, String pass2, int which, String save, String savePass)
input:<br>
id: new id for that account<br>
pass1, pass2: the two times input of the password<br>
which: create which kind of account (saving, checking, security)<br>
save, savePass: if create security account, pass the associated saving account<br>
calling: call at the "1. Create checking. 2. Create saving. 3. Create security." function <br>
return: true for success, false for all errors
####public boolean buyStock(String name, String shares, String id, String pass)
input:<br>
name: the company name<br>
shares: how many shares the customer is going to buy<br>
id, pass: the id and password for security account
calling: call at the "4. Buy stocks.." function <br>
return: true for success, false for all errors
####public String viewStocks()
input: null<br>
calling: call at the "5. View stocks." function <br>
return: return the info of all stocks that bought by customer
####public String sellStocks(String id, String pass, String name)
input: the security account id and password and the company's name<br>
calling: call at the "6. Sell stocks." function <br>
return: return the money eran by the selling, "fail" will be returned for all errors
####public boolean modifyMoney(String id, String pass, String type, String money, int which)
input:<br>
id: the account id<br>
pass: the password for that account <br>
type: which kind of money is going to manipulate<br>
money: how much money is going to modify<br>
which: add money or withdraw money<br>
calling: call at the "7. Add money to account. 8. Withdraw money to account." function <br>
return: true for success, false for all errors
####public boolean makeTransaction(String idFrom, String passFrom, String idTo, String passTo, String type, String money)
input:<br>
idFrom, passFrom: the id and pass for the account the transaction from<br>
idTo, passTo: the id and pass for the account the transaction to <br>
type: which kind of money is going to manipulate<br>
money: how much money is going to modify<br>
calling: call at the "9. Make transaction within my account." function <br>
return: true for success, false for all errors
####public boolean requestLoan(String money)
input: how much money is going to loan<br>
calling: call at the "10. Request a loan." function <br>
return: true for success, false for all errors
####public boolean payForLoan(String id, String pass)
input: the id and password for the account to pay for the loan<br>
calling: call at the "11. Pay for the loan." function <br>
return: true for success, false for all errors
####public String viewBalance()
input: null<br>
calling: call at the "12. View balance." function <br>
return: return the balance of the customer
####public String viewAccount()
input: null<br>
calling: call at the "13. View account." function <br>
return: return the accounts' info of the customer
####public String closeAccount(String id, String pass)
input: the id and password for the closing account<br>
calling: call at the "14. Close account." function <br>
return: return the money earn by the closing, "fail"
####public String getTransaction()
input: null<br>
calling: call at the "15. Get transaction." function <br>
return: return the log of the customer, "fail" will be returned for all errors
####public void customerLogout()
input: null<br>
calling: call at the "16. Logout." function <br>
return: null
