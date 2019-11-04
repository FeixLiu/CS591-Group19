public class Manager extends Person {
    private Password pass;

    public Manager() {
        super(new Name("Manager"));
        pass = new Password("123456");
    }

    public boolean login(String key) {
        return pass.checkPassword(key);
    }
}
