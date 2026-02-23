import example.model.AccountHolder;
import example.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class BankAccountTest {
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;
    protected final String name = "Mario";
    protected final String surname = "Rossi";
    protected final int id = 1;
    protected final double defaultBalance = 0;
    protected final int wrongId = 2;
    protected final double depositAmount = 100;
    protected final double withdrawAmount = 70;
    protected final double wrongIdDepositAmount = 50;

    @BeforeEach
    void beforeEach() {
        testSetup();
    }

    @Test
    void testInitialBalance() {
        assertEquals(defaultBalance, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), depositAmount);
        assertEquals(depositAmount, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), depositAmount);
        bankAccount.deposit(wrongId, wrongIdDepositAmount);
        assertEquals(depositAmount, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.id(), depositAmount);
        double expectedWithdrawBalance = expectedWithdrawBalance();
        bankAccount.withdraw(accountHolder.id(), withdrawAmount);
        assertEquals(expectedWithdrawBalance, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), depositAmount);
        double rightIdBalance = bankAccount.getBalance();
        bankAccount.withdraw(wrongId, withdrawAmount);
        assertEquals(rightIdBalance, bankAccount.getBalance());
    }

    abstract void testSetup();
    abstract double expectedWithdrawBalance();
}
