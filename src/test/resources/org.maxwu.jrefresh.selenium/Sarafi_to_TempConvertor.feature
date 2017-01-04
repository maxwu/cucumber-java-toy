Feature:  Browse the temperature converter page
  As a developer,
  In order to convert temperature
  I want to enter number and check result on google search page "temperature convertor".

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
    #And The converter table includes Fahrenheit and Celsius input cell


#####################################################