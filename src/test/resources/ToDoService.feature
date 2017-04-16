Feature: ToDOList Controller

  Scenario Outline: Can create new ToDo item
    When I make an HTTP POST request to ToDOList controller with <uri> <itemName> <priority> <status> to create a new ToDoItem
    Then I expect to receive the the newly created ToDoItem with <itemName> <priority> <status>
    Examples:
      | uri            | itemName   | priority | status |
      | todoapp/todo/  | Buymilk    | 1        | 3      |
      | todoapp/todo/  | PostLetter | 2        | 2      |
      | todoapp/todo/  | Payfee     | 3        | 1      |

  Scenario Outline: Can update ToDo item
    When I make an HTTP PUT request to ToDOList controller with <uri> <priorityid> to update a ToDoItem
    Then I expect to the ToDoItem to be modified with <priorityid>
    Examples:
      | uri              | priorityid |
      | todoapp/todo/1/  | 3          |
      | todoapp/todo/2/  | 2          |
      | todoapp/todo/3/  | 1          |

  Scenario Outline: Can get ToDo item
    When I make an HTTP GET request to ToDOList controller with <uri> to get a ToDoItem
    Then I expect to the ToDoItem with id <id>
    Examples:
      | uri              | id |
      | todoapp/todo/3/  | 3  |
      | todoapp/todo/2/  | 2  |
      | todoapp/todo/1/  | 1  |

  Scenario Outline: Can filter ToDo items by status
    When I make an HTTP GET request to ToDOList controller with <uri> to get ToDoItems filtered by Status
    Then I expect to get only ToDoItems with status <status>
    Examples:
      | uri                         | status |
      | todoapp/todo/list/status/1  | 1      |

  Scenario Outline: Can filter ToDo items by Priority
    When I make an HTTP GET request to ToDOList controller with <uri> to get ToDoItems filtered by Priority
    Then I expect to get only ToDoItems with priority <priority>
    Examples:
      | uri                          | priority |
      | todoapp/todo/list/priority/3 | 3        |