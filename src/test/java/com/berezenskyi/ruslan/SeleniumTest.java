package com.berezenskyi.ruslan;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("file:///" + System.getProperty("user.dir") + ConfProperties.getProperty("testpage"));
    }

    @Test
    public void test1() {
        Assert.assertTrue("Email field has not been found", mainPage.isPresent(By.id(mainPage.inputEmail)));
        Assert.assertTrue("Password field has not been found", mainPage.isPresent(By.id(mainPage.inputPassword)));
        Assert.assertTrue("Sign in button is not present", mainPage.isPresent(By.xpath(mainPage.signIn)));
        mainPage.inputEmail("email@com");
        mainPage.inputPassword("password");
        mainPage.clickSignInBtn();
    }

    @Test
    public void test2() {
        List<WebElement> allElements = mainPage.listGroup();
        WebElement listItem2 = allElements.get(1);
        Assert.assertEquals("There are not three values in the listgroup", 3, allElements.size());
        Assert.assertTrue("Second list item's value is not set to List Item 2", listItem2.getText().contains("List Item 2"));
        Assert.assertEquals("The second list item's badge value is not 6", "6", listItem2.findElement(By.className("badge")).getText());
    }

    @Test
    public void test3() {
        Assert.assertTrue("Option 1 is not the default selected value", mainPage.dropdownMenuButton.getText().contains("Option 1"));
        mainPage.clickDropdownMenuButton();
        mainPage.dropdownItem("Option 3").click();
    }

    @Test
    public void test4() {
        Assert.assertTrue("Button is disabled when expected to be enabled", mainPage.activeButton.isEnabled());
        Assert.assertFalse("Button is active when expected to be disabled", mainPage.disabledButton.isEnabled());
    }

    @Test
    public void test5() {
        WebElement hiddenButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("test5-button")));
        mainPage.hiddenButton.click();
        Assert.assertFalse("Button is active when expected to be disabled", mainPage.hiddenButton.isEnabled());
        Assert.assertTrue("Success message is wrong", mainPage.alert.getText().contains("You clicked a button!"));
    }

    @Test
    public void test6() {
        Assert.assertTrue("The value of the cell is not Ventosanzap", mainPage.getElementByCoordinates(2, 2).getText().contains("Ventosanzap"));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
