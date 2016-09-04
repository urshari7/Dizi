var app = angular.module('DAppModule');


app.controller('DAddParticipantsController', ['$scope','$http', '$location',function($scope,$http,$location){
    $scope.participants = [{order: '',email:''}];
    $scope.participants[0].order=1;
    
    $scope.submitForm = function(){
        console.log(angular.toJson($scope.participants));
        $http({
          method  : 'POST',
          url     : 'http://localhost/dizisign-1.0-SNAPSHOT/rest/workflow/saveParticipants',
          data    : 'participants='+angular.toJson($scope.participants), //forms user object
          headers : {'Content-Type': 'application/x-www-form-urlencoded'} 
         })
          .success(function(response) {
                console.log('response:'+response.status);

                if (response.status=='success'){
                    console.log('workflow id:'+response.data);
                    $location.path('/setWorkflowFields');
                }else{
                    console.log('error :'+response.data);
                    console.log('mssg:'+response.message);
                }
          });
    };
    
    
    $scope.addParticipant = function() {
          var newItemNo = $scope.participants.length+1;
          $scope.participants.push({'order': newItemNo,'email':''});
          $scope.resetOrder();
    };
    
    $scope.resetOrder = function() {
          var index=0;
          for(index=0;index<$scope.participants.length;index++){
              $scope.participants[index].order=index+1;
          }
    };
    
    $scope.removeParticipant = function(index) {
          $scope.participants.splice(index, 1);
          $scope.resetOrder();        
    };
    
    $scope.showAddParticipant = function(index) {
        return index === $scope.participants.length-1;
    };
    
    $scope.showRemoveParticipant = function(index) {
        return index !== $scope.participants.length-1;
    };
    
    
}]);
