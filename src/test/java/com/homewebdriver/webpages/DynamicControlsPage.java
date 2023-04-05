package com.homewebdriver.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicControlsPage {
    static WebDriver driver;

    private static String DYNAMIC_CONTROL_URL="http://localhost:7080/dynamic_controls";

    @FindBy(xpath = "//div[@id='checkbox']")
    public WebElement checkBox;

    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    public WebElement removeButton;

    @FindBy(xpath = "//button[contains(text(),'Add')]")
    public WebElement addButton;

    @FindBy(xpath = "//p[@id='message']")
    public WebElement responseMessage;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement textBox;

    @FindBy(xpath = "//button[contains(text(),'Enable')]")
    public WebElement enableButton;

    @FindBy(xpath = "//button[contains(text(),'Disable')]")
    public WebElement disableButton;


    public DynamicControlsPage(final WebDriver driver){
        this.driver=driver;
        driver.get(DYNAMIC_CONTROL_URL);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    public boolean verifyAddAndRemoveCheckbox() throws InterruptedException {
        Boolean flag = false;
        if(checkBox.isDisplayed()){
            removeButton.click();
            Thread.sleep(3000);
             flag = responseMessage.getText().equals("It's gone!");
            return flag;
        }
        else if(addButton.isDisplayed()){
            addButton.click();
            Thread.sleep(3000);
            flag = checkBox.isDisplayed() && responseMessage.getText().equals("It's back!");
            return flag;

        }
        return flag;

    }
    public boolean verifyEnableAndDisable() throws InterruptedException {
        Boolean flag = false;
        if(!textBox.isEnabled()){
            enableButton.click();
            Thread.sleep(3000);
           flag = textBox.isEnabled() && responseMessage.getText().equals("It's enabled!");
            return flag;
        }
        else if(textBox.isEnabled() && disableButton.isDisplayed()){
            disableButton.click();
            Thread.sleep(3000);
            Boolean flag1 = textBox.isEnabled();
            Boolean flag2 = responseMessage.getText().equals("It's disabled!");
            if(flag1 == false && flag2 == true){
                flag = true;
            }
            return flag;

        }
        return flag;

    }
}