(function () {
    'use strict';
    
    var app= angular.module('DAppModule', ['ngRoute','DDashboardModule','pdf','cgBusy','ngAnimate']);

    //the function expression that will 
    //configure our router module
    app.config(['$routeProvider','$locationProvider',
                 function ($routeProvider,$locationProvider) {
                    $routeProvider
                    //when the user clicks signup
                    .when('/register', {
                        templateUrl: 'app/register/register.html'
                    })
                    .when('/home', {
                        templateUrl: 'app/home/home.html'
                    })
                    .when('/dashboard', {
                        templateUrl: 'app/dashboard/dashboard.html'
                    })
                    .otherwise({redirectTo: '/home'});
                    
                    //$locationProvider.html5Mode(true);
                }
               ]);

}());