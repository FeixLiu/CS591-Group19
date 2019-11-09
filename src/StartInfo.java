import java.util.HashMap;
import java.util.List;

public class StartInfo {
    public List<Double> bankInfo; //[serviceFee, savingInterest, loanInterest, dollarBalance, euroBalance, yuanBalance, date, securityLimit, savingLimit]
    public List<HashMap<String, List<Double>>> stockInfo;
    //[name1 -> [available, price, haveSold, curSold, historyPrice1, historyPrice2],
    // name2 -> [available, price, haveSold, curSold, historyPrice1, historyPrice2]]
    public HashMap<String, CustomerInfo> customerInfo; //[id1 -> info, id2 -> info]
}

class CustomerInfo {
    public String name;
    public String pass;
    public List<Double> loanInfo;
    public List<AccountInfo> accountInfo;
    public List<SecurityInfo> securityInfo;
}
class AccountInfo {
    public String id;
    public String type;
    public String pass;
    public HashMap<String, Double> balanceInfo;
}
class SecurityInfo {
    public String id;
    public String type;
    public String pass;
    public String saveId;
    public HashMap<String, HashMap<Double, Integer>> stocks;
}
