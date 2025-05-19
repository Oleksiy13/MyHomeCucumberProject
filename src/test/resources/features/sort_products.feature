Feature: Product sorting

  Scenario: User sorts products by name
    Given the user is on the home page
    And the user navigates to the product page
    When the user sort products by name
    Then the products are displayed in alphabetical order