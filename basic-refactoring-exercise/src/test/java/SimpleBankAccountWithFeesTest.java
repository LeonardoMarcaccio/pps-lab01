import example.model.AccountHolder;
import example.model.SimpleBankAccountWithFees;

public class SimpleBankAccountWithFeesTest extends BankAccountTest {
    private final double DefaultFee = 1;

    @Override
    void testSetup() {
        this.accountHolder = new AccountHolder(this.name, this.surname, this.id);
        this.bankAccount = new SimpleBankAccountWithFees(this.accountHolder, this.defaultBalance, this.DefaultFee);
    }

    @Override
    double expectedWithdrawBalance() {
        return bankAccount.getBalance() - withdrawAmount - this.DefaultFee;
    }
}
