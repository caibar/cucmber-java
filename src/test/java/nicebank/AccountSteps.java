package nicebank;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import support.KnowsTheAccount;
import transforms.MoneyConverter;

public class AccountSteps {

    KnowsTheAccount accountHelper;

    public AccountSteps(KnowsTheAccount helper) {
        this.accountHelper = helper;
    }

    @Given("^my account has been credited with \\$(\\d+\\.\\d+)$")
    public void myAccounthasBeenCreditedWith$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        accountHelper.getMyAccount().credit(amount);
    }

    @Then("^the balance of my account should be \\$(\\d+\\.\\d+)$")
    public void theBalanceOfMyAccountShouldBe$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        int timeoutMilliSecs = 3000;
        int pollIntervalMilliSecs = 100;
        while (!accountHelper.getMyAccount().getBalance().equals(amount) && timeoutMilliSecs > 0) {
            Thread.sleep(pollIntervalMilliSecs);
            timeoutMilliSecs -= pollIntervalMilliSecs;
        }

        Assert.assertEquals("Incorrect account balance -", amount, accountHelper.getMyAccount().getBalance());
    }

    @Then("^I should be told that I have insufficient funds in my account$")
    public void iShouldBeToldThatIHaveInsufficientFundsInMyAccount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
