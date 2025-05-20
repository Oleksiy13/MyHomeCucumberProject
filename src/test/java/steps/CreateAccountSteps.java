package steps;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import pages.NewCustomerPage;

public class CreateAccountSteps {

    private final TestContext context;
    private HomePage homePage;
    private NewCustomerPage newCustomerPage;

    public CreateAccountSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context);
        this.newCustomerPage = new NewCustomerPage(context);

    }

    @And("the user navigates to the Create New Customer Account page")
    public void goToCreateAccountPage(){
        homePage.goToCreateAccountPage();
    }

    @When("the user fills in the required fields with an existing email and password")
    public void fillsFields(){
        newCustomerPage.createAccount();
    }

    @Then("an error message {string} should be displayed")
    public void errorMessageDisplayed(String expectedErrorMessage) {
        String actualMessage = newCustomerPage.getExistingAccountErrorMessageText().trim();

        Assertions.assertTrue(actualMessage.contains(expectedErrorMessage),
                "Expected error message '" + expectedErrorMessage +
                        "' was not found in actual message: '" + actualMessage + "'");
    }
}
