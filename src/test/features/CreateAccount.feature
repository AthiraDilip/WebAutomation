Feature: Validate the Create an Account functionality

  Scenario: Open the Sign in page
    Given Start the driver instance
    When Open the Home page - YourLogo site
      And I click on "Sign In"
    Then "Sign In" page with title "Login - My Store" is displayed

  Scenario: Validate Email address in Sign in page
    When I click on "Sign In"
    And "Email address" field value is given below values
    |test@test.com|


#Then Kill the driver instance