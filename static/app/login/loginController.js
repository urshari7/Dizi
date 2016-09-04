
var app = angular.module('DAppModule');

app.controller('DLoginController', ['$scope', '$http', '$location', function ($scope,$http,$location) {
    console.log('form submitted3');
    // create a blank object to handle form data.
    $scope.loginform = {};

	$scope.delay = 0;
	$scope.minDuration = 0;
	$scope.message = 'Please Wait...';
	$scope.backdrop = true;
	$scope.promise = null;
    
    // calling our submit function.
    $scope.submitForm = function() {

        console.log('form submitted');
        console.log('email:'+$scope.loginform.email)
        console.log('password:'+$scope.loginform.password);
        // Posting data to REST service
        var data = 'email='+$scope.loginform.email+'&password='+$scope.loginform.password;
        console.log('data:'+data);
        $scope.promise = $http({
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