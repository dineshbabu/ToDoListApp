'use strict';

toDoListApp.controller('ToDoItemListController',
    function ($scope, ToDoItemService, uiGridConstants, $location, $window) {
    $scope.gridOptions = {
        paginationPageSizes: [15, 30, 45],
        paginationPageSize: 10
    };

    $scope.editToDoItem = function(toDoItem){
        $scope.goToCreateItem($scope.getUrlEncodedBase64StringFromJSonObject(toDoItem));
    }
    $scope.buttonWidth = "100px";
    var cellEditTemplate = '<input ng-click="grid.appScope.editToDoItem(row.entity)" type="button" value="Update" style="width:100px" class="embeddedControl cursorHand"/>';

    $scope.gridOptions.columnDefs = [
                                    { field: 'name', displayName: 'Name'},
                                    { field: 'priorityText', displayName: 'Priority'},
                                    { field: 'statusText', displayName: 'Status'},
                                    { name: 'Update', displayName: '',cellTemplate:cellEditTemplate,enableSorting: false}
                                    ];

    $scope.selectedStatus = '';
    $scope.selectedPriority = '';

    $scope.getAllToDoItems = function(){
        ToDoItemService.getAllToDoItems(function(data){
            $scope.gridOptions.data = data;
        });
    }
    $scope.filterByStatus = function(){
           $scope.selectedPriority = '';

           if($scope.selectedStatus == '4'){
               $scope.getAllToDoItems();
           }else{
                ToDoItemService.getToDoItemsFilteredByStatus($scope.selectedStatus, function(data){
                   $scope.gridOptions.data = data;
                });
           }
    }

    $scope.filterByPriority = function(){
           $scope.selectedStatus = '';
           if($scope.selectedPriority == '4'){
               $scope.getAllToDoItems();
           }else{
               ToDoItemService.getToDoItemsFilteredByPriority($scope.selectedPriority, function(data){
                       $scope.gridOptions.data = data;
               });
           }
    }

    if($scope.selectedStatus == '' && $scope.selectedPriority == ''){
        $scope.getAllToDoItems();
    }else if($scope.selectedPriority != ''){
        $scope.filterByPriority();
    }else {
        $scope.filterByStatus();
    }

    $scope.goToCreateItem = function(data){
        var location = '/todoapp/createItem.html';
        if(data !== undefined){
            location += '?q='+data
        }
        $window.location = location;
    }

    $scope.statuses = [
                       {key :  '1', value : 'Complete'},
                       {key :  '2', value : 'In progress'},
                       {key :  '3', value : 'Pending'},
                       {key :  '4', value : 'All'}
                      ];

    $scope.priorities = [
                           {key :  '1', value : 'High'},
                           {key :  '2', value : 'Medium'},
                           {key :  '3', value : 'Low'},
                           {key :  '4', value : 'All'}
                        ];

    $scope.getUrlEncodedBase64StringFromJSonObject = function(jsonObj) {
         return encodeURIComponent(btoa(JSON.stringify(jsonObj)));
    };

});