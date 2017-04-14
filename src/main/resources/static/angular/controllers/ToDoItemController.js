'use strict';

toDoListApp.controller('ToDoItemController',
    function ($scope, ToDoItemService, uiGridConstants, $location, $window) {
    $scope.toDoItemData = {};
    $scope.createUpdateButtonLabel = 'Create';

    $scope.getJSonObjectFromUrlEncodedBase64String = function(base64Str) {
         return JSON.parse(atob(decodeURIComponent(base64Str)));
    };

    $scope.getQueryParameters = function() {
        var a = $window.location.search.substr(1).split('&');

        if (a == "") return {};
        var b = {};
        for (var i = 0; i < a.length; ++i)
        {
            var p=a[i].split('=', 2);
            if (p.length == 1)
                b[p[0]] = "";
            else
                b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
        }
        return b;
    };

    $scope.params = $scope.getQueryParameters();

    if($scope.params.q !== undefined) {
        $scope.createUpdateButtonLabel = 'Update';
        $scope.toDoItemData = $scope.getJSonObjectFromUrlEncodedBase64String($scope.params.q);
        $scope.toDoItemData.priority = ''+$scope.toDoItemData.priority+'';
        $scope.toDoItemData.status = ''+$scope.toDoItemData.status;+'';
    }

    console.log('$scope.toDoItemData : ' + JSON.stringify($scope.toDoItemData,null,4));

    if(angular.equals($scope.toDoItemData, {})){
        $scope.toDoItemData.name = '';
        $scope.toDoItemData.priority = '1';
        $scope.toDoItemData.status = '3';
    }
    $scope.redcolor = {color: "red"};



    $scope.priorities = [
                           {key :  '1', value : 'High'},
                           {key :  '2', value : 'Medium'},
                           {key :  '3', value : 'Low'}
                        ];
    $scope.statuses = [
                           {key :  '1', value : 'Complete'},
                           {key :  '2', value : 'In progress'},
                           {key :  '3', value : 'Pending'}
                      ];



    $scope.createUpdateToDoItem = function(){
        $scope.nameMissing = false;
        if($scope.toDoItemData.name == '') {
            $scope.nameMissing = true;
            return;
        }
        if($scope.params.q !== undefined){
            ToDoItemService.updateToDoItem($scope.toDoItemData)
        }else{
            ToDoItemService.createToDoItem($scope.toDoItemData)
        }

    }

});