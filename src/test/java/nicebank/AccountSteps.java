package nicebank;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import transforms.MoneyConverter;

public class AccountSteps {

    Account account;
    private Money originalBalance;

    public AccountSteps(Account account) {
        this.account = account;
    }

    @Given("^my account has been credited with \\$(\\d+\\.\\d+)$")
    public void myAccounthasBeenCreditedWith$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        account.credit(amount);
    }

    @Given("^my account is in credit$")
    public void myAccountIsInCredit$() throws Throwable {
        originalBalance = new Money(30, 00);
        account.credit(originalBalance);
    }

    @Then("^the balance of my account should be unchanged$")
    public void theBalanceOfMyAccountShouldBeUnchanged() throws Throwable{
        checkBalanceIs(originalBalance);
    }

    @Then("^the balance of my account should be \\$(\\d+\\.\\d+)$")
    public void theBalanceOfMyAccountShouldBe$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        checkBalanceIs(amount);
    }

    private void checkBalanceIs(Money amount) throws InterruptedException {
        int timeoutMilliSecs = 3000;
        int pollIntervalMilliSecs = 100;
        while (!account.getBalance().equals(amount) && timeoutMilliSecs > 0) {
            Thread.sleep(pollIntervalMilliSecs);
            timeoutMilliSecs -= pollIntervalMilliSecs;
        }

        Assert.assertEquals("Incorrect account balance -", amount, account.getBalance());
    }

}
