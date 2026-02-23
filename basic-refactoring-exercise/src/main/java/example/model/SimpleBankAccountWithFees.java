package example.model;

public class SimpleBankAccountWithFees extends SimpleBankAccount {

    private final double Fee;

    public double getFee() {
        return super.getBalance();
    }

    public SimpleBankAccountWithFees(AccountHolder holder, double balance, double fee) {
        super(holder, balance);
        this.Fee = fee;
    }

    @Override
    public void withdraw(final int userId, final double amount) {
        super.withdraw(userId, amount + Fee);
    }
}
