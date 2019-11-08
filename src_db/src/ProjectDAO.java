import java.io.File;
import java.sql.*;

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
                        "(id TEXT PRIMARY KEY     NOT NULL," +
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



}
