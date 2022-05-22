package com.berezenskyi.ruslan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public final String inputEmail = "inputEmail";
    public final String inputPassword = "inputPassword";
    public final String signIn = "//button[text()='Sign in']";

    @FindBy(id = inputEmail)
    public WebElement emailField;

    @FindBy(id = inputPassword)
    public WebElement passwordField;

    @FindBy(xpath = signIn)
    private WebElement signBtn;

    @FindBy(id = "dropdownMenuButton")
    public WebElement dropdownMenuButton;

    @FindBy(xpath = "//div[@id='test-4-div']/button[1]")
    public WebElement activeButton;

    @FindBy(xpath = "//div[@id='test-4-div']/button[2]")
    public WebElement disabledButton;

    @FindBy(id = "test5-button")
    public WebElement hiddenButton;

    @FindBy(id = "test5-alert")
    public WebElement alert;

    public WebElement dropdownItem(String option) {
        return driver.findElement(By.xpath(String.format("//a[@class='dropdown-item' and text()='%s']", option)));
    }

    public List<WebElement> listGroup() {
        return driver.findElements(By.xpath("//ul[@class='list-group']/li"));
    }

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignInBtn() {
        signBtn.click();
    }

    public void clickDropdownMenuButton() {
        dropdownMenuButton.click();
    }

    public WebElement getElementByCoordinates(int x, int y) {
        List<WebElement> tableRows = driver.findElements(By.xpath("//div[@id='test-6-div']//tbody//tr"));
        WebElement tableRow = tableRows.get(x);
        List<WebElement> tableCells = tableRow.findElements(By.cssSelector("td"));
        return tableCells.get(y);
    }

    public boolean isPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }
}
