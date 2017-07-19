package com.sanvi.test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.http.HttpStatus;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by jagapathiraju on 19/07/17.
 */
public class EmployeeRestTestSteps extends BaseSpringRestTest {

    @When("^the client calls /listAllEmployees")
    public void the_client_issues_GET_version() throws Throwable {
        executeGet("http://localhost:8080/listAllEmployees");
    }


    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int arg1) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(arg1));
    }

    @And("^the client response should be JSON: (.+)$")
    public void the_client_receives_listAllEmployees_values_should_be (String jsonFileName) throws Throwable {
       assertThat("JSON Body : " + latestResponse.getBody(), latestResponse.getBody(), is(getJSONData(jsonFileName)));
    }
}
