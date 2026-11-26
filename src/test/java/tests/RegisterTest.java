package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DataGenerator;

public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeMethod
    public void initPage(){
        registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
    }

    @Test(description = "Verify successful registration with valid data")
    @Feature("Registration")
    @Story("Register With Valid Data")
    @Description("Verify that a user can successfully register with valid data and is logged in.")
    public void registerWithValidData(){
        String pass = DataGenerator.password();
        registerPage.fillFormWithRandomData(DataGenerator.randomUsername(), pass, pass)
                .submit();

        Assert.assertEquals(registerPage.getSuccessText(),
                "Your account was created successfully. You are now logged in.");
    }


    @Test(description = "Verify registration fails when using an existing username", enabled = false)
    @Feature("Registration")
    @Story("Register With Existing Username")
    @Description("Note: This test is temporarily disabled because ParaBank may reset or change account credentials, causing previously valid login data to become invalid. Re-enable the test after verifying the account credentials.")
    public void registerWithExistingUsername(){
        String pass = DataGenerator.password();
        registerPage.fillFormWithRandomData(ConfigReader.get("username"), pass, pass)
                .submit();

        Assert.assertEquals(registerPage.getUsernameError(),
                "This username already exists.");
    }

    @Test(description = "Verify registration validation with empty fields")
    @Feature("Registration")
    @Story("Register With Empty Fields")
    @Description("Verify that the system displays a required field error when the registration form is submitted empty.")
    public void registerWithEmptyFields(){
        registerPage.submit();

        Assert.assertEquals(registerPage.getFirstNameError(),
                "First name is required.");
    }

    @Test(description = "Verify registration fails when passwords do not match")
    @Feature("Registration")
    @Story("Register With Mismatched Passwords")
    @Description("Verify that the system displays an error message when the password and confirmation password do not match.")
    public void registerWithMismatchedPasswords(){
        registerPage.fillFormWithRandomData(DataGenerator.randomUsername(),
                DataGenerator.password(),"Different@999")
            .submit();

        Assert.assertEquals(registerPage.getPasswordError(),
                "Passwords did not match.");
    }
}
