package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DataGenerator;

public class BillPayPage extends BasePage {

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    private final By billPayLink     = By.linkText("Bill Pay");
    private final By payeeName       = By.name("payee.name");
    private final By address         = By.name("payee.address.street");
    private final By city            = By.name("payee.address.city");
    private final By state           = By.name("payee.address.state");
    private final By zipCode         = By.name("payee.address.zipCode");
    private final By phone           = By.name("payee.phoneNumber");
    private final By account         = By.name("payee.accountNumber");
    private final By verifyAccount   = By.name("verifyAccount");
    private final By amount          = By.name("amount");
    private final By sendPayment     = By.cssSelector("input[value='Send Payment']");

    private final By resultTitle     = By.cssSelector("#billpayResult .title");
    private final By payeeNameError  = By.id("validationModel-name");
    private final By accountMismatch = By.id("validationModel-verifyAccount-mismatch");
    private final By amountError     = By.id("validationModel-amount-invalid");

    public BillPayPage openBillPayPage(){
        click(billPayLink);
        return this;
    }

    public BillPayPage fillAndSend(String name, String addr, String cityName,
                                   String stateName, String zip, String phoneNumber,
                                   String accountNumber, String verifyNumber,
                                   String amountValue){
        send(payeeName, name);
        send(address, addr);
        send(city, cityName);
        send(state, stateName);
        send(zipCode, zip);
        send(phone, phoneNumber);
        send(account, accountNumber);
        send(verifyAccount, verifyNumber);
        send(amount, amountValue);
        click(sendPayment);
        return this;
    }

    public BillPayPage payWithRandomData(String accountNumber, String verifyNumber,
                                         String amountValue){
        return fillAndSend(
                DataGenerator.firstName(),
                DataGenerator.address(),
                DataGenerator.city(),
                DataGenerator.state(),
                DataGenerator.zipCode(),
                DataGenerator.phone(),
                accountNumber, verifyNumber, amountValue);
    }

    public BillPayPage sendEmpty(){
        click(sendPayment);
        return this;
    }

    public String getResultTitle()          {return getText(resultTitle);}
    public String getPayeeNameError()       {return getText(payeeNameError);}
    public String getAccountMismatchError() {return getText(accountMismatch);}
    public String getAmountError()          {return getText(amountError);}
}
