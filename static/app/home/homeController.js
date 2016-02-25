var mainApp = angular.module('mainApp', ['ngRoute']);

mainApp.config(function($routeProvider){
	$routeProvider
	// route for the about page
    .when('/workflow', {
        templateUrl : 'app/workflow/workflow.html',
        controller  : 'workflowController'
    })

    .when('/sign', {
        templateUrl : 'app/sign/sign.html',
        controller  : 'signController'
    })
    
    .when('/reports', {
        templateUrl : 'app/reports/reports.html',
        controller  : 'reportsController'
    })
    
    // route for the contact page
    .when('/broadcast', {
        templateUrl : 'app/broadcast/broadcast.html',
        controller  : 'broadcastController'
    });
});

// create the controller and inject Angular's $scope
mainApp.controller('homeController', function($scope) {
  // create a message to display in our view
  $scope.message = 'We are in HomePage!!';
});

mainApp.controller('workflowController', function($scope) {
	  // create a message to display in our view
	  $scope.message = 'We are in Workflow page!!';
	});


mainApp.controller('signController', function($scope) {
	  // create a message to display in our view
	  $scope.message = 'We are in Sign page!!';
	});


mainApp.controller('reportsController', function($scope) {
	  // create a message to display in our view
	  $scope.message = 'We are in reports page!!';
	});

mainApp.controller('broadcastController', function($scope) {
	  // create a message to display in our view
	  $scope.message = 'We are in broadcast page!!';
	});