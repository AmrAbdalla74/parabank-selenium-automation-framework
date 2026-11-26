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
import pages.TransferPage;
import utils.DataGenerator;

public class TransferTest extends BaseTest {

    private TransferPage transferPage;
    private String fromId;
    private String toId;

    @BeforeMethod
    public void prepareTwoAccounts(){
        String pass = DataGenerator.password();
        new RegisterPage(driver)
                .openRegisterPage()
                .fillFormWithRandomData(DataGenerator.randomUsername(), pass, pass)
                .submit();

        OpenAccountPage openAccountPage = new OpenAccountPage(driver);
        openAccountPage.openAccountPage().openAccount("SAVINGS");
        toId = openAccountPage.getNewAccountId();

        transferPage = new TransferPage(driver);
        transferPage.openTransferPage();
        fromId = transferPage.getFirstAccountId();    }

    @Feature("Transfer")
    @Story("Successful Money Transfer")
    @Description("Verify that a user can successfully transfer money between two different accounts using a valid amount.")
    @Test(description = "Verify successful money transfer between two different accounts")
    public void transferValidAmount(){
        transferPage.transfer("10", fromId, toId);

        Assert.assertEquals(transferPage.getResultTitle(), "Transfer Complete!");
    }

    @Feature("Transfer")
    @Story("Transfer Validation")
    @Description("Verify that the system displays an error when the user attempts to transfer money without entering an amount.")
    @Test(description = "Verify error message when transferring with an empty amount")
    public void transferWithEmptyAmount(){
        transferPage.transfer("", fromId, toId);

        Assert.assertEquals(transferPage.getErrorTitle(), "Error!");
        Assert.assertEquals(transferPage.getErrorText(), "An internal error has occurred and has been logged.");
    }

    @Feature("Transfer")
    @Story("Transfer Validation")
    @Description("Verify that the system displays an error when the user enters non-numeric characters as the transfer amount.")
    @Test(description = "Verify error message when transferring with a non-numeric amount")
    public void transferWithNonNumericAmount() {
        transferPage.transfer("abc", fromId, toId);

        Assert.assertEquals(transferPage.getErrorTitle(), "Error!");
    }

    @Feature("Transfer")
    @Story("Transfer Validation")
    @Description("Verify the system's behavior when the user attempts to transfer money from an account to the same account.")
    @Test(description = "Verify system behavior when transferring money to the same account")
    public void transferToSameAccount() {
        transferPage.transfer("10", fromId, fromId);

        System.out.println("Result title: " + transferPage.getResultTitle());
    }
}

