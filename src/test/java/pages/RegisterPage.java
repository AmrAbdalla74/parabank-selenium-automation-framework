package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DataGenerator;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private final By registerLink    = By.linkText("Register");
    private final By firstName       = By.id("customer.firstName");
    private final By lastName        = By.id("customer.lastName");
    private final By address         = By.id("customer.address.street");
    private final By city            = By.id("customer.address.city");
    private final By state           = By.id("customer.address.state");
    private final By zipCode         = By.id("customer.address.zipCode");
    private final By phone           = By.id("customer.phoneNumber");
    private final By ssn             = By.id("customer.ssn");
    private final By username        = By.id("customer.username");
    private final By password        = By.id("customer.password");
    private final By confirmPassword = By.id("repeatedPassword");
    private final By registerButton  = By.cssSelector("input[value='Register']");

    private final By successText     = By.cssSelector("#rightPanel p");
    private final By usernameError   = By.id("customer.username.errors");
    private final By passwordError   = By.id("repeatedPassword.errors");
    private final By firstNameError  = By.id("customer.firstName.errors");





    public RegisterPage openRegisterPage(){
        click(registerLink);
        return this;
    }

    public RegisterPage fillForm(String fName, String lName, String addr,
                                 String cityName, String stateName, String zip,
                                 String phoneNumber, String ssnNumber,
                                 String user, String pass, String confirmPass){

        send(firstName, fName);
        send(lastName, lName);
        send(address, addr);
        send(city, cityName);
        send(state, stateName);
        send(zipCode, zip);
        send(phone, phoneNumber);
        send(ssn, ssnNumber);
        send(username, user);
        send(password, pass);
        send(confirmPassword, confirmPass);
        return this;
    }
    public RegisterPage fillFormWithRandomData(String user, String pas, String confirmPass){
        return fillForm(
                DataGenerator.firstName(),
                DataGenerator.lastName(),
                DataGenerator.address(),
                DataGenerator.city(),
                DataGenerator.state(),
                DataGenerator.zipCode(),
                DataGenerator.phone(),
                DataGenerator.ssn(),
                user, pas, confirmPass);
    }


    public RegisterPage submit(){
        click(registerButton);
        return this;
    }


    public String getSuccessText(){return getText(successText);}
    public String getUsernameError(){return getText(usernameError);}
    public String getPasswordError(){return getText(passwordError);}
    public String getFirstNameError(){return getText(firstNameError);}

}
