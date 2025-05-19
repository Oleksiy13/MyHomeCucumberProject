package steps;


import context.TestContext;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SortSteps {

    private final TestContext context;
    private HomePage homePage;
    private ProductPage productPage;
    private List<String> productNamesBeforeSort;

    public SortSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context);
        this.productPage = new ProductPage(context);
    }


    @And("the user navigates to the product page")
    public void the_user_navigates_to_the_product_page() {
        productPage = homePage.goToProductsPage();
        productNamesBeforeSort = new ArrayList<>(productPage.getProductNames());
    }

    @When("the user sort products by name")
    public void the_user_sort_products_by_name() {
        productPage.sortByProductName();
    }

    @Then("the products are displayed in alphabetical order")
    public void the_products_are_displayed_in_alphabetical_order() {
        List<String> sortedNames = new ArrayList<>(productNamesBeforeSort);
        Collections.sort(sortedNames, String.CASE_INSENSITIVE_ORDER);

        List<String> productNamesAfterSort = productPage.getProductNames();

        Assertions.assertEquals(sortedNames, productNamesAfterSort,
                "Products are not sorted alphabetically by name.");
    }
}
