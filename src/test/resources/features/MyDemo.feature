Feature: Mobile App Checkout Flow

  Scenario: Complete checkout flow on the mobile app
    Given I open the app and select the first item
    When I add the item to the cart
    And I proceed to checkout
    And I log in with username "bob@example.com" and password "10203040"
    And I enter shipping details
    And I proceed to payment
    Then I should see the payment and review order screens