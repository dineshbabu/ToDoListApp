'use strict';

toDoListApp.factory('ToDoItemService', function($http, $window) {

    var toDoItemToEdit = {};

    return {
        createToDoItem: function(toDoItem) {
            $http({ method:'POST',
                    url:'/todoapp/todo/',
                    headers: {
                      'Content-Type': 'application/json'
                    },
                    data: toDoItem
            }).success(function(data, status, headers, config){
                $window.location = '/todoapp/ListItems.html';
            }).error(function(data, status, headers, config){
                console.log('ToDOItem creation failed. status: ' +status + ' data: '+ data );
            });

        },
        updateToDoItem: function(toDoItem) {
                    $http({ method:'PUT',
                            url:'/todoapp/todo/'+toDoItem.id,
                            headers: {
                              'Content-Type': 'application/json'
                            },
                            data: toDoItem
                    }).success(function(data, status, headers, config){
                        $window.location = '/todoapp/ListItems.html';
                    }).error(function(data, status, headers, config){
                        console.log('ToDOItem creation failed. status: ' +status + ' data: '+ data );
                    });

        },
        getAllToDoItems: function( successHandler) {
             $http({method:'GET', url:'/todoapp/todo/list'}).
             success(function(data, status, headers, config){
                 successHandler(data);
             }).
             error(function(data, status, headers, config){
                 console.log('Listing ToDOItems failed. status: ' +status + ' data: '+ data );
             });
        },
        getToDoItemsFilteredByStatus: function( status, successHandler) {
             $http({method:'GET', url:'/todoapp/todo/list/status/'+status}).
             success(function(data, status, headers, config){
                 successHandler(data);
             }).
             error(function(data, status, headers, config){
                 console.log('Filtering ToDOItems by status failed. status: ' +status + ' data: '+ data );
             });
        },
        getToDoItemsFilteredByPriority: function( priority, successHandler) {
             $http({method:'GET', url:'/todoapp/todo/list/priority/'+priority}).
             success(function(data, status, headers, config){
                 successHandler(data);
             }).
             error(function(data, status, headers, config){
                 console.log('Filtering ToDOItems by priority failed. status: ' +status + ' data: '+ data );
             });
        }
    }
})
