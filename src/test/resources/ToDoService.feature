Feature: ToDOList Controller

  Scenario Outline: Can create new ToDo item
    When I make an HTTP POST request to ToDOList controller with <uri> to create a new ToDoItem
    Then I expect to receive the id of the newly created ToDoItem <id>
    Examples:
      | uri            | id |
      | todoapp/todo/  | 1  |
      | todoapp/todo/  | 2  |
      | todoapp/todo/  | 3  |
  
  Scenario Outline: Can update ToDo item
    When I make an HTTP PUT request to ToDOList controller with <uri> to update a ToDoItem
    Then I expect to the ToDoItem to be modified<id>
    Examples:
      | uri              | id |
      | todoapp/todo/3/  | 3  |
      | todoapp/todo/2/  | 2  |
      | todoapp/todo/1/  | 3  |