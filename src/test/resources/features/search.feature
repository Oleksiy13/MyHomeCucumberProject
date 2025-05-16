Feature: Search for products on the website
  As a website user
  I want to be able to search for products
  So that I can find the products I need

  And I want to receive a message indicating no results,
  when my search query returns no matches

  @positive
  @smoke
  Scenario: Searching for a product on the site
    Given the user is on the home page
    When the user searches for "Argus All-Weather Tank"
    Then search results are displayed
    And the first search result should contain "Argus All-Weather Tank"


  @negative
  Scenario: Searching for a non-existent product on the site
    Given the user is on the home page
    When the user searches for "Футболка"
    Then the following message should be displayed indicating no search results:
      """
      Your search returned no results.
      """