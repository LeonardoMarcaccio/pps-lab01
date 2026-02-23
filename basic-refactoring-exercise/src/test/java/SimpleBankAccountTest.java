import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;
    private final String name = "Mario";
    private final String surname = "Rossi";
    private final int id = 1;
    private final int wrongId = 2;
    private final double defaultBalance = 0;
    private final double depositAmount = 100;
    private final double withdrawAmount = 70;
    private final double wrongIdDepositAmount = 50;

    protected AccountHolder getAccountHolder() {return this.accountHolder;}
    protected void setAccountHolder(AccountHolder accountHolder) {this.accountHolder = accountHolder;}
    protected BankAccount getBankAccount() {return this.bankAccount;}
    protected void setBankAccount(BankAccount bankAccount) {this.bankAccount = bankAccount;}
    protected String getName() {return this.name;}
    protected String getSurname() {return this.surname;}
    protected int getId() {return this.id;}
    protected double getDefaultBalance() {return this.defaultBalance;}
    protected double getDepositAmount() {return this.depositAmount;}
    protected double getWithdrawAmount() {return this.withdrawAmount;}

    @BeforeEach
    void beforeEach(){
        setAccountHolder(new AccountHolder(name, surname, id));
        setBankAccount(new SimpleBankAccount(accountHolder, defaultBalance));
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
        double withdrawLeftoverBalance = bankAccount.getBalance() - withdrawAmount;
        bankAccount.withdraw(accountHolder.id(), withdrawAmount);
        assertEquals(withdrawLeftoverBalance, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), depositAmount);
        double rightIdBalance = bankAccount.getBalance();
        bankAccount.withdraw(wrongId, withdrawAmount);
        assertEquals(rightIdBalance, bankAccount.getBalance());
    }
}
