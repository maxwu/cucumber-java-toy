# Selenium Refreshment Project

*This project is a self-study project to refresh the skills and knowledges about Java, JUnit, Cucumber and Selenium topics*


## Tips

- Assert.assertEqual() will call obj.equals(), however, to check with "==", we shall use Asser.assertSame() instead.
- @Cucumber.Options() is replaced by @CucumberOptions annotation.
- Step def class must be public.

## TODO
```
      #And There is a converter table in page
      #And The converter table includes Fahrenheit and Celsius input cell

#  Scenario: Convert Temperature "Celsius" "100"
#    Given Convert Page
#    When  I entered 100 Celsius
#    Then  I shall get 212 Fahrenheit
```