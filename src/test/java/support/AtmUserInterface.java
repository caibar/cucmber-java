package support;

import hooks.ServerHooks;
import nicebank.Account;
import nicebank.Money;
import nicebank.Teller;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class AtmUserInterface implements Teller {

    private final EventFiringWebDriver webDriver;

    public AtmUserInterface(MyWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void withdrawFrom(Account account, int dollars) {
        webDriver.get("http://localhost:" + ServerHooks.PORT);
        webDriver.findElement(By.id("amount")).sendKeys(String.valueOf(dollars));
        webDriver.findElement(By.id("withdraw")).click();
    }

    public boolean isDisplaying(String message) {
        List<WebElement> list = webDriver
                .findElements(By.xpath("//*[contains(text(),'" + message + "')]"));
        return (list.size() > 0);
    }
}
