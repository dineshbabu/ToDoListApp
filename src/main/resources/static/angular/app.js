'use strict';

var toDoListApp = angular.module('toDoListApp', ['ngAnimate', 'ngTouch', 'ui.grid','ui.grid.edit', 'ui.grid.pagination']);

toDoListApp.config(['$locationProvider', function($locationProvider){
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false
    });
}]);