package steps;

import context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;

public class SearchProductSteps {

    private final TestContext context;
    private HomePage homePage;

    public SearchProductSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context);
    }

    @When("the user searches for {string}")
    public void userSearch(String searchText) {
        homePage.search(searchText);
    }

    @Then("search results are displayed")
    public void searchResults() {
        Assertions.assertTrue(homePage.searchResults.size() > 0, "Search results are not displayed.");
    }

    @Then("the first search result should contain {string}")
    public void firstSearchResult(String expectedText) {
        String firstProductTitle = homePage.getFirstSearchResult().getText();
        Assertions.assertTrue(firstProductTitle.contains(expectedText), "Первый товар не соответствует поисковому запросу.");
    }

    @Then("the following message should be displayed indicating no search results:")
    public void theFollowingMessageShouldBeDisplayedIndicatingNoSearchResults(String expectedMessage) {
        String actualMessage = homePage.wrongSearchMessage.getText().trim();
        Assertions.assertEquals(expectedMessage, actualMessage, "The message about the absence of search results does not match the expected one.");
    }
}
