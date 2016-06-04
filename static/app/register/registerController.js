 
    var app= angular.module('DAppModule');
    app.controller('DRegister', ['$scope', '$http', function ($scope,$http) {
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
              headers : {'Content-Type': 'application/j'} 
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
 
