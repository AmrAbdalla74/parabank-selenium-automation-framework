package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.OpenAccountPage;
import pages.RegisterPage;
import utils.DataGenerator;

public class OpenAccountTest extends BaseTest {

    private OpenAccountPage openAccountPage;
    @BeforeMethod
    public void registerAndOpenPage(){
        String pass = DataGenerator.password();
        new RegisterPage(driver)
                .openRegisterPage()
                .fillFormWithRandomData(DataGenerator.randomUsername(), pass, pass)
                .submit();
        openAccountPage = new OpenAccountPage(driver);
        openAccountPage.openAccountPage();
    }

    @Test(description = "Verify that a user can successfully open a savings account")
    @Feature("Open Account")
    @Story("Open Savings Account")
    @Description("Verify that opening a savings account succeeds and a new account ID is generated.")
    public void openSavingsAccount(){
        openAccountPage.openAccount("SAVINGS");

        Assert.assertEquals(openAccountPage.getResultTitle(), "Account Opened!");
        Assert.assertFalse(openAccountPage.getNewAccountId().isEmpty());
    }

    @Test(description = "Verify that a user can successfully open a savings account")
    @Feature("Open Account")
    @Story("Open Savings Account")
    @Description("Verify that opening a savings account succeeds and a new account ID is generated.")
    public void openCheckingAccount(){
        openAccountPage.openAccount("CHECKING");

        Assert.assertEquals(openAccountPage.getResultTitle(), "Account Opened!");
        Assert.assertFalse(openAccountPage.getNewAccountId().isEmpty());
    }

    @Test(description = "Verify that a newly opened account appears in Accounts Overview")
    @Feature("Open Account")
    @Story("Verify Account in Overview")
    @Description("Verify that the newly opened savings account is listed in Accounts Overview using its account ID.")
    public void newAccountAppearsInOverview(){
        openAccountPage.openAccount("SAVINGS");

        String newId = openAccountPage.getNewAccountId();
        openAccountPage.goToAccountsOverview();

        Assert.assertTrue(openAccountPage.isAccountListed(newId),
                "Account " + newId + " not found in Accounts Overview");
    }
}
