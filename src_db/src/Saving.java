public class Saving extends Account {
    private InterestRate interestRate;

    public Saving(Id accountId, Password accountPassword, InterestRate interestRate, double serviceFee) {
        super(accountId, accountPassword, serviceFee, "Saving");
        this.interestRate = interestRate;
    }

    public void updateBalance(double limit) {
        for (Balance b: super.getAccountBalance()) {
            if (b.getMoney() > limit) {
                double delta = b.getMoney() * interestRate.getInterest();
                b.increaseBalance(delta);
            }
        }
    }
}
