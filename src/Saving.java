public class Saving extends Account {
    private InterestRate interestRate;

    public Saving(Id accountId, Password accountPassword, InterestRate interestRate, int serviceFee) {
        super(accountId, accountPassword, serviceFee, "Saving");
        this.interestRate = interestRate;
    }

    public void updateBalance(int limit) {
        for (Balance b: super.getAccountBalance()) {
            if (b.getMoney() > limit) {
                double delta = b.getMoney() * interestRate.getInterest();
                b.increaseBalance(delta);
            }
        }
    }
}
