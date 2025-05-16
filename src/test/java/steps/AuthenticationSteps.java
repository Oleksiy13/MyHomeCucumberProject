package steps;
import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import pages.SignInPage;
import utils.ConfigurationReader;


public class AuthenticationSteps {

    private final TestContext context;
    private HomePage homePage;
    private SignInPage signInPage;

    public AuthenticationSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context);
        this.signInPage = new SignInPage(context);
    }

    @Given("the user is on the home page")
    public void onHomePage() {
        context.driver.get(ConfigurationReader.get("url"));
        homePage.acceptCookie();
    }

    @When("the user navigates to the sign-in page")
    public void goToSignIn() {
        homePage.goToSignInPage();
    }


    @When("the user logs in with valid credentials")
    public void userLogIn() {
        signInPage.login();
    }

    @Then("the user should be successfully logged in")
    public void isLoggedIn() {
        Assertions.assertTrue(homePage.isUserSignedIn(), "User should be logged in");
    }

    @Then("the home page should display the welcome message {string}")
    public void welcomeMessageDisplayed(String expectedMessage) {
        Assertions.assertTrue(homePage.welcomeMessage.getText().contains(expectedMessage),
                "Welcome message should contain: " + expectedMessage);
    }


    @When("the user tries to log in with incorrect credentials")
    public void userTriesWrongLogin() {
        signInPage.wrongLogin();
    }

    @Then("the sign-in page should display the error message")
    public void userShouldNotBeLoggedIn() {
        boolean isNotSignedIn = signInPage.isUserNotSignedIn();
        Assertions.assertTrue(isNotSignedIn, "Пользователь залогинен, хотя не должен был");
    }


}
