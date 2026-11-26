package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BillPayPage;
import pages.RegisterPage;
import utils.DataGenerator;

public class BillPayTest extends BaseTest {

    private BillPayPage billPayPage;

    @BeforeMethod
    public void registerAndOpenBillPay(){
        String username = DataGenerator.randomUsername();
        String pass = DataGenerator.password();
        System.out.println("Generated Username: " + username);
        new RegisterPage(driver)
                .openRegisterPage()
                .fillFormWithRandomData(DataGenerator.randomUsername(), pass, pass)
                .submit();

        billPayPage = new BillPayPage(driver);
        billPayPage.openBillPayPage();
    }

    @Test(description = "Verify bill payment succeeds with valid data")
    @Feature("Bill Payment")
    @Story("Pay Bill With Valid Data")
    @Description("Verify that a user can complete a bill payment when valid payment details are provided.")
    public void payBillWithValidData(){

        billPayPage.payWithRandomData("1122", "1122","100");

        Assert.assertEquals(billPayPage.getResultTitle(), "Bill Payment Complete");
    }

    @Test(description = "Verify validation appears when bill payment fields are empty")
    @Feature("Bill Payment")
    @Story("Pay Bill With Empty Fields")
    @Description("Verify that the system displays a required-field error when the bill payment form is submitted empty.")
    public void payBillWithEmptyFields(){

        billPayPage.sendEmpty();

        Assert.assertEquals(billPayPage.getPayeeNameError(),"Payee name is required.");
    }

    @Test(description = "Verify validation appears when account numbers do not match")
    @Feature("Bill Payment")
    @Story("Pay Bill With Mismatched Accounts")
    @Description("Verify that the system rejects a bill payment when the account number and confirmation account number do not match.")
    public void payBillWithMismatchedAccounts(){

        billPayPage.payWithRandomData("1122", "3344", "100");

        Assert.assertEquals(billPayPage.getAccountMismatchError(),
                "The account numbers do not match.");
    }

    @Test(description = "Verify validation appears when the payment amount is non-numeric")
    @Feature("Bill Payment")
    @Story("Pay Bill With Non-Numeric Amount")
    @Description("Verify that the system displays a validation error when a non-numeric amount is entered.")
    public void payBillWithNonNumericAmount(){

        billPayPage.payWithRandomData("1122", "1122", "AA");

        Assert.assertEquals(billPayPage.getAmountError(),
                "Please enter a valid amount.");
    }
}
