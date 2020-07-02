package com.rest.demo.steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

/**
 * Hooks class to set the @Before annotation - method to run before each scenario begins 
 * to map the scenarios with relevant stubs created in RestBaseforMock class
 * @author Santhalakshmi 
 */
public class Hooks extends RestBaseforMock{
	
	@Before
	public void setUp(Scenario scenario) {

		String name = scenario.getName();
		switch (name) {
		case "login to system":
			mockLogin();
			break;
		case "create employee [Single]":
			mockEmployeeCreate();
			break;
		case "create employees [Multiple - List]":
			mockEmployeeCreate();
			break;
		case "User should not be able to Create with incorrect login credential":
			mockEmployeeCreate();
			break;		
		case "Get Employee by valid name":
			mockgetemployeeByName();
			break;
		case "Get Employee by Invalid employeename":
			mockgetemployeeByName();
			break;	
		case "Update employee using PUT":
		     mockEmployeeUpdate();
		     break;
		case "User should not be able to Update incorrect login credential":
		     mockEmployeeUpdate();
		     break;
		case "Delete employee using Delete with valid logged in user":
		     mockDeleteEmployee();
			 break;
		case "Logout from current session":
			mockLogout();
			break;
			
		default:
			break;
		}

		

	}

}