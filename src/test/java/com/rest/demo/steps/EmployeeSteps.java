package com.rest.demo.steps;

import com.rest.demo.SpringIntegrationTest;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import org.hamcrest.core.IsEqual;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Value;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

@Ignore
public class EmployeeSteps extends SpringIntegrationTest {

	@Value("${app.employees.uri}")
	protected String baseURI;

	protected static String fname;

	@Given("^service request timeout is set$")
	public void configureServiceTimeout() {
		config = RestAssured
				.config().httpClient(HttpClientConfig.httpClientConfig().
						setParam("http.connection.timeout", timeOut).
						setParam("http.socket.timeout", timeOut).
						setParam("http.connection-manager.timeout", timeOut)).
				sslConfig(new SSLConfig().allowAllHostnames());
	}
	
	@Given("^the username as (.*) and password as (.*)$")
	public void credentials(String username, String password) {
		request = given().log().all().baseUri(baseURI).config(config)
				.auth().basic(username, password);
		
	}

	@When("^the client get session$")
	public void getLogin() {
		response = request.when().get("employee/login");
	}
	
	@Then("^the service status code is (\\d+)$")
	public void service_code_check(int statusCode) {
		json = response.then().statusCode(statusCode);
		System.out.println(response.getStatusCode());
	}

	@Then("^the response has (.*)$")
	public void response_check(String expectedText) {
		response.then().assertThat().body("result",containsString(expectedText));
	}
	
	@Given("^the employee first name as (.*)$")
	public void add_first_name(String firstName) {
		request = request.log().all().param("first_name",firstName);
	}	

	@Given("^the employee last name as (.*)$")
	public void add_last_name(String lastName) {
		request = request.formParam("last_name",lastName);
	}

	@Given("^the employee email as (.*)$")
	public void add_email(String email) {
		request = request.formParam("email",email);
	}

	@When("^the client create single employee$")
	public void create_employee() {
		response = request.log().all().post("employee");
	}

	@Given("^the employee names as list")
	public void empArray(List<String> data) {
		request = request.formParam("Emplist", data);
	}
	
	@When("^the client create an employee records as list") 
	public void postEmpArray() {
		request.post("employee");
	}

	 @Given("^the employee first name (.*) to search")
	 public void searchbyfirstnamee(String data) {
		 request = given().log().all().baseUri(baseURI).param("first_name", data);
	 }
	
	@When("the client get employee by employee name")
	public void getemployeeByName() {		
		response = request.get("employee/employeename");
	}
	
	@Given("the employee name (.*) of the employee to be updated")
	public void empNameUpdate(String data){
		request = request.param("first_name", data);
	}
	
	@When("the client sends the Put call")
	public void putEmp(){
		response = request.put("employee/employeename");	
	}
	
	@Given("^the user logged in with (.*) and (.*)")
	public void authenticateuser(String userName ,String password) {		
		request = given().log().all().baseUri(baseURI).config(config).auth().preemptive().basic(userName, password);
	}
	
	@Given("the (.*) of the employee to be deleted")
	public void empNameDelete(String data){
		request = request.param("first_name", data);	
	}
	
	@When("the client sends the Delete call")
	public void deleteEmp(){
		response = request.delete("employee/employeename");	
	}
	
	
	@Given("^the user logs out from the system$")
	public void logoutsystem() {
		request = given().log().all().baseUri(baseURI).config(config);
	}
	
	@When("^the client get logout session$")
	public void getLogout() {
		response = request.get("employee/logout");
	}

	
}
