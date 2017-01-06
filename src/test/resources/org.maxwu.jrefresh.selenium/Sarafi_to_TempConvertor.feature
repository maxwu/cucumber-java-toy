Feature:  Browse the temperature converter page
  As a developer,
  In order to convert temperature
  I want to enter number and check result on google search page "temperature convertor".

  Background: Web Browser is successfully initialized (Here we launch profiled firefox as an example)

  @baseline
  Scenario: Open Temperature Convertor
    Given Google Entrance Page with:
    """
    A multiple lines sample parameter.
    This is the 2nd line.
    -end-line-
    """
    When  Search "temperature converter"
    Then  The page title is "temperature converter"
      And There is a "Temperature" option selected

  # With tags yet, the browser still needs to input key words before executing below feature.
  @calculation
  Scenario: Calculate temperature degrees
    Given "Fahrenheit" select is present
      And "Celsius" select is present
    When  Input data from the table:
      | 100 | 212|
      | 0   | 32 |
      | -100|-148|
    Then  Results are correct as on table

  ## Scenario outline sample test
  @calculation
  Scenario Outline: Convert Celsius to Fahrenheit and verify the values
    Given Google search page with predefined keywords
    When  Enter Celsius degree as "<celsius_degree>"
    Then  Check the value against "<fahrenheit_degree>"
  Examples:
    | celsius_degree | fahrenheit_degree |
    | 100            | 212               |
    | 0              | 32                |
    | -100           | -148              |
#####################################################