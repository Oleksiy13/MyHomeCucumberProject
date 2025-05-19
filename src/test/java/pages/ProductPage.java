package pages;

import context.TestContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    public ProductPage(TestContext context) {
        super(context);
    }


    @FindBy(css = "select#sorter")
    private List<WebElement> sortDropdowns;


    @FindBy(css = ".product-item-link")
    private List<WebElement> productNames;


    public WebElement getVisibleSortDropdown() {
        return sortDropdowns.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Видимый элемент сортировки не найден"));
    }


    public void sortByProductName() {
        WebElement visibleSorter = getVisibleSortDropdown();
        new Select(visibleSorter).selectByVisibleText("Product Name");


        context.wait
                .withTimeout(Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-item")));
    }


    public List<String> getProductNames() {
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}