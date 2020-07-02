package com.rest.demo.steps;

import com.rest.demo.SpringIntegrationTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.core.IsEqual;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

/**
 * @Ignore annotation to ignore all the methods of this class by Junittest
 * as the cucumber feature calls the methods in step definition file
*/
@Ignore
public class EmployeeSteps extends SpringIntegrationTest {

	/**
	 * baseURI set to  urlpath ,fetching value from properties file
	 */
	@Value("${app.employees.uri}")
	protected String baseURI;
	
	protected static RequestSpecification request;
	protected static Response response;
	protected static ValidatableResponse json;
	protected static RestAssuredConfig config;

	
	/**
	 * Mock server configured with timeOut properties
	 * TimeOut set to 10000 milliseconds in application properties file and value retrieved in Spring Integration class
	 * Configure the SSLConfig to allow all host names 
	 */
	@Given("^service request timeout is set$")
	public void configureServiceTimeout() {
		config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
									 .setParam("http.connection.timeout", timeOut)
						             .setParam("http.socket.timeout", timeOut)
						             .setParam("http.connection-manager.timeout", timeOut))
				.sslConfig(new SSLConfig().allowAllHostnames());
	}

	
	/**
	 * Request specification to login to baseuri with basic auth set to username and password 
	 * @param username-login username from feature file
	 * @param password-login password from feature file
	 */
	@Given("^the username as (.*) and password as (.*)$")
	public void credentials(String username, String password) {
		request = given().log().all().baseUri(baseURI).config(config).auth().basic(username, password);

	}

	/**
	 * To return a response for request with get method for login feature
	 */
	@When("^the client get session$")
	public void getLogin() {
		response = request.when().get("employee/login");
	}
	
	/**
	 * To verify the status code(parameterized from feature file) with a ValidatableResponse
	 * @param statusCode - status code of the response from feature file
	 */
	@Then("^the service status code is (\\d+)$")
	public void service_code_check(int statusCode) {
		json = response.then().statusCode(statusCode);
		
	}

	/**
	 * To verify the response contains the given message
	 * @param expectedText- message in response body
	 */
	@Then("^the response has (.*)$")
	public void response_check(String expectedText) {
		response.then().assertThat().body("result", containsString(expectedText));
	}

	/**
	 * Request with parameter firstname
	 * @param firstName - First name of employee from feature file
	 */
	@Given("^the employee first name as (.*)$")
	public void add_first_name(String firstName) {
		request = request.log().all().param("first_name", firstName);
	}
	
	
	/**
	 * Request with parameter lastname
	 * @param lastName-Last name of employee from feature file
	 */
	@Given("^the employee last name as (.*)$")
	public void add_last_name(String lastName) {
		request = request.formParam("last_name", lastName);
	}
	
	/**
	 * Request with parameter email
	 * @param email -email address of employee from feature file
	 */
	@Given("^the employee email as (.*)$")
	public void add_email(String email) {
		request = request.formParam("email", email);
	}

	/**
	 * To return a response for request with post method for create employee feature
	 */
	@When("^the client create single employee$")
	public void create_employee() {
		response = request.log().all().post("employee");
	}

	/**
	 * Request with parameter employee list
	 * @param data- Employee names as list from feature file
	 */
	@Given("^the employee names as list")
	public void empArray(List<String> data) {
		request = request.formParam("Emplist", data);
	}

	/**
	 * To return a response for request with post method for create employee as list 
	 */
	@When("^the client create an employee records as list")
	public void postEmpArray() {
		request.post("employee");
	}

	/**
	 * Request specification with baseURI and query parameter with employeename to search
	 * @param data - First name of employee to search from feature file
	 */
	@Given("^the employee first name (.*) to search")
	public void searchbyfirstnamee(String data) {
		request = given().log().all().baseUri(baseURI).param("first_name", data);
	}
	
	/**
	 * To return a response for request with get method for search employee by name feature
	 */
	@When("the client get employee by employee name")
	public void getemployeeByName() {
		response = request.get("employee/employeename");
	}

	/**
	 * Request with parameter first name
	 * @param data -First name of employee to be updated from feature file
	 */
	@Given("the employee name (.*) of the employee to be updated")
	public void empNameUpdate(String data) {
		request = request.param("first_name", data);
	}

	/**
	 * To return a response for request with put method for update employee feature
	 */
	@When("the client sends the Put call")
	public void putEmp() {
		response = request.put("employee/employeename");
	}

	/**
	 *Request specification with baseURI and basic authorization to restrict actions only for logged in user  
	 * @param userName - user login username
	 * @param password-user login password
	 */
	@Given("^the user logged in with (.*) and (.*)")
	public void authenticateuser(String userName, String password) {
		request = given().log().all().baseUri(baseURI).config(config).auth().preemptive().basic(userName, password);
	}

	/**
	 * Request with parameter first name
	 * @param data-First name of employee to be deleted
	 */
	@Given("the (.*) of the employee to be deleted")
	public void empNameDelete(String data) {
		request = request.param("first_name", data);
	}

	/**
	 * To return a response for request with delete method for delete employee feature
	 */
	@When("the client sends the Delete call")
	public void deleteEmp() {
		response = request.delete("employee/employeename");
	}

	/**
	 * Request specification with baseURI 
	 */
	@Given("^the user logs out from the system$")
	public void logoutsystem() {
		request = given().log().all().baseUri(baseURI).config(config);
	}

	/**
	 * To return a response for request with get method to logout employee 
	 */
	@When("^the client get logout session$")
	public void getLogout() {
		response = request.get("employee/logout");
	}

}
