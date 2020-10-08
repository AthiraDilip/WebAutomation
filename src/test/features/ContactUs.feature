Feature: Validate the Contact Us

  #Positive Scenario: Open Contact Us page
  Scenario: Open the Contact Us page
    Given Start the driver instance
    Then Open the Home page - YourLogo site
    Then "Home" page with title "My Store" is displayed
    When I click on "Contact Us"
    Then "Contact Us" page with title "Contact us - My Store" is displayed

  #Positive Scenario: Field validation - Subject Heading dropdown field value list
  Scenario: Validate the Subject heading dropdown values
    When "Contact Us" page with title "Contact us - My Store" is displayed
    Then "Subject heading" dropdown should contain below values
        |-- Choose --       |
        |Customer service   |
        |Webmaster          |

  #Negative Scenario: Field validation - Subject Heading dropdown field
  Scenario: Field validation for Subject Heading dropdown field - Blank data
    When Subject heading dropdown is "-- Choose --"
    And "Email address" field value is given below values
    |test@test.test|
    And "Message" field value is given below values
    |test|
    And User clicks on "Send" button
    Then Error messages should be displayed for "Subject Heading" field

#  #Negative Scenario: Field validation - Subject Heading dropdown field
#  Scenario Outline: Field validation for Subject Heading dropdown field
#    When Subject heading dropdown is "<subject heading choice>"
#    Then "<error message for subject heading>" message for "<subject heading choice>" dropdown is displayed
#    Examples:
#    |subject heading choice|error message for subject heading            |
#    |Customer service      |For any question about a product, an order   |
#    |Webmaster             |If a technical problem occurs on this website|

  #Negative Scenario: Field validation - Subject Heading dropdown field
  Scenario: Field validation for Subject Heading dropdown field - without using Outline
    Then validate the error message dropdown is displayed
      |subject heading       |error message                                 |
      |Customer service      |For any question about a product, an order    |
      |Webmaster             |If a technical problem occurs on this website |

  #Negative Scenario: Field validation - Email address field
  Scenario: Field validation for Email address field
    When "Email address" field value is given below values
      |" "                  |
      |test                 |
      |test@test            |
      |test test@test.test  |
      |test@test#test.test  |
    And User clicks on "Send" button
    Then Error messages should be displayed for "Email address" field

  #Negative Scenario: Field validation - Message field
  Scenario: Field validation for Message field
    When "Message" field value is given below values
      |" "           |
    And "Email address" field value is given below values
      |test@test.test|
    And User clicks on "Send" button
    Then Error messages should be displayed for "Message" field



  #Positive Scenario: Sending a message without attachment
  Scenario: Sending a message successfully in Contact Us page without attachment
    When I click on "Contact Us"
    When Subject heading dropdown is "Customer service"
    And "Email address" field value is given below values
    |test_test@test.test|
    And "Message" field value is given below values
    |~!@#$%^&*()_+\`-={}:\"<>?[]\;\',./|
    And User clicks on "Send" button
    Then Success message should be displayed
    And "Home" button should be displayed
    When User clicks on "Home" button
    Then "Home" page with title "My Store" is displayed
#      | subject heading choice | valid email field values | valid message field values      |
#      | Customer service       | test_test@test.test      | ~!@#$%^&*()_+\`-={}:\"<>?[]\;\',./ |
#      | Customer service       | test@test.test           | test                            |
#      | Webmaster              | test@test.test.test      | 123                             |
#      | Customer service       | test-test@test.test      | test                            |
#      | Customer service       | test1@test.test          | test                            |
#      | Customer service       | test1@test.test          | test                            |
#      | Customer service       | test@test-test.test      | test                            |
#      | Customer service       | test@test-test.tt        | test                            |

  #Positive Scenario: Sending a message with attachment
  Scenario: Sending a message successfully in Contact Us page
    When I click on "Contact Us"
    When Subject heading dropdown is "Customer service"
    And "Email address" field value is given below values
    |test@test.test|
    And User attaches a file in "Attach File" field
    And "Message" field value is given below values
    |test|
    And User clicks on "Send" button
    Then Success message should be displayed
    And "Home" button should be displayed
    When User clicks on "Home" button
    Then "Home" page with title "My Store" is displayed

And Kill the driver instance







