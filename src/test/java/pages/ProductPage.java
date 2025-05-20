package pages;

import context.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    private WebElement sortDropdown; // Изменили на приватный WebElement

    @FindBy(css = ".product-item-link")
    public List<WebElement> productNames;

    public ProductPage(TestContext context) {
        super(context);
        this.sortDropdown = findVisibleSortDropdown(); // Инициализируем видимый элемент в конструкторе
    }

    private WebElement findVisibleSortDropdown() {
        WebDriverWait wait = new WebDriverWait(context.driver, Duration.ofSeconds(15));
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='sorter']"))
        );
    }

    // Теперь работаем с найденным видимым элементом sortDropdown
    public void sortByProductName() {
        WebDriverWait wait = new WebDriverWait(context.driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Product Name");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-item-link")));
    }

    public List<String> getProductNames() {
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
