public class Loan {
    private double loan;
    private InterestRate rate;

    public Loan(double loan, InterestRate rate) {
        this.loan = loan;
        this.rate = rate;
    }

    public void calculateInterest() {
        loan += loan * rate.getInterest();
    }

    public double getLoan() {
        return loan;
    }
}
