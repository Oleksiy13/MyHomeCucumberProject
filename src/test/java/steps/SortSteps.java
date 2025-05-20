package steps;


import context.TestContext;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ProductPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SortSteps {

    private final TestContext context;
    private HomePage homePage;
    private ProductPage productPage; // Не инициализируем здесь
    private List<String> productNamesBeforeSort;

    public SortSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context);
    }


    @And("the user navigates to the product page")
    public void the_user_navigates_to_the_product_page() {
        productPage = homePage.goToProductsPage(); // Инициализируем ProductPage после перехода
        productNamesBeforeSort = productPage.getProductNames(); // Получаем названия сразу после перехода
    }

    @When("the user sort products by name")
    public void the_user_sort_products_by_name() {
        productPage.sortByProductName();
    }

    @Then("the products are displayed in alphabetical order")
    public void the_products_are_displayed_in_alphabetical_order() {
        List<String> sortedNames = new ArrayList<>(productNamesBeforeSort);
        Collections.sort(sortedNames, String.CASE_INSENSITIVE_ORDER);
        context.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-item-link"))); // Используем context.wait
        List<String> productNamesAfterSort = productPage.getProductNames();  // Получаем список *после* сортировки
        Assertions.assertEquals(sortedNames, productNamesAfterSort, "Products are not sorted alphabetically.");
    }
}
