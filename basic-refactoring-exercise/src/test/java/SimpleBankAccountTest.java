import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends BankAccountTest {

    @Override
    void testSetup() {
        this.accountHolder = new AccountHolder(this.name, this.surname, this.id);
        this.bankAccount = new SimpleBankAccount(this.accountHolder, this.defaultBalance);
    }

    @Override
    double expectedWithdrawBalance() {
        return bankAccount.getBalance() - withdrawAmount;
    }
}
