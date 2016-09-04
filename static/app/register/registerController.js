 
    var app= angular.module('DAppModule');
    app.controller('DRegister', ['$scope', '$http','$location', function ($scope,$http,$location) {
        // create a blank object to handle form data.
        $scope.signup = {};
        
        // calling our submit function.
        
        $scope.register = function(user) {

            console.log('form submitted');
            // Posting data to REST service
           console.log('data:'+JSON.stringify(user));
           alert(JSON.stringify(user));
            $http({
              method  : 'POST',
              url     : 'http://localhost:8080/dizisign-1.0-SNAPSHOT/rest/signup',
              data    : user, //forms user object
              headers : {'Content-Type': 'application/json'} 
             })
              .success(function(response) {
                    console.log('response:'+response.status);
                    //console.log('response:'+response.data.personalDetails.firstName);
                    //console.log('response:'+response.data.personalDetails.lastName);
                    if (response.status == 'failed') {
                      // Showing errors.
//                      $scope.errorName = response.data.errors.name;
//                      $scope.errorUserName = response.data.errors.username;
//                      $scope.errorEmail = response.data.errors.email;
                    } else {
//                      $scope.message = data.message;
                      $location.path('/registerSuccess');
                    }
              });
        };
        
    }]);
 
