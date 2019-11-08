public class Checking extends Account {
    public Checking(Id accountId, Password accountPassword, double serviceFee) {
        super(accountId, accountPassword, serviceFee, "Checking");
    }
}
