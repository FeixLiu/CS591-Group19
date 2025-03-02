import java.util.Scanner;

public class Util {
    private static Scanner scan = new Scanner(System.in);

    public static int stringToInt(String in) {
        try {
            return Integer.parseInt(in);
        }
        catch (Exception e) {
            return -1;
        }
    }

    public static double stringToDouble(String in) {
        try {
            return Double.parseDouble(in);
        }
        catch (Exception e) {
            return -1.0;
        }
    }

    public static int readInt() {
        String str = scan.nextLine();
        int temp;
        try {
            temp = Integer.parseInt(str);
        }
        catch (Exception e) {
            temp = 0;
        }
        return temp;
    }

    public static double readDouble() {
        String str = scan.nextLine();
        double temp;
        try {
            temp = Double.parseDouble(str);
        }
        catch (Exception e) {
            temp = 0;
        }
        return temp;
    }

    public static String readStr() {
        String str = scan.nextLine();
        while(str.length() == 0) {
            System.out.print("Give me some input: ");
            str = scan.nextLine();
        }
        return str;
    }
}
