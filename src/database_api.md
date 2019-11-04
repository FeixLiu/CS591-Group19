#First method
read all the information when start the day<br>
write all information to the database at the end of the day

#Second method
##StartDay
####int[] bankStart()
return current service fee, saving interest, loan interest

##BankOperations()
####boolean managerLogin(String password); 
check whether the password for manger correct or not
####void newCustomer(String id, String passWord, String name); 
add a new customer
####boolean customerLogin(String id, String name, String pass); 
check whether there is a customer with name and id and check the password
####int[] getAllId(int which); 
return all ids of account (which == Config.NEWACCOUNT) or all ids of all customer (which == Config.NEWCUSTOMER)
####void addLog(String log);
add log to the bank's log
####void increaseBalance(String type); 
increase the bank's balance with one serviceFee    

##ManagerOperations()
####String lookAllCustomer(); 
return the information of all customers including name, id and account's info
####String lookAllMoney(); 
return the information of all customers including name and the accounts info
 (account's balance, balance currency type)
####String lookAllLoan(); 
return the information of all customer's who have a loan and the information of the loan
####String lookSpecific(String id, String name); 
return the information of a specific customer
####String lookCustomerWithMostLoan(); 
return the information of the customer with the mose loan and the information of the loan
####String lookingAllStocks(); 
return the information of all stocks (current price, current available, shares have sold, shares current sold)
####String lookingCustomerStocks(); 
return the information of customers and stocks they have bought (at which price buy how many shares)            
####boolean changeStockPrice(String companyName, double newPrice); 
change the price of a specific company, return false if no such a company
####boolean modifyStockAvailable(String companyName, int newAvailable); 
change the available of a specific company, return false if no such a company
####boolean addNewStock(String companyName, int available, double price); 
add a new stock, return false if the company has already hold a stock
####boolean deleteStock(String companyName); 
delete a stock, return false if some customers are holding stocks of such company or no such a stock
####void changeInterest(double saving, double loan); 
change the interest of saving account and loan
####void calculateLoan(); 
update all loan by the loan interest
####void calculateSave(); 
update all save more than saving limit by the save interest
####void changeServiceFee(int newService); 
change the service fee of the bank
####String getLog(); 
return the log of the bank
####String getBalance(); 
return the balance of the bank

##CustomerOperations()
####void createChecking(String cId, String cName, String id, String pass, double initialBalance); 
create a checking account for the customer
####void createSaving(String cId, String cName, String id, String pass, double savingInterest, double initialBalance); 
create a saving account for the customer
####String getAccount(String cId, String cName, String id, String pass); 
check the customer whether has this account and the correctness of the password, return the type of the account
return "No" for any incorrectness
####double getMoney(String cId, String cName, String id, String type); 
get one kind of money from the customer's account
####void createSecurity(String cId, String cName, String id, String pass, String associateSavingId); 
create a security account for the customer
####void addMoney(String cId, String cName, String id, double money, String type); 
add a one kind of money to the customer's account, if don't have that kind of money, create it
####int[] getStock(String name); 
check whether has a stock, if has return the available and price, if not return [-1, -1]
####String getSavingIdForSecurity(String cId, String cName, String id); 
return the saving account's id relating with the security account
####boolean withDrawMoney(String cId, String cName, String id, double money, String type); 
with draw one kind on money from one account, if sufficient money, modify the balance, if not return false
####void buyStock(String cId, String cName, String id, String companyName, String howMany, double price); 
buy howMany shares stocks with price, modify the available, currentSold, haveSold information of stocks, modify the information of the security account
####void addLog(String cId, String cName);
add log to customer's log       
####String vewStocks(String cId, String cName); 
view all stocks the customer has bought (at which price buy how many)  
####double sellStock(String cId, String cName, String id, String name); 
sell stock of company name, return -1 if don't buy that stock, or else return the money earn by the selling     
####double getMoney(String cId, String cName, String type); 
return all money of one kine that the customer holds
####void requestALoan(String cId, String cName, double money, double loanInterest); 
request a loan
####double getLoan(String cId, String cName); 
get all loan of the customer
####void payForLoan(String cId, String cName); 
pay for all loan
####String viewBalance(String cId, String cName); 
return the information of all balance
####String viewAccount(String cId, String cName); 
return the information of all account
####double closeAccount(String cId, String cName, String id); 
close an account and return the money it left
####double sellAllStock(String cId, String cName, String id); 
sell all money in a security account and close that account
####String getTransaction(String cId, String cName); 
return the log for the customer

##HelperFUNCTIONS:
####double getSaveInterest();
get the saving account interest
####double getLoanInterest();
get the loan interest