# Selenium Refreshment Project

*This project is a self-study project to refresh the skills and knowledge about Java, JUnit, Cucumber and Selenium topics*



## Tips
This section records the issues resolved during the construction/devops phase.

- Assert.assertEqual() will call obj.equals(), however, to check with "==", we shall use Asser.assertSame() instead.
- @Cucumber.Options() is replaced by @CucumberOptions annotation.
- Step def class must be public.

## TODO
```
- Change browser binary, profile, local/remote and other parameters to json.config.
- Test more elements.
- Parameterized test. 
- Add below scenario and implement the def.
#  Scenario: Convert Temperature "Celsius" "100"
#    Given Convert Page
#    When  I entered 100 Celsius
#    Then  I shall get 212 Fahrenheit
```