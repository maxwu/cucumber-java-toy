Feature:  With "Temperature Converter" Page, verify the Fahrenheit value turned from Celsius degree
  # With tags yet, the browser still needs to input key words before executing below feature.
  Background: There is a background assumption that Celsius number is in right column and Fahrenheit is in the left.
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
  @quit
  Scenario Outline: Convert Celsius to Fahrenheit and verify the values
  Given Google search page with predefined keywords
  When  Enter Celsius degree as "<celsius_degree>"
  Then  Check the value against "<fahrenheit_degree>"
  Examples:
  | celsius_degree | fahrenheit_degree |
  | 100            | 212               |
  | 0              | 32                |
  | -100           | -148              |