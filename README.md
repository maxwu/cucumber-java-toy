[![Build Status](https://travis-ci.org/maxwu/cucumber-java-toy.svg?branch=master)](https://travis-ci.org/maxwu/cucumber-java-toy)
[![codecov](https://codecov.io/gh/maxwu/cucumber-java-toy/branch/master/graph/badge.svg)](https://codecov.io/gh/maxwu/cucumber-java-toy)
[![CircleCI](https://circleci.com/gh/maxwu/cucumber-java-toy.svg?style=svg)](https://circleci.com/gh/maxwu/cucumber-java-toy)
[![Documentation Status](https://readthedocs.org/projects/cucumber-java-toy/badge/?version=latest)](http://cucumber-java-toy.readthedocs.io/en/latest/?badge=latest)
[![DUB](https://img.shields.io/dub/l/vibe-d.svg)]()

# Selenium Refreshment Project

*This project is a self-study project to refresh the skills and knowledge about Java, JUnit, Cucumber and Selenium topics*

## Scope
>* [X] Basic cucumber process
>* [X] Gherkin description and how to map to steps implementation
>* [X] JUnit integration and the annotations to run by Cucumber
>* [X] Multiple line parameters to step method by Cucumber Gherkin statement
>* [X] DataTable for Cucumber 
>* [X] Java 8 Lambda practice
>* [X] Failure hook for Cucumber
>* [X] Screenshot in Cucumber hook
>* [X] Parameterized test with Cucumber Scenario Outline
>* [X] Introduce WebDriverManager to support Travis could test
>* [X] Travis test configuration
>* [X] Refactor to Page Factory Pattern

## Tips
This section records the issues resolved during the construction/devops phase.

- @Before and @After hooks are invoked by each scenario on *every* test class defined in option *"glue".
    - However, most of the online documents and tutorials suggest to initialize Page Objects (for specific scenario) during @Before;
        - This brings redundant browser activities on those classes have nothing to do with other feature files;
        - The execution time extends dramatically.
        - If failure check hook is place with @After, for example, to take screenshot, useless screens are saved. 
          Only one of the class is taking necessary picture, other pictures are from browsers without any test steps.
    - If there are multiple scenarios in one single feature definition file, here is the tip:
        - Browsers instance could not restore if they quit (destroyed) already;
        - To mitigate and separate test scenarios, we shall initial browser at each scenario @Before.
    - Current Solution:
      This solution optimized test time from 3:09 to 2:18 min on dev MacPro env and from 4:24 to 3:08 on circleCI.
      However, there is no global hook supports with Cucumber-JVM for now. A possible balance is to:
        - Still creating new driver in @Before hook but don't go further to PageObject;
        - In the @Given step of Gherkin, initialize the PageObject, in Page Factory Pattern if you like;
        - Still destroy browsers by @After hook via quit() method.
        > The redundant browser instances will still be invoked but it is better and test will be much faster.
        
- CircleCI browser version issue resolved by updating chrome to >=v52
    ```
    google-chrome --version
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
    sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb stable main" >> /etc/apt/sources.list.d/google.list'
    sudo apt-get update
    sudo apt-get --only-upgrade install google-chrome-stable
    google-chrome --version
    ```
- Assert.assertEqual() will call obj.equals(), however, to check with "==", we shall use Asser.assertSame() instead.

- @Cucumber.Options() is replaced by @CucumberOptions annotation.

- Step def class must be public.

- Blinking scenarios, which passes sometimes but fails other time, is the smell of environmental(sharing), design, or, unexpected errors.

- Cucumber report is not thread-safe, we shall avoid this case multiple tests write to same report.

- How to run Cucumber with specified sequence?
    - In CLI, provide the first features in order and then the rest with whole folder.
    - With Maven, renaming feature name is needed.
    * Shall avoid this kind of dependency, which is out of Unit Test scope.

- Pay attention to @FindBy and @FindBys, @FindAll.
    - @FindBys is to narrow down the filter with multiple matching rules, AKA., to get the intersection.
    - @FindAll is to build the union set with at least one rule matched.
    - With @Find* annotation, each the factory or the constructor shall load initElements call.

- Not so important but keep in mind, the annotation @Before/@After and so on shall be imported from cucumber.api.java or cucumber.api.java.en (The Gherkin keys).
    - Make sure to import cucumber.api.java and cucumber.api.java.en;
    - Pay attention to any JUnit import and the application scope.

- Environmental Issue with network:
    - The WebDriverManager lib was configured to download latest nightly Firefox driver but the network will blink if we keep on downloading many times in a short while;
    - Set properties value to force cache will resolve it:
        ```
        wdm.forceCache=true
        ```

## TODO
```
- Change browser binary, profile, local/remote and other parameters to json.config.
- Consider Log4J to refactor the logging instructions.
```

## About
An open source toy project to refresh Cucumber-JVM+Selenium in MIT License.

maxwunj{AT}gmail{DOT}com 

http://www.maxwu.me