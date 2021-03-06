package nicebank;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import support.AtmUserInterface;

public class TellerSteps {

    Account account;
    AtmUserInterface teller;

    public TellerSteps(AtmUserInterface teller, Account account) {
        this.teller = teller;
        this.account = account;
    }

    @When("^I withdraw \\$(\\d+)$")
    public void iWithdraw$(int dollars) throws Throwable {
        teller.withdrawFrom(account, dollars);
    }

    @Given("^I request some of my money$")
    public void iRequestSomeOfMyMoney() {
        int dollarsRequested = 10;
        teller.withdrawFrom(account, dollarsRequested);
    }

    @Then("^I should see an out-of-order message$")
    public void iShouldSeeAnOutOfOrderMessage() throws Throwable {
        Assert.assertTrue(
                "Expected error message not displayed",
                teller.isDisplaying("Out of order"));
    }

    @Then("^I should see an ask-for-less-money message$")
    public void iShouldSeeAnAskForLessMoneyMessage() throws Throwable {
        Assert.assertTrue(
                "Expected error message not displayed",
                teller.isDisplaying("Insufficient ATM funds"));
    }
}
