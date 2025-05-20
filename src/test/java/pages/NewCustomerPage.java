package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

public class NewCustomerPage extends BasePage {


    public NewCustomerPage(TestContext context) {
        super(context);
    }

    @FindBy(css = "#firstname")
    public WebElement firstName;

    @FindBy(css = "#lastname")
    public WebElement lastName;

    @FindBy(css = "#email_address")
    public WebElement emailAddress;

    @FindBy(css = "input#password")
    public WebElement password;

    @FindBy(css = "#password-confirmation")
    public WebElement confirmPassword;

    @FindBy(xpath = "//button[span[text()='Create an Account']]")
    public WebElement createAccountButton;

    @FindBy(xpath = "//div[contains(@class, 'message-error') and contains" +
            "(., 'There is already an account with this email address.')]")
    public WebElement existingAccountErrorMessage;

    public NewCustomerPage createAccount(){

        firstName.sendKeys(ConfigurationReader.get("firstName"));
        lastName.sendKeys(ConfigurationReader.get("lastName"));
        emailAddress.sendKeys(ConfigurationReader.get("userName"));
        password.sendKeys(ConfigurationReader.get("userPassword"));
        confirmPassword.sendKeys(ConfigurationReader.get("userPassword"));
        createAccountButton.click();
        return new NewCustomerPage(context);

    }

    public String getExistingAccountErrorMessageText() {
        context.wait.until(ExpectedConditions.visibilityOf(existingAccountErrorMessage));
        return existingAccountErrorMessage.getText();
    }

}
