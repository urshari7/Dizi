(function () {
    'use strict';
    
    var app= angular.module('DApp', ['ngRoute']);

    //the function expression that will 
    //configure our router module
    app.config(['$routeProvider','$locationProvider',
                 function ($routeProvider,$locationProvider) {
                    $routeProvider
                    //when the user clicks signup
                    .when('/register', {
                        templateUrl: 'app/register/register.html',
                        controller: function ($scope) {
                            $scope.message = 'Hello!';
                        }
                    })
                    .when('/home', {
                        templateUrl: 'app/home/home.html',
                        controller: function ($scope) {
                            $scope.message = 'Hello!';
                        }
                    })
                    .when('/dashboard', {
                        templateUrl: 'app/dashboard/dashboard.html',
                        controller: function ($scope) {
                            $scope.message = 'Hello!';
                        }
                    })
                    .otherwise({redirectTo: '/home'});
                    
                    //$locationProvider.html5Mode(true);
                }
               ]);
    
    app.controller('DIndex', ['$scope', '$http', '$location', function ($scope,$http,$location) {
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
                      if (response.status=='success'){
                          $location.path('/dashboard');
                      }
                    }
              });
        };
    
    }]);
    
    /*
    //create the router module
    var myRouter = angular.module('DApp', ['ngRoute']),

        //the function expression that will 
        //configure our router module
        configFunction = function ($routeProvider) {
        $routeProvider

            //when the user clicks <a href="#hello">Hello</a>
            .when('/hello', {
                templateUrl: 'message.html',
                controller: function ($scope) {
                    $scope.message = 'Hello!';
                }
            })
            .otherwise({
              redirectTo: '/90',
              templateUrl: 'app/index/home.html',
              controller: 'DIndex'
            });
        };

    myRouter.controller('DIndex', ['$scope', function ($scope) {
       $scope.message = 'Index Page';
    }]);
    
    //call the config method of our router module
    myRouter.config(['$routeProvider', configFunction]);
    */
}());