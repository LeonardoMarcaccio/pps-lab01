import example.model.AccountHolder;
import example.model.SimpleBankAccount;
import example.model.SimpleBankAccountWithFees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBankAccountWithFeesTest extends SimpleBankAccountTest {

    @BeforeEach
    void beforeEach(){
        setAccountHolder(new AccountHolder(getName(), getSurname(), getId()));
        setBankAccount(new SimpleBankAccountWithFees(getAccountHolder(), getDefaultBalance()));
    }

    @Test
    void testInitialBalance() {
        super.testInitialBalance();
    }

    @Test
    void testDeposit() {super.testDeposit();}

    @Test
    void testWrongDeposit() {super.testWrongDeposit();}

    @Test
    void testWithdraw() {
        getBankAccount().deposit(getAccountHolder().id(), getDepositAmount());
        double withdrawLeftoverBalance = getBankAccount().getBalance() - getWithdrawAmount() - SimpleBankAccountWithFees.Fee;
        getBankAccount().withdraw(getAccountHolder().id(), getWithdrawAmount());
        assertEquals(withdrawLeftoverBalance, getBankAccount().getBalance());
    }

    @Test
    void testWrongWithdraw() {super.testWrongWithdraw();}
}
