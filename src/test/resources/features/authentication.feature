Feature: User Authentication
  As a registered website user
  I want to be able to log in to my account
  So that I can access my personal dashboard and website features

  @positive
  @smoke
  Scenario: Successful login
    Given the user is on the home page
    When the user navigates to the sign-in page
    And the user logs in with valid credentials
    Then the user should be successfully logged in
    And the home page should display the welcome message "Welcome, Alex Pupkin!"


  @negative
  Scenario: Unsuccessful login with incorrect credentials
    Given the user is on the home page
    When the user navigates to the sign-in page
    When the user tries to log in with incorrect credentials
    Then the sign-in page should display the error message