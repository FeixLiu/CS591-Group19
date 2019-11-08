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
                        " currencyType        TEXT NOT NULL," +
                        " userId TEXT NOT NULL) ";
                stmt.executeUpdate(sql);
                //create table: Loan
                sql = "CREATE TABLE Loan " +
                        "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        " userId            TEXT     NOT NULL, " +
                        " balance        REAL NOT NULL)";
                stmt.executeUpdate(sql);
                //create table: Balance
                sql = "CREATE TABLE Balance " +
                        "(id TEXT PRIMARY KEY     NOT NULL," +
                        " accountId            TEXT     NOT NULL, " +
                        " currencyType        TEXT NOT NULL," +
                        " money REAL NOT NULL) ";
                stmt.executeUpdate(sql);
                //create table: Security
                sql = "CREATE TABLE Security " +
                        "(id TEXT PRIMARY KEY     NOT NULL," +
                        " userId            TEXT     NOT NULL, " +
                        " boughtStock        TEXT NOT NULL," +
                        " accountId TEXT NOT NULL) ";
                stmt.executeUpdate(sql);
                //create table: Stock
                sql = "CREATE TABLE Stock " +
                        "(companyName TEXT PRIMARY KEY     NOT NULL," +
                        " price            REAL     NOT NULL, " +
                        " available        INT NOT NULL," +
                        " haveSold        INT NOT NULL," +
                        " curSold        INT NOT NULL," +
                        " historyPrice TEXT NOT NULL) ";
                stmt.executeUpdate(sql);
                //create table: Log
                sql = "CREATE TABLE Log " +
                        "(logId TEXT PRIMARY KEY     NOT NULL," +
                        " log TEXT NOT NULL) ";
                stmt.executeUpdate(sql);
                //Initialize log0, which belongs to the bank
                sql = "INSERT INTO Log (logId,log) " +
                        "VALUES (0, '');";
                stmt.executeUpdate(sql);



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
     *     check whether the password for manger correct or not
     */
    boolean managerLogin(String password) {
        Connection c = null;
        Statement stmt = null;
        String managerPass = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Bank where id=1;" );
            while ( rs.next() ) {
                managerPass = rs.getString("managerPass");
            }
            rs.close();
            stmt.close();
            c.close();
            return managerPass.equals(password);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return false;
    }



