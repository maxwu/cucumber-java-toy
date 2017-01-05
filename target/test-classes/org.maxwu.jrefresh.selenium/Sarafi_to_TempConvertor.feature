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
      | Celsius | 100 |
      | Celsius | 0   |
      | Celsius | -100|
    Then  Get results as the table:
      | Fahrenheit | 212 |
      | Fahrenheit | 32  |
      | Fahrenheit | -148|

  ## Scenario outline
#####################################################