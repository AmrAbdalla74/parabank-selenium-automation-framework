package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginBtn      = By.cssSelector("input.button");
    private final By errorMessage  = By.className("error");
    private final By pageTitle     = By.cssSelector("#rightPanel .title");
    private final By logoutLink    = By.linkText("Log Out");
    private final By loginHeader   = By.cssSelector("#leftPanel h2");
    private final By forgotLink    = By.linkText("Forgot login info?");

    public LoginPage login(String username, String password){
        send(usernameField, username);
        send(passwordField,password);
        click(loginBtn);
        return this;
    }

    public LoginPage loginWithEmptyFields(){
        click(loginBtn);
        return this;
    }

    public void logout(){
        click(logoutLink);
    }

    public ForgotLoginPage goToForgotLogin(){
        click(forgotLink);
        return new ForgotLoginPage(driver);
    }

    public String getPageTitle(){return getText(pageTitle);}
    public String getErrorText(){return getText(errorMessage);}
    public String getLoginHeader(){return getText(loginHeader);}

}
