package nicebank;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import support.TestCashSlot;

public class CashSlotSteps {

    TestCashSlot testCashSlot;

    public CashSlotSteps(TestCashSlot testCashSlot) {
        this.testCashSlot = testCashSlot;
    }

    @Then("^\\$(\\d+) should be dispensed$")
    public void $ShouldBeDispensed(int dollars) throws Throwable {
        Assert.assertEquals("Incorrect amount dispensed -", dollars, testCashSlot.getContents());
    }

    @Given("^the cash slot has developed a fault$")
    public void theCashSlotHasDevelopedAFault() throws Throwable {
        testCashSlot.injectFault();
    }

    @Given("^the ATM contains \\$(\\d+)$")
    public void theATMContains$(int dollars) throws Throwable {
        testCashSlot.load(dollars);
    }
}
