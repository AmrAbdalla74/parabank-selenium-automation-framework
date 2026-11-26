package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TransferPage extends BasePage {

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    private final By transferLink    = By.linkText("Transfer Funds");
    private final By amountField     = By.id("amount");
    private final By fromAccount     = By.id("fromAccountId");
    private final By toAccount       = By.id("toAccountId");
    private final By fromOptions     = By.cssSelector("#fromAccountId option");
    private final By toOptions       = By.cssSelector("#toAccountId option");
    private final By transferButton  = By.cssSelector("input[value='Transfer']");
    private final By resultTitle     = By.cssSelector("#showResult .title");
    private final By errorTitle = By.cssSelector("#showError .title");
    private final By errorText  = By.cssSelector("#showError .error");

    public TransferPage  openTransferPage(){
        click(transferLink);
        waits.waitForElementsMoreThan(fromOptions,1);
        waits.waitForElementsMoreThan(toOptions,1);
        return this;
    }

    public String getFirstAccountId(){
        List<WebElement> options = driver.findElements(fromOptions);
        return options.get(0).getText().trim();
    }

    public TransferPage transfer(String amount, String fromId, String toId){
        send(amountField, amount);
        send(fromAccount, fromId);
        send(toAccount, toId);
        click(transferButton);
        return this;
    }

    public String getResultTitle() {
        return getText(resultTitle);
    }

    public String getErrorTitle(){return getText(errorTitle);}
    public String getErrorText(){return getText(errorText);}
}
