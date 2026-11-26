package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotLoginPage extends BasePage {

    public ForgotLoginPage(WebDriver driver) {
        super(driver);
    }

    private final By firstName  = By.id("firstName");
    private final By lastName   = By.id("lastName");
    private final By address    = By.id("address.street");
    private final By city       = By.id("address.city");
    private final By state      = By.id("address.state");
    private final By zipCode    = By.id("address.zipCode");
    private final By ssn        = By.id("ssn");
    private final By findButton = By.cssSelector("input[value='Find My Login Info']");
    private final By resultText = By.cssSelector("#rightPanel p");


    public ForgotLoginPage fillAndSubmit(String fName, String lName, String addr,
                                         String cName, String sName,
                                         String zip, String ssnNumber){
        send(firstName, fName);
        send(lastName, lName);
        send(address, addr);
        send(city, cName);
        send(state, sName);
        send(zipCode, zip);
        send(ssn, ssnNumber);
        click(findButton);
        return this;
    }

    public String getResultText(){
        return getText(resultText);
    }
}
