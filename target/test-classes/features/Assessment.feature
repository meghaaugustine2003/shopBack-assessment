Feature: Shopback web application testing

  Scenario: To verify user can successfully login with valid credentials
    Given User is on the login screen
    When User clicks on Login option
    And User clicks on Login button
    And User enters the email "qaassignment@shopback.com"
    And User tap on Next button
    And User enters the password "Shopback22"
    And User tap on Next button
    Then User Should redirect to Home screen

  @Login
  Scenario: To verify whether the sorting option in product details page is working
    Given User is on the home page after login
    When User hover on the option categories
    And User select the option Fashion
    And User select the sub category Jeans
    And User click on the product named "Woman Jeans High Waist Clothes Wide Leg Denim Clothing Blue Streetwear Vintage Quality Fashion Harajuku Straight Pants"
    Then User should be directed to the product details page
    When User clicks on filter option
    And User select the availability check box for In Stock Only and click on Done button
    Then Products should be filtered with In Stock only
    When User click on sort option and select price High to Low
    Then Products should be listed in the ascending order of price
    When User scroll down to the page and click on 2nd page button
    Then User should be directed to the second page