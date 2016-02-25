(function () {
    'use strict';
 
    var app= angular.module('DApp');
    app.controller('DIndex', ['$scope', function ($scope) {
       $scope.message = 'Index Page';
    }]);
 
}());