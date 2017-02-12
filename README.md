# Cucumber-JVM + JUnit + Selenium Web Driver Toy Project

*This project is a fast toy project to refresh the feeling and fun on Java, JUnit, Selenium and expand Cucumber-JVM topic*

Master:
[![Build Status](https://travis-ci.org/maxwu/cucumber-java-toy.svg?branch=master)](https://travis-ci.org/maxwu/cucumber-java-toy)
[![codecov](https://codecov.io/gh/maxwu/cucumber-java-toy/branch/master/graph/badge.svg)](https://codecov.io/gh/maxwu/cucumber-java-toy)
[![CircleCI](https://circleci.com/gh/maxwu/circleci_stat/tree/master.svg?style=svg)](https://circleci.com/gh/maxwu/circleci_stat/tree/master)

Dev: 
[![Build Status](https://travis-ci.org/maxwu/cucumber-java-toy.svg?branch=dev)](https://travis-ci.org/maxwu/cucumber-java-toy)
[![codecov](https://codecov.io/gh/maxwu/cucumber-java-toy/branch/dev/graph/badge.svg)](https://codecov.io/gh/maxwu/cucumber-java-toy)
[![CircleCI](https://circleci.com/gh/maxwu/circleci_stat/tree/dev.svg?style=svg)](https://circleci.com/gh/maxwu/circleci_stat/tree/dev)

License: [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


## Summary of Tips, Issues and Solutions:
 
 - [Tips on Selenium PageObject Design (with a common template sample)](http://maxwu.me/2017/01/21/Tips-on-Selenium-PageObject-Design/)
 - [Cucumber Java Toy v1.2 Release Notes and a brief technical summary](https://maxwu.github.io/2017/01/13/Cucumber-JVM-Selenium-JUnit-Toy-Project-baselined-to-v1-2/)
 - [JUnit tips (in progress)](http://maxwu.me/2017/01/23/JUnit-Tips/)
 - [Practice tips between JUnit and Python Unittest](http://maxwu.me/2017/01/23/Python-unittest-tips-from-JUnit-view/)
 - [Trusting hosts with HttpClient in WebAPI test](http://maxwu.me/2017/02/03/Trust-all-hosts-in-WebAPI-test-with-OkHttp-and-Java-SSL/)
 
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
>* [X] Add Travis test configuration
>* [X] Refactor to Page Factory Pattern
>* [X] Various tests and code reading to clarify obj lifecycle of Cucumber-JVM
>* [X] Add circleCI online CI and deployment test
>* [X] Customized Annotation and the test
>* [X] Test the Interceptor Implementation with Cglib Proxy
>* [X] Add RESTful test cases for freegeoip and ipify public WebAPIs

## Tips
This section records the issues resolved during the construction/devops phase.

- @Before and @After hooks are invoked by each scenario on *every* test class thru internal hookAdd() when scanning is performed on "glue" implementations.
    - However, most of the online documents and tutorials suggest to initialize Page Objects (for specific scenario) during @Before;
        - This brings redundant browser activities on those classes have nothing to do with other feature files;
        - The execution time extends dramatically when typically (in those tutorials) not all Setup(s) are necessary for all scenarios.
        - If failure check hook is place with @After, for example, to take screenshot, useless screens are saved. 
          Only one of the class is taking necessary picture, other pictures are from browsers without any test steps.
          Because the scenario status is shared between all registered hooks.
    - If there are multiple scenarios in one single feature definition file, here are the tips:
        - Browsers instance could not restore if they quit (destroyed) already;
        - To mitigate and separate test scenarios, we shall initial browser at each scenario @Before;
        - However, for default Object Factory (as most new comers to Cucumber), not suggested to add PageObject Init into @Before Hook;
    - Current Solution:
      This solution optimized test time from 3:09 to 2:18 min on dev MacPro env and from 4:24 to 3:08 on circleCI.
      However, there is no global hook supports with Cucumber-JVM for now. A possible balance is to:
        - Still creating new driver in @Before hook but don't go further to PageObject;
        - In the @Given step of Gherkin, initialize the PageObject, in Page Factory Pattern if you like;
        - Still destroy browsers by @After hook via quit() method.
        > [TODO] To resolve it better, try to customize the Object Factory instead of default one in close future.
    - Readers shall pay attention to understand @Before, @After and the steps in Background section comparing to Junit.
      The concept is to invoke objects by reflection and run all hooks regardless to which scenarios is currently executing at present.
      Therefore, the sequence is:
        - Load Glue, build map for hooks;
        - Run scenarios:
            - Run @Before hooks (with reflected instances) according to the order (order value default to 10,000);
            - Run method which matching the Steps in scenario;
            - Run @After hooks;
        - Format reports.
        
- CircleCI browser version issue resolved by updating chrome to >=v52
    ```
    google-chrome --version
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
    sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb stable main" >> /etc/apt/sources.list.d/google.list'
    sudo apt-get update
    sudo apt-get --only-upgrade install google-chrome-stable
    google-chrome --version
    ```
    
- Use online tools to validate YAML files for TravisCI and CodeCov before commit to test:
    - CodeCov
    
    ```
        cat codecov.yml | curl --data-binary @- https://codecov.io/validate
    ```
    
    - Travis-CI
    
    ```
    http://lint.travis-ci.org/
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
- The dev and test env:
    - Dev env on MBP with IntelliJ IDEA CE, Maven, JUnit, Selenium WebDriver, Firefox (stable and nightly), Chrome;
    - Test with Travis-CI and CircleCI, Ubuntu 14, Chrome.
    
## TODO

```
- Change browser binary, profile, local/remote and other parameters to config YAML.
- With primitive analysis, improve the code coverage to above 80%.
- Consider Log4J to refactor the logging instructions.
- Migrating to Docker based Test and prepare for a further project releasing App in Docker.
    - Dockerfile added, however, an error under debug.
```

## About

An open source toy project to refresh Cucumber-JVM + Selenium in MIT License.

maxwunj{AT}gmail{DOT}com 

http://maxwu.me