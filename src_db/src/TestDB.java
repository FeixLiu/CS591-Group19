import java.sql.*;
import java.util.Arrays;

public class TestDB {
    public static void main(String args[]) {
        ProjectDAO dao = new ProjectDAO();
        //dao.insertUser();
//        double[] bankParam = dao.bankStart();
//        dao.changeStockPrice("Google", 104.0);
//        dao.modifyStockAvailable("Google", 100);
//        dao.addNewStock("FakeGoogle", 100, 10.0);
//        dao.deleteStock("FakeGoogle");
//        dao.withDrawMoney("1", "FakeName", "1", 30.0, "USD");

        /*
        Add money test is working.
         */
//        dao.addMoney("1", "Name", "1", 40.0, "Euro");
        dao.buyStock("1", "Name", "2", "Google", 5);
//        dao.createSecurity("1", "name", "2", "pass", "1");
//        dao.requestALoan("1", "name", 10.0);
        dao.sellStock("1", "Name", "2", "Google");
        dao.withDrawMoney("1", "Name", "1", 10.0, "Euro");
    }

}
