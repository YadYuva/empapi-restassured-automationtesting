# empapi-restassured-automationtesting
Rest Assured Code Assessment

How to test -
Clone the GitHub branch given below 
Run the bellow command from project path with Maven installed in machine 
mvn clean install -Pintegration -Dspring.profiles.active=test

As an alternative if maven is not installed , 
Import the maven project from Eclipse(or any IDE)
Run from the runner class with JUnitTest to see the results in console and verify results in extent reports


Design Approach used
•	Build Cucumber Feature file with Steps 
o	/rest-client/src/test/java/com/rest/demo/features
•	Implement the Step definitions using Rest Assured
o	/rest-client/src/test/java/com/rest/demo/steps/EmployeeSteps.java
•	Implement the Mock using WireMock
o	/rest-client/src/test/java/com/rest/demo/steps/RestBaseforMock.java
•	Debug or Unit test using Cucumber – Junit Tests
o	/rest-client/src/test/java/com/rest/demo/CucumberRunnerTests.java

