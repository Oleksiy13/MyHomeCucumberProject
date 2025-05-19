Feature: Adding a product to the shopping cart from the product page
  As a website user
  I want to verify that I can add a product to the shopping cart from the product page

  Scenario: Add Montana Wind Jacket to cart and verify
    Given the user is on the home page
    And the user navigates to the "Montana Wind Jacket" product page
    When the user adds an item to the cart
    Then the added product "Montana Wind Jacket" should be in the cart

  Scenario: Add Montana Wind Jacket to cart without parameters
    Given the user is on the home page
    And the user navigates to the "Montana Wind Jacket" product page
    When the user clicks the "Add to Cart" button
    Then Required fields message is shown