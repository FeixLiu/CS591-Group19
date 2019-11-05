import java.sql.*;
import java.util.Arrays;

public class TestDB {
    public static void main(String args[]) {
        ProjectDAO dao = new ProjectDAO();
        //dao.insertUser();
        double[] bankParam = dao.bankStart();
        System.out.println(Arrays.toString(bankParam));
        System.out.println(dao.managerLogin("123456"));
        System.out.println(dao.managerLogin("1233"));
        dao.addLog("asec");
    }

}
