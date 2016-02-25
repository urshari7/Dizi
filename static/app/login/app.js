var diziMainApp = angular.module('diziMainApp',[]);

diziMainApp.controller('diziLoginController',function($scope){
	$scope.onLogin=function(){
		console.log($scope.loginID);
	};
});