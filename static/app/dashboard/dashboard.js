var app= angular.module('DDashboardModule', ['ngRoute']);

//the function expression that will 
//configure our router module
app.config(['$routeProvider',
     function ($routeProvider) {
            $routeProvider
            //when the user clicks signup
            .when('/createWorkflow', {
                templateUrl: 'app/workflow/create.html'
            })
            .when('/signDocument', {
                templateUrl: 'app/sign/load.html'
            })
            .when('/reports', {
                templateUrl: 'app/reports/search.html'
            });
    }
]);
    
    app.controller('DDashboard', ['$scope', '$http', function ($scope,$http) {
        // create a blank object to handle form data.
        $scope.loginform = {};
        
        // calling our submit function.
        $scope.submitForm = function() {

            console.log('form submitted');
            console.log('email:'+$scope.loginform.email)
            console.log('password:'+$scope.loginform.password);
            // Posting data to REST service
            var data = 'email='+$scope.loginform.email+'&password='+$scope.loginform.password;
            console.log('data:'+data);
            $http({
              method  : 'POST',
              url     : 'http://localhost/dizisign-1.0-SNAPSHOT/rest/login',
              data    : data, //forms user object
              headers : {'Content-Type': 'application/x-www-form-urlencoded'} 
             })
              .success(function(response) {
                    console.log('response:'+response.status);
                    console.log('response:'+response.data.personalDetails.firstName);
                    console.log('response:'+response.data.personalDetails.lastName);
                    if (data.errors) {
                      // Showing errors.
                      $scope.errorName = data.errors.name;
                      $scope.errorUserName = data.errors.username;
                      $scope.errorEmail = data.errors.email;
                    } else {
                      $scope.message = data.message;
                    }
              });
        };
    
    }]);
    