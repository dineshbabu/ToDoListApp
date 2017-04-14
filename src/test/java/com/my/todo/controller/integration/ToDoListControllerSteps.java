package com.my.todo.controller.integration;

import com.my.todo.model.ToDoItem;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.core.ParameterizedTypeReference;

public class ToDoListControllerSteps extends Steps{

    private String actualId;

    @When("^I make an HTTP POST request to ToDOList controller with (.*) to create a new ToDoItem$")
    public void iMakeAnHTTPPOSTRequestToDOListControllerUri(String uri) throws Throwable {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName("Buy Grocery");
        toDoItem.setPriority(1);
        toDoItem.setStatus(1);
        actualId = makeHttpPostRequest(uri, toDoItem, new ParameterizedTypeReference<String>(){});
    }

    @Then("^I expect to receive the id of the newly created ToDoItem (.*)$")
    public void iExpectToReceiveTheIdOfTheNewlyCratedToDoItemId(String expectedId) throws Throwable {
        assert(actualId.equals(expectedId));
    }

    @When("^I make an HTTP PUT request to ToDOList controller with <uri> to update a ToDoItem$")
    public void iMakeAnHTTPPUTRequestToToDOListControllerWithUriToUpdateAToDoItem() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I expect to the ToDoItem to be modified<id>$")
    public void iExpectToTheToDoItemToBeModifiedId() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
