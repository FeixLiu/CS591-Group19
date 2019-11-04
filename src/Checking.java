public class Checking extends Account {
    public Checking(Id accountId, Password accountPassword, int serviceFee) {
        super(accountId, accountPassword, serviceFee, "Checking");
    }
}
