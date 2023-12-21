Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
### Available products: "orange", "apple", "pasta", "cola"
### Prepare Positive and negative scenarios
  @pasta_positive
  Scenario: validating the positive scenario
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/pasta"
    Then he sees the results displayed for pasta
    Then we validate the response of pasta

  @pasta_negative-01
  Scenario: validating the positive scenario with invalid resource
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/pasta/01"
    Then response should contains Not Found of pasta

  @pasta_negative-02
  Scenario: validating the positive scenario with invalid resource using post method
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/pasta" using post method
    Then response should contains Method Not Allowed of pasta

  @positive
  Scenario: validating the positive scenario for all product
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/cola"
    Then he sees the results displayed for cola
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/car"
    Then he doesn not see the results