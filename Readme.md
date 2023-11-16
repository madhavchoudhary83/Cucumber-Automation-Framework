Introduction:
-------------
- It is a Behavior Design Pattern Test Automation Framework
- It supports UI Testing on multiple browsers
- Supports parallel execution of independent tests or data driven tests
- Automatically attaches screenshots in reports on test failure
- Capability to attach test specific output file(s) in test reports


Framework Technical Information:
------------------------------
- Selenium 4.x
- Cucumber : To support gherkin based test creation
- TestNg : For test framework and assertions
- Log4J : For logging
- Extent Reports (Spark Report) : For test execution reports in HTML/PDF formats
- Maven : For build, dependency & plugin management


Pre-Requisites:
---------------
- Java JDK 17
- Apache Maven v 3.6
- Setup environment variables like M2_HOME, M2
- IntelliJ Idea or Eclispse or similar IDE


Framework Brief Overview :
--------------------------
- Resuable methods consuming webdriver instance and for page elements initialization. To be inherited across page objects and test steps
/src/main/java/base

- Create or Destroy Webdriver instance, Handle browser specific capabilities/options
/src/main/java/driver

- Maintain framework level constants/enums
/src/main/java/constants

- Create utitilies code (common code resuable across projects/framework)
/src/main/java/utils

- Create Page Objects and Page Actions under project specific folders
/src/test/java/pageobjects

- Maintain test steps related code under project specifc folder
/src/test/java/stepDefinitions

- Cucumber Runner & TestNg.xml : To control test execution based on features, tags
/src/test/java/runners/

*Cucumber runner - For manual execution via IDE
*Testng.xml - Drives Cucumber Runner. Consumed for triggering test for Manual/CLI/Jenkins executions

- Maintain feature files under project specifc folders
/src/test/resources/features

- Maintain configuration properties
/src/test/resources/properties
* Used to set browser for execution of tests

- Maintain test data for validations
/src/test/resources/testData

- To store the executions results
/ExtentReports

- To maintain test output data, executions logs, etc.
/target

- To store temp data required during test executions for processing
/test_output

- To maintain all project dependencies
/pom.xml


Browser Support :
----------------
- config.properties : Set browser name or pass browserName as an argument from CLI command


Test Reports :
--------------
/ExtentReports : Get executions reports in html format


Test Logs :
-----------
/target/logs/ : Maintain executions logs for last executions


Test Execution from IDE  (For manual trigger) :
-----------------------------------------------
- /src/test/runner/CucumberRunner : Configure cucumber options to set feature files , tags , etc.
- /src/test/runner/testng/testng.xml : Points to Cucumberrunner to trigger executions


Test Execution via CLI (Examples) :
-----------------------------------
- Open Terminal window on project root directory

- Execute tests based on tags :
For CP project  : mvn test "-DargLine=-Dcucumber.filter.tags='@cp'"
For DP2 project : mvn test "-DargLine=-Dcucumber.filter.tags='@dp2'"
For all project : mvn test "-DargLine=-Dcucumber.filter.tags='@regression'"

- Execute specific feature(s) for a project :
For CP project  : mvn test "-DargLine=-Dcucumber.features='src/test/resources/features/cp1_features/'"
For DP2 project  : mvn test "-DargLine=-Dcucumber.features='src/test/resources/features/dp2_features/'"
For CP project  : mvn test "-DargLine=-Dcucumber.features='src/test/resources/features'"

- Execute tests based on combination of specfic feature folder/file and tags :
 mvn test "-DargLine=-Dcucumber.features='src/test/resources/features/cp1_features/' -Dcucumber.filter.tags='@shop'"
 mvn test "-DargLine=-Dcucumber.features='src/test/resources/features/cp1_features/' -Dcucumber.filter.tags='@regression'"

- Execute tests based on feature and thread pool size (for parallel execution)
mvn test "-DargLine=-Dcucumber.features='src/test/resources/features' -Ddataproviderthreadcount='2'"

- Execute tests based on specific browser
 mvn test "-DargLine=-Dcucumber.features='src/test/resources/features' -Ddataproviderthreadcount='2' -DbrowserType='edge'"
 
 
 Instructions for attaching scenarios specific test output files:
 -----------------------------------------------------------------
 - For any scenario level output file(s) to be attached in reports >> store result in file of any format under test_output
 - Use tag <@attachoutput> on the undelying scenario in corresponding feature file
