package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ForgotLoginPage;
import pages.LoginPage;
import utils.ConfigReader;


//Note: Tests requiring valid account credentials are temporarily disabled because ParaBank may
//occasionally reset or change account data, causing previously valid credentials to become invalid.
//These tests can be re-enabled once the account credentials are verified.

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage(){
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Verify login succeeds with valid credentials", enabled = false)
    @Feature("Login")
    @Story("Login With Valid Data")
    @Description("Note: This test is temporarily disabled because ParaBank may reset or change account credentials, causing previously valid login data to become invalid. Re-enable the test after verifying the account credentials.")
    public void loginWithValidData(){
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        Assert.assertEquals
                (loginPage.getPageTitle(), "Accounts Overview");
    }

    @Test(description = "Verify validation appears when username and password are empty")
    @Feature("Login")
    @Story("Login With Empty Fields")
    @Description("Verify that the system displays an error message when the login form is submitted without a username and password.")
    public void loginWithEmptyFields(){
        loginPage.loginWithEmptyFields();
        Assert.assertEquals(loginPage.getErrorText(),
                "Please enter a username and password.");
    }

    @Test(description = "Verify login fails with an incorrect password", enabled = false)
    @Feature("Login")
    @Story("Login With Wrong Password")
    @Description("Note: This test is temporarily disabled because ParaBank may reset or change account credentials, causing previously valid login data to become invalid. Re-enable the test after verifying the account credentials.")
    public void loginWithWrongPassword(){
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("invalidPassword"));

        Assert.assertEquals(loginPage.getErrorText(),
                "The username and password could not be verified.");
    }

    @Test(description = "Verify login fails with an incorrect Username", enabled = false)
    @Feature("Login")
    @Story("Login With Wrong Username")
    @Description("Note: This test is temporarily disabled because ParaBank may reset or change account credentials, causing previously valid login data to become invalid. Re-enable the test after verifying the account credentials.")
    public void loginWithWrongUsername(){

        loginPage.login(ConfigReader.get("invalidUsername"), ConfigReader.get("password"));

        Assert.assertEquals(loginPage.getErrorText(),
                "The username and password could not be verified.");
    }

    @Test(description = "Verify login fails with an incorrect username", enabled = false)
    @Feature("Login")
    @Story("Login With Wrong Username")
    @Description("Note: This test is temporarily disabled because ParaBank may reset or change account credentials, causing previously valid login data to become invalid. Re-enable the test after verifying the account credentials.")
    public void loginAndLogout(){
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        loginPage.logout();
        Assert.assertEquals
                (loginPage.getLoginHeader(), "Customer Login");

    }

    @Test(description = "Verify forgotten login information can be retrieved", enabled = false)
    @Feature("Login")
    @Story("Retrieve Forgotten Login Information")
    @Description("Note: This test is temporarily disabled because ParaBank may reset or change account credentials, causing previously valid login data to become invalid. Re-enable the test after verifying the account credentials.")
    public void ForgotLogiInfo(){

        ForgotLoginPage forgotPage = loginPage.goToForgotLogin();
        forgotPage.fillAndSubmit(
                ConfigReader.get("firstname"),
                ConfigReader.get("lastname"),
                ConfigReader.get("address"),
                ConfigReader.get("city"),
                ConfigReader.get("state"),
                ConfigReader.get("zipcode"),
                ConfigReader.get("ssn"));
        Assert.assertEquals
                (forgotPage.getResultText(),
        "Your login information was located successfully. You are now logged in.");

    }
}



