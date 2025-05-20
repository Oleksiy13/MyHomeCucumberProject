package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.CartPage;
import pages.HomePage;
import pages.JacketPage;
import utils.ConfigurationReader;

public class AddToCartSteps {

    private final TestContext context;
    private HomePage homePage;
    private JacketPage jacketPage;
    private CartPage cartPage;

    public AddToCartSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context);
        this.jacketPage = new JacketPage(context);
        this.cartPage = new CartPage(context);
    }


    @Given("the user navigates to the \"Montana Wind Jacket\" product page")
    public void jacketPage() {
        homePage.goToJacketPage();
    }

    @When("the user adds an item to the cart")
    public void addJacketToCart() {
        jacketPage.addToCart();
    }

    @Then("the added product \"Montana Wind Jacket\" should be in the cart")
    public void checkProductInCart() {
        Assertions.assertTrue(cartPage.isOnCartPage(), "Not on cart page");
        Assertions.assertTrue(cartPage.isCorrectProductInCart("Montana Wind Jacket"), "Product is not in the cart");
    }

    @When("the user clicks the \"Add to Cart\" button")
    public void buttonClick(){
        jacketPage.addToCartButton.click();
    }

    @Then ("Required fields message is shown")
    public void requiredFieldsMessage(){
        Assertions.assertTrue(jacketPage.requiredFieldsMessage.isDisplayed(), "Ьessage not shown");

    }
}