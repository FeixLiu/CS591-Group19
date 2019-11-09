import javax.xml.transform.Result;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class ProjectDAO {
    public ProjectDAO() {
        Connection c = null;
        File file = new File("database.db");
        if (file.exists()) {
            System.out.println("tables have been created.");
        } else {
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                //create table: Bank
                String sql = "CREATE TABLE Bank " +
                        "(id INT PRIMARY KEY     NOT NULL," +
                        " saveInterest           REAL    NOT NULL, " +
                        " loanInterest            REAL     NOT NULL, " +
                        " serviceFee        REAL NOT NULL," +
                        " logId        TEXT NOT NULL," +
                        " dollarBalance        REAL NOT NULL," +
                        " euroBalance        REAL NOT NULL," +
                        " yuanBalance        REAL NOT NULL," +

                        " managerPass        TEXT," +
                        " managerName        TEXT NOT NULL) ";

                stmt.executeUpdate(sql);

                //Initialize Bank's values, such as interest etc.
                sql = "INSERT INTO Bank (id,saveInterest,loanInterest,serviceFee, logId, dollarBalance, euroBalance, yuanBalance, managerPass, managerName) " +
                        "VALUES (1, 0.05, 0.1, 10.0, '0', 100000, 100000, 100000, '123456', 'manager' );";
                stmt.executeUpdate(sql);

                //create table: User
                sql = "CREATE TABLE User " +
                        "(id TEXT PRIMARY KEY     NOT NULL," +
                        " name           TEXT    NOT NULL, " +
                        " pass            TEXT     NOT NULL, " +
                        " logId        TEXT NOT NULL)";
                stmt.executeUpdate(sql);
                //create table: Account
                sql = "CREATE TABLE Account " +
                        "(id TEXT PRIMARY KEY     NOT NULL," +
                        " pass            TEXT     NOT NULL, " +
                        " accountType        TEXT NOT NULL," +
                        " userId TEXT NOT NULL) ";
                stmt.executeUpdate(sql);
//                //create table: Loan
                sql = "CREATE TABLE Loan " +
                        "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        " userId            TEXT     NOT NULL, " +
                        " balance        REAL NOT NULL)";
                stmt.executeUpdate(sql);
//                //create table: Balance
                sql = "CREATE TABLE Balance " +
                        "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        " accountId            TEXT     NOT NULL, " +
                        " currencyType        TEXT NOT NULL," +
                        " money REAL NOT NULL) ";
                stmt.executeUpdate(sql);
//                //create table: Security
                sql = "CREATE TABLE \"Security\" (" +
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "companyName TEXT NOT NULL," +
                        "accountId TEXT NOT NULL," +
                        "price REAL NOT NULL," +
                        "securityAccountId TEXT NOT NULL," +
                        "share INTEGER NOT NULL," +
                        "pass TEXT NOT NULL)";
                stmt.executeUpdate(sql);
//                //create table: Stock
                sql = "CREATE TABLE Stock " +
                        "(companyName TEXT PRIMARY KEY     NOT NULL," +
                        " price            REAL     NOT NULL, " +
                        " available        INT NOT NULL," +
                        " haveSold        INT NOT NULL," +
                        " curSold        INT NOT NULL," +
                        " historyPrice TEXT NOT NULL) ";
                stmt.executeUpdate(sql);
                //create table: Log
//                sql = "CREATE TABLE Log " +
//                        "(logId TEXT PRIMARY KEY     NOT NULL," +
//                        " log TEXT NOT NULL) ";
//                stmt.executeUpdate(sql);
//                //Initialize log0, which belongs to the bank
//                sql = "INSERT INTO Log (logId,log) " +
//                        "VALUES (0, '');";
//                stmt.executeUpdate(sql);
//
//

                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Bank created successfully");
        }

    }

    //util function
    String sqlText(String s) {
        return "'" + s + "'";
    }


    /**
     *  return current service fee, saving interest, loan interest
     */
    double[] bankStart() {
        double[] bankParam = new double[3];
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Bank where id=1;" );
            while ( rs.next() ) {
                double saveInterest = rs.getDouble("saveInterest");
                double loanInterest = rs.getDouble("loanInterest");
                double fee = rs.getDouble("serviceFee");
                bankParam[0] = fee;
                bankParam[1] = saveInterest;
                bankParam[2] = loanInterest;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return bankParam;
    }

    /**
     *         add a new customer
     */
    void newCustomer(String id, String passWord, String name) {
        Connection c = null;
        Statement stmt = null;
        File file = new File("database.db");
        if (!file.exists()) {
            System.out.println("database not exist yet.");
        } else {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                String sql = "INSERT INTO User (id,name,pass,logId) " +
                        "VALUES (1, 'Paul', 32, '123456' );";
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Records created successfully");
        }
    }

    void changeStockPrice(String companyName, double newPrice) {
        String get = "SELECT * FROM Stock where companyName=?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(get);
            pstmt.setString(1, companyName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String history = rs.getString("historyPrice") + newPrice;

                String set = "UPDATE Stock SET price=?, historyPrice=?";
                pstmt = conn.prepareStatement(set);
                pstmt.setDouble(1, newPrice);
                pstmt.setString(2, history);
                pstmt.executeUpdate();
            }
            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void modifyStockAvailable(String companyName, int newAvailable) {
        String sql = "UPDATE Stock SET available=? WHERE companyName=?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newAvailable);
            pstmt.setString(2, companyName);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void addNewStock(String companyName, int available, double price) {
        String sql = "INSERT INTO Stock(companyName, price, available, haveSold, curSold, historyPrice) VALUES(?,?,?, 0,0,?)";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, companyName);
            pstmt.setInt(2, available);
            pstmt.setDouble(3, price);
            pstmt.setString(4, Double.toString(price));
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void deleteStock(String companyName) {
        String sql = "DELETE FROM Stock WHERE companyName=?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, companyName);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void withDrawMoney(String cId, String cName, String id, double money, String type) {
        String sql = "SELECT money FROM Balance WHERE accountId=? AND currencyType=?";
        String updatebalance = "UPDATE Balance SET money=? WHERE accountId=? AND currencyType=?";
        Double balance = 0.0;
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, type);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("money");
                pstmt = conn.prepareStatement(updatebalance);
                pstmt.setDouble(1,balance-money);
                pstmt.setString(2, id);
                pstmt.setString(3, type);
                pstmt.executeUpdate();
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void buyStock(String cId, String cName, String secAccId, String companyName, int howMany) {
        String associatedSavingsId="";
        double savingsAccountBalance=0.0, stockPrice=0.0;
        int available=0, haveSold=0, curSold=0;
        Connection conn = connect();
        try {
            String getStockInfo = "SELECT * FROM Stock WHERE companyName=?";
            PreparedStatement pstmt = conn.prepareStatement(getStockInfo);
            pstmt.setString(1, companyName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Retrieved stock information");
                available = rs.getInt("available");
                haveSold = rs.getInt("haveSold");
                curSold = rs.getInt("curSold");
                stockPrice = rs.getDouble("price");
            }

            String setStockInfo = "UPDATE Stock SET available=?, haveSold=?, curSold=? WHERE companyName=?";
            pstmt = conn.prepareStatement(setStockInfo);
            pstmt.setInt(1, available-howMany);
            pstmt.setInt(2, haveSold+howMany);
            pstmt.setInt(3, curSold+howMany);
            pstmt.setString(4, companyName);
            pstmt.executeUpdate();

            String getAssociatedSavingId = "SELECT accountId FROM Security WHERE securityAccountId=?";
            pstmt = conn.prepareStatement(getAssociatedSavingId);
            pstmt.setString(1,secAccId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                associatedSavingsId = rs.getString("accountId");
                System.out.println("Grabbed associated savings Id: " + associatedSavingsId);
            }

            String addPurchasedEntry = "INSERT INTO Security(companyName, accountId, price, securityAccountId, share)" +
                    "VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(addPurchasedEntry);
            pstmt.setString(1, companyName);
            pstmt.setString(2, associatedSavingsId);
            pstmt.setDouble(3, stockPrice);
            pstmt.setString(4, secAccId);
            pstmt.setInt(5, howMany);
            pstmt.executeUpdate();

            String getBalanceInfo = "SELECT * FROM Balance WHERE accountId=? AND currencyType=\"Dollar\"";
            pstmt = conn.prepareStatement(getBalanceInfo);
            pstmt.setString(1, associatedSavingsId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Grabbed old savings balance");
                savingsAccountBalance = rs.getDouble("money");
            }
            String setBalanceInfo = "UPDATE Balance SET money=? WHERE accountId=? AND currencyType=\"Dollar\"";
            pstmt = conn.prepareStatement(setBalanceInfo);
            pstmt.setDouble(1, (savingsAccountBalance-(stockPrice*howMany)));
            pstmt.setString(2, associatedSavingsId);
            pstmt.executeUpdate();

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void addMoney(String cId, String cName, String accountId, double money, String type) {
        String sql = "SELECT * FROM Balance WHERE accountId=? AND currencyType=?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountId);
            pstmt.setString(2, type);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                // Add a new entry
                String insert = "INSERT INTO Balance(accountId, currencyType, money) VALUES(?, ?, ?)";
                pstmt = conn.prepareStatement(insert);
                pstmt.setString(1, accountId);
                pstmt.setString(2, type);
                pstmt.setDouble(3, money);
                pstmt.executeUpdate();
            } else {
                // update the money value
                double oldBal = rs.getDouble("money");
                String update = "UPDATE Balance SET money=? WHERE accountId=?";
                pstmt = conn.prepareStatement(update);
                pstmt.setDouble(1, money+oldBal);
                pstmt.setString(2, accountId);
                pstmt.executeUpdate();
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void sellStock(String cId, String cName, String secAccId, String name) {
        Connection conn = connect();
        double sellingPrice=0.0, userMoney=0.0;
        int available=0, curSold=0;
        int amountOwned = 0;
        String associatedSavingsAccount="";
        try {
            String getCompanyStockInfo = "SELECT * FROM Stock WHERE companyName=?";
            PreparedStatement pstmt = conn.prepareStatement(getCompanyStockInfo);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sellingPrice = rs.getDouble("price");
                available = rs.getInt("available");
                curSold = rs.getInt("curSold");
            }

            String getStocksOwned = "SELECT * FROM Security WHERE securityAccountId=? AND companyName=?";
            pstmt = conn.prepareStatement(getStocksOwned);
            pstmt.setString(1, secAccId);
            pstmt.setString(2, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                amountOwned += rs.getInt("share");
                associatedSavingsAccount = rs.getString("accountId");
            }
            String updateStocksOwned = "DELETE FROM Security WHERE securityAccountId=? AND companyName=?";
            pstmt = conn.prepareStatement(updateStocksOwned);
            pstmt.setString(1, secAccId);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

            String setCompanyStockInfo = "UPDATE Stock SET available=?, curSold=?";
            pstmt = conn.prepareStatement(setCompanyStockInfo);
            pstmt.setInt(1, available+amountOwned);
            pstmt.setInt(2, curSold-amountOwned);
            pstmt.executeUpdate();

            String getUserBalance = "SELECT * FROM Balance WHERE accountId=? AND currencyType=\"Dollar\"";
            pstmt = conn.prepareStatement(getUserBalance);
            pstmt.setString(1, associatedSavingsAccount);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                userMoney = rs.getDouble("money");
            }

            String setUserBalance = "UPDATE Balance SET money=? WHERE accountId=? AND currencyType=\"Dollar\"";
            pstmt = conn.prepareStatement(setUserBalance);
            pstmt.setDouble(1, userMoney+(amountOwned*sellingPrice));
            pstmt.setString(1, associatedSavingsAccount);
            pstmt.executeUpdate();

            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void createAccount(String cId, String cName, String id, String pass, double initialBalance, String type) {
        String sql = "INSERT INTO Account (id, pass, accountType, userId) VALUES(?, ?, ?, ?)";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Add to account table
            pstmt.setString(1, id);
            pstmt.setString(2, pass);
            pstmt.setString(3, type);
            pstmt.setString(4, cId);
            pstmt.executeUpdate();

            // Add to balance table
            sql = "INSERT INTO Balance(accountId, currencyType, money) VALUES(?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, "USD");
            pstmt.setDouble(3, initialBalance);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    void createSecurity(String cId, String cName, String id, String pass, String associateSavingId) {
        String sql = "INSERT INTO Security (companyName, accountId, price, securityAccountId, share) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Add to account table
            pstmt.setString(1, "");
            pstmt.setString(2, associateSavingId);
            pstmt.setDouble(3, 0.0);
            pstmt.setString(4, id);
            pstmt.setInt(5, 0);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    void requestALoan(String cId, String cName, double money) {
        String sql = "INSERT INTO Loan(userId, balance) VALUES(?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cId);
            pstmt.setDouble(2, money);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("...Connected to database...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
