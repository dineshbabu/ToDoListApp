package com.my.todo.controller.integration;

import com.my.todo.model.ToDoItem;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.core.ParameterizedTypeReference;

import java.net.MalformedURLException;
import java.util.List;

public class ToDoListControllerSteps extends Steps{

    private String createdToDoItemId;
    private ToDoItem toDoItemCreated;
    private ToDoItem updatedTodoItem;
    private ToDoItem getTodoItem;
    private List<ToDoItem> toDoItemsFilteredByStatus;
    private List<ToDoItem> toDoItemsFilteredByPriority;

    @When("^I make an HTTP POST request to ToDOList controller with (.*) (.*) (.*) (.*) to create a new ToDoItem$")
    public void iMakeAnHTTPPOSTRequestToDOListControllerUri(String uri, String name, int priority, int status) throws Throwable {
        ToDoItem toDoItemToCreate = new ToDoItem();
        toDoItemToCreate.setName(name);
        toDoItemToCreate.setPriority(priority);
        toDoItemToCreate.setStatus(status);

        createdToDoItemId = makeHttpPostRequest(uri, toDoItemToCreate, new ParameterizedTypeReference<String>(){});
    }

    @Then("^I expect to receive the the newly created ToDoItem with (.*) (.*) (.*)$")
    public void iExpectToReceiveNewlyCratedToDoItems(String expectedName, int expectedPriority, int expectedStatus) throws Throwable {
        toDoItemCreated = getTheCreatedToDoItem();
        assert(toDoItemCreated.getName().equals(expectedName));
        assert(toDoItemCreated.getPriority() == expectedPriority);
        assert(toDoItemCreated.getStatus() == expectedStatus);
    }

    private ToDoItem getTheCreatedToDoItem() throws MalformedURLException {
        return makeHttpGetRequest("/todoapp/todo/"+createdToDoItemId, new ParameterizedTypeReference<ToDoItem>() {
        });
    }

    @When("^I make an HTTP PUT request to ToDOList controller with (.*) (.*) to update a ToDoItem$")
    public void iMakeAnHTTPPUTRequestToToDOListControllerWithUriToUpdateAToDoItem(String uri, int priorityId) throws Throwable {
        ToDoItem todoItemToBeUpdated = getToDoItemToBeUpdated(uri);

        todoItemToBeUpdated.setPriority(priorityId);
        makeHttpPutRequest(uri, todoItemToBeUpdated, new ParameterizedTypeReference<String>() {
        });

        updatedTodoItem = makeHttpGetRequest("/todoapp/todo/"+todoItemToBeUpdated.getId(), new ParameterizedTypeReference<ToDoItem>() {
        });
    }

    private ToDoItem getToDoItemToBeUpdated(String uri) throws MalformedURLException {
        return makeHttpGetRequest(uri, new ParameterizedTypeReference<ToDoItem>() {
        });
    }

    @Then("^I expect to the ToDoItem to be modified with (.*)$")
    public void iExpectToTheToDoItemToBeModifiedId(int expectedPriority) throws Throwable {
        assert(updatedTodoItem.getPriority() == expectedPriority);

    }

    @When("^I make an HTTP GET request to ToDOList controller with (.*) to get a ToDoItem$")
    public void iMakeAnHTTPGETRequestToToDOListControllerWithUriToGetAToDoItem(String uri) throws Throwable {
        getTodoItem = makeHttpGetRequest(uri, new ParameterizedTypeReference<ToDoItem>() {
        });
    }

    @Then("^I expect to the ToDoItem with id (.*)$")
    public void iExpectToTheToDoItemWithIdId(long expectedId) throws Throwable {
        assert(expectedId == getTodoItem.getId());
    }

    @When("^I make an HTTP GET request to ToDOList controller with (.*) to get ToDoItems filtered by Status$")
    public void iMakeAnHTTPGETRequestToToDOListControllerWithUri(String uri) throws Throwable {
        toDoItemsFilteredByStatus = makeHttpGetRequest(uri, new ParameterizedTypeReference<List<ToDoItem>>() {
        });
    }

    @Then("^I expect to get only ToDoItems with status (.*)$")
    public void iExpectToGetOnlyToDoItemsWithStatusStatus(int expectedStatus) throws Throwable {
        assert(toDoItemsFilteredByStatus.stream()
                .allMatch(toDoItem -> toDoItem.getStatus() == expectedStatus));
    }

    @When("^I make an HTTP GET request to ToDOList controller with (.*) to get ToDoItems filtered by Priority$")
    public void iMakeAnHTTPGETRequestToToDOListControllerWithUriToGetToDoItemsFilteredByPriority(String uri) throws Throwable {
        toDoItemsFilteredByPriority = makeHttpGetRequest(uri, new ParameterizedTypeReference<List<ToDoItem>>() {
        });
    }

    @Then("^I expect to get only ToDoItems with priority (.*)$")
    public void iExpectToGetOnlyToDoItemsWithPriorityPrioriy(int expectedPriority) throws Throwable {
        assert(toDoItemsFilteredByPriority.stream()
                .allMatch(toDoItem -> toDoItem.getPriority() == expectedPriority));
    }
}