//do I need a logId here?
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

    /**
     * check whether there is a customer with name and id and check the password
     */
    boolean customerLogin(String id, String name, String pass) {
        Connection c = null;
        Statement stmt = null;
        String userName = null;
        String userPass = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM User where id=" + id + ";" );
            while ( rs.next() ) {
                userName = rs.getString("name");
                userPass = rs.getString("pass");
            }
            rs.close();
            stmt.close();
            c.close();
            return userName.equals(name) && userPass.equals(pass);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return false;
    }


    //I don't understand this api
    /**
     * return all ids of account (which == Config.NEWACCOUNT) or all ids of all customer (which == Config.NEWCUSTOMER)
     */
    int[] getAllId(int which) {
        int[] allId = null;
        return allId;
    }

    /**
     * add log to the bank's log
     */
    void addLog(String log) {
        Connection c = null;
        Statement stmt = null;
        String logs = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");

            stmt = c.createStatement();
            //get the log first
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Log where logId=0;" );
            while ( rs.next() ) {
                logs = rs.getString("logId");
            }
            rs.close();

            logs += log;
            String sql = "UPDATE Log set log=" + sqlText(logs) +" where logId=0;";
            stmt.executeUpdate(sql);


            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }



    String getBalance() {
        return "";
    }

    void createChecking(String cId, String cName, String id, String pass, double initialBalance) {
        createAccountHelper(cId, id, pass, initialBalance, "Checking");
    }

    void createSaving(String cId, String cName, String id, String pass, double savingInterest, double initialBalance) {
        createAccountHelper(cId, id, pass, initialBalance, "Saving");
    }

    void createAccountHelper(String cId, String id, String pass, double initialBalance, String accountType) {
        String sql = "INSERT INTO Account (id, pass, accountType, userId) VALUES(?, ?, ?, ?)";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Add to account table
            pstmt.setString(1, id);
            pstmt.setString(2, pass);
            pstmt.setString(3, accountType);
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
//            disconnect(conn);
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    String getAccount(String cId, String cName, String id, String pass) {
        // If we are getting an account, we need just the id, cId, and pass for the account itself, not cName
        String sql = "SELECT accountType FROM Account WHERE id=?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("accountType");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    double getMoney(String cId, String cName, String id, String type) {
        String sql = "SELECT money FROM Balance WHERE accountId=? AND currencyType=?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cId);
            pstmt.setString(2, type);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("money");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1.0;
    }

    void createSecurity(String cId, String cName, String id, String pass, String associateSavingId) {
        String sql = "INSERT INTO Account (id, pass, accountType, userId, AssociatedSavingsId) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Add to account table
            pstmt.setString(1, id);
            pstmt.setString(2, pass);
            pstmt.setString(3, "Securities");
            pstmt.setString(4, cId);
            pstmt.setString(5, associateSavingId);
            pstmt.executeUpdate();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }

    }

    void addMoney(String cId, String cName, String id, double money, String type) {
        // cName not necessary. Does this add new row to the Balance table? If so, is this representing the balance in a different currency or is this a brand new entry?

    };

    double[] getStock(String name) {
        double[] ret = {0,0};
        String sql = "SELECT * FROM Stock WHERE companyName=?";

        try {
          Connection conn = connect();
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, name);
          ResultSet rs = pstmt.executeQuery();
          while (rs.next()) {
              ret[0] = rs.getDouble("available");
              ret[1] = rs.getDouble("price");
          }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    String getSavingIdForSecurity(String cId, String cName, String id) {
        String sql = "SELECT accountId in Security WHERE id=? AND userId=?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, cId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("accountId");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    boolean withDrawMoney(String cId, String cName, String id, double money, String type) {
        // TODO : What is the purpose of the cId? And why does balance have its own Id? Shouldn't it just be foreign key to accountId?
        String sql = "SELECT money FROM Balance WHERE accountId=?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getDouble("money") > money) {
                    Double newVal = rs.getDouble("money") - money;
                    conn.createStatement().executeUpdate("UPDATE Balance SET money=" + newVal + " WHERE accountId=" + id);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    void buyStock(String cId, String cName, String id, String companyName, String howMany, double price) {
        // TODO: what does accountId, id, and boughtStock refer to in the Security Table?
    }

    void addLog(String id, String log) {
        String sql = "INSERT INTO Log(logId, log) VALUES(?,?)";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, log);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    String viewStocks(String cId, String cName) {
        // TODO how do we keep track of the price bought? The table does not directly show this.
        return "";
    }

    /**
     * Check if user owns that stock and sell it if they do
     * @param cId
     * @param cName
     * @param id
     * @param name
     * @return
     */
    double sellStock(String cId, String cName, String id, String name) {
        // TODO need to update table after getting stock price
        String sql = "SELECT price FROM Stock WHERE companyName in (SELECT boughtStock from Security WHERE userId=? AND boughtStock=?)";
        Double ret = 0.0;
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cId);
            pstmt.setString(2, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ret+=rs.getDouble("price");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1.0;
    }

    double getMoney(String cId, String cName, String type) {
        Double ret = 0.0;
        String sql = "SELECT money FROM Balance WHERE currencyType=? AND accountId in " +
                "(SELECT id FROM Account where userId=?)";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, type);
            pstmt.setString(2, cId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ret+=rs.getDouble("money");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    /**
     *
     * @param cId
     * @param cName
     * @param money
     * @param loanInterest
     */
    void requestALoan(String cId, String cName, double money, double loanInterest) {
        // TODO : assumption - this api does not do validation check on whether customer is eligible for a loan. this just inserts into table.
        String sql = "INSERT INTO Loan(userId, balance) VALUES(?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cId);
            pstmt.setDouble(2, money);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Double> getLoan(String cId, String cName) {
        // TODO: assuming that a user can have multiple loans, so this returns an arraylist
        String sql = "SELECT * FROM Loan WHERE userId=?";
        ArrayList<Double> loans = new ArrayList<>();
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loans.add(rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return loans;
    }

    /**
     * Pay for all loans
     * @param cId
     * @param cName
     */
    void payForLoan(String cId, String cName) {
        // TODO
        // Which account to pay from?
    }

    /**
     * View balance of all accounts of a customerID
     * @param cId - customer ID
     * @param cName
     * @return
     */
    String viewBalance(String cId, String cName) {

        String ret = "";
        String sql = "SELECT * FROM Balance WHERE accountId IN " +
                "(SELECT id FROM Account where userId=?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String balanceInfo = rs.getString("accountId") + " "
                        + rs.getString("currencyType") + " "
                        + rs.getDouble("money") + "\n";
                ret+= balanceInfo;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    /**
     * return information of all accounts of customer cID
     * @param cId - customer ID
     * @param cName
     * @return
     */
    String viewAccount(String cId, String cName) {
        String ret = "";
        String sql = "SELECT id, currencyType FROM Account WHERE userId=?";
        try {
            Connection conn = connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String accountInfo = rs.getString("id") + " "
                        + rs.getString("currencyType") + "\n";
                ret+=accountInfo;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    /**
     *
     * @param cId - customer ID
     * @param cName - customer name
     * @param id - account ID
     * @return money left in the account belong to customer cID
     */
    double closeAccount(String cId, String cName, String id) {
        //TODO
        // Assumption: id is foreign key to account id
        String selectSQL = "SELECT money FROM Balance where accountId=?";
        String deleteSQL = "DELETE FROM Account WHERE id=? AND userId=?";
        Double ret = 0.0;
        try {
            Connection conn = connect();

            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ret = rs.getDouble("money");
            }

            // Delete the account
            pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setString(1, id);
            pstmt.setString(2, cId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    double sellAllStock(String cId, String cName, String id) {
        // TODO
        return 0.0;
    }

    String getTransaction(String cId, String cName) {
        String sql = "SELECT log FROM Log where logId=?";
        String ret = "";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ret = rs.getString("log");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    double getSaveInterest() {
        Double ret = 0.0;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();

            String sql = "SELECT saveInterest FROM BANK";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ret = rs.getDouble("saveInterest");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    double getLoanInterest() {
        Double ret = 0.0;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();

            String sql = "SELECT loanInterest FROM BANK";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ret = rs.getDouble("loanInterest");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
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

    private void disconnect(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
