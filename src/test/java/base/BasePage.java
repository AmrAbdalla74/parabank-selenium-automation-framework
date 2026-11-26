package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

public class BasePage {
    protected WebDriver driver ;
    protected WaitUtils waits;

    public BasePage(WebDriver driver){
        this.driver=driver;
        this.waits=new WaitUtils(driver);
    }

    protected void click(By locator){
        waits.waitForClickable(locator).click();
    }

    protected void send(By locator, String text){
        waits.waitForVisible(locator).sendKeys(text);
    }

    protected String getText(By locator){
        return waits.waitForVisible(locator).getText().trim();
    }

    protected void  selectByText(By locator, String text){
        new Select(waits.waitForVisible(locator)).selectByVisibleText(text);
    }

}
