import java.sql.*;
import java.util.Arrays;

public class TestDB {
    public static void main(String args[]) {
        ProjectDAO dao = new ProjectDAO();
        //dao.insertUser();
//        double[] bankParam = dao.bankStart();
//        System.out.println(Arrays.toString(bankParam));
//        System.out.println(dao.managerLogin("123456"));
//        System.out.println(dao.managerLogin("1233"));
//        dao.addLog("asec");
//        Double loan = dao.getLoanInterest();
//        System.out.println(loan);

        // TEST closeAccount
//        Double left = dao.closeAccount("1", "fakename", "1");
//        System.out.println(left);

        // TEST Requestloan
//        dao.requestALoan("1", "fakename", 100.0, 10.0);

//        dao.createChecking("4", "fakeName", "4", "password", 40.0);
        String out = dao.getAccount("4", "What", "4", "password");
        System.out.println(out);
    }

}
