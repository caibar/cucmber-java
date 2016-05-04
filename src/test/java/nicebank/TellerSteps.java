package nicebank;

import cucumber.api.java.en.When;
import support.AtmUserInterface;
import support.KnowsTheAccount;
import support.KnowsTheTeller;

public class TellerSteps {

    KnowsTheAccount accountHelper;
    Teller teller;

    public TellerSteps(AtmUserInterface teller, KnowsTheAccount accountHelper) {
        this.teller = teller;
        this.accountHelper = accountHelper;
    }

    @When("^I withdraw \\$(\\d+)$")
    public void iWithdraw$(int dollars) throws Throwable {
        teller.withdrawFrom(accountHelper.getMyAccount(), dollars);
    }

}
