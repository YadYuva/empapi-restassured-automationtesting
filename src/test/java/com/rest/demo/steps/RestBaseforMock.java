package com.rest.demo.steps;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;


/**
 * This acts as a base class for WireMock Stubs and created as super class for this design framework
 * @author Santhalakshmi
 *
 */
public class RestBaseforMock {

	/**
	 * Get stub created for login employee feature 
	 */
	protected void mockLogin() {
		stubFor(get(urlEqualTo("/employee/login"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("login.json")
						.withBody("{\"result\":\"successful operation\"}")
						));
	}
	
	/**
	 * Get stub created for logout employee feature
	 */
	protected void mockLogout() {
		stubFor(get(urlEqualTo("/employee/logout"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("login.json")
						.withBody("{\"result\":\"successful operation\"}")
						));
	}
	
	/**
	 * Post stub created for create employee feature
	 */
	protected void mockEmployeeCreate() {
		stubFor(post(urlEqualTo("/employee"))
				.withBasicAuth("Lakshmi.K@cts.com", "pass123")
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("create-employee.json")
						.withBody("{\"result\":\"successful operation\"}")
						));
	}
	
	/**
	 * Get stub created for searching employee by name feature
	 */
	protected void mockgetemployeeByName() {
		stubFor(get(urlPathMatching("/employee/employeename"))
				.withQueryParam("first_name", matching("([A-Za-z]+)$"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("getempname.json")
						.withBody("{\"result\":\"successful operation\"}")
						));
	}

	/**
	 * Put stub created for update employee feature
	 */
	protected void mockEmployeeUpdate() {
		stubFor(put(urlPathMatching("/employee/employeename"))
				.withQueryParam("first_name", matching("([A-Za-z]+)$"))
				.withBasicAuth("Lakshmi.K@cts.com", "pass123")
				.willReturn(aResponse()
						.withStatus(202)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("update-employee.json")
						.withBody("{\"result\":\"successful operation\"}")
						));
	}
	
	/**
	 * Delete stub created for delete employee feature
	 */
	protected void mockDeleteEmployee(){
		stubFor(delete(urlPathMatching("/employee/employeename"))
				.withQueryParam("first_name", matching("([A-Za-z]+)$"))
				.withBasicAuth("Lakshmi.K@cts.com", "pass123")
				.willReturn(aResponse()
						.withStatus(203)
						.withHeader("Content-Type", "application/json")
						.withBody("{\"result\":\"successful operation\"}")
						));
	}

	/**
	 * Error stub created for any request with incorrect Url other than defined to show up 404 status code with message "failed operation" 
	 */
	protected void mockErrorstub(){
		stubFor(any(anyUrl())
				.atPriority(10)
				.willReturn(aResponse()
						.withStatus(404)
						.withBody("{\"result\":\"failed operation\"}")
						));
	}

}


