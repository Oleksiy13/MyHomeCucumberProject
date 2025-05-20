Feature: Existing Account Creation Prevention

  As a website user,
  I want to ensure that it's not possible to create a duplicate account.

  Scenario: Attempting to create an account with an existing email
    Given the user is on the home page
    And the user navigates to the Create New Customer Account page
    When the user fills in the required fields with an existing email and password
    Then an error message "There is already an account with this email address." should be displayed