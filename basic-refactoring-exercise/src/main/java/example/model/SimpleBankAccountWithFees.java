package example.model;

public class SimpleBankAccountWithFees extends SimpleBankAccount {

    public static final double Fee = 1;

    public SimpleBankAccountWithFees(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void withdraw(final int userId, final double amount) {
        super.withdraw(userId, amount + Fee);
    }
}
