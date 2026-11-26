package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    private final By openAccountLink      = By.linkText("Open New Account");
    private final By accountsOverviewLink = By.linkText("Accounts Overview");
    private final By accountType          = By.id("type");
    private final By fromAccountOptions   = By.cssSelector("#fromAccountId option");
    private final By openButton           = By.cssSelector("input[value='Open New Account']");
    private final By resultTitle          = By.cssSelector("#openAccountResult .title");
    private final By newAccountId         = By.id("newAccountId");


    public OpenAccountPage openAccountPage(){
        click(openAccountLink);
        return this;
    }

    public OpenAccountPage openAccount(String type){
        selectByText(accountType, type);
        waits.waitForElementsMoreThan(fromAccountOptions, 0);
        click(openButton);
        return this;
    }

    public OpenAccountPage goToAccountsOverview(){
        click(accountsOverviewLink);
        return this;
    }

    public String getResultTitle(){return getText(resultTitle);}
    public String getNewAccountId(){return getText(newAccountId);}


    public boolean isAccountListed(String accountId){
        try {
            waits.waitForVisible(By.linkText(accountId));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
