var app = angular.module('DAppModule');

app.directive('fileModel', ['$parse', function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);
                  var modelSetter = model.assign;
                  
                  element.bind('change', function(){
                     scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            };
}]);


app.service('DFileUploadService', ['$http','$q', function ($http,$q) {

    
        
    this.uploadFileToUrl = function(file, uploadUrl){
           var fd = new FormData();
           fd.append('file', file);

           return 
           $http.post(uploadUrl, fd, {
              transformRequest: angular.identity,
              headers: {'Content-Type': undefined}
           })
           .then(function(data) {

                    return data;


                })
    }
    
}]);

app.controller('DFileUploadController', ['$http','$scope', 'DFileUploadService',  function($http,$scope, fileUpload){
    $scope.submitForm = function(){
       var uf = $scope.uploadForm.file;
       console.log('file');
       console.log(uf);

       var uploadUrl = "http://localhost/dizisign-1.0-SNAPSHOT/rest/workflow/upload";
       $scope.uploadedFile="In Progress";
        
       var fd = new FormData();
       fd.append('file', uf);

       $scope.promise =  $http.post(uploadUrl, fd, {
                            transformRequest: angular.identity,
                            headers: {'Content-Type': undefined}
                          }).success(function(response){
                                console.log(response);
                                if (response.status=='success'){
                                       $scope.uploadedFile="Success";
                                }else{
                                    $scope.uploadedFile="Failed";
                                }
                                
                          }).error(function(response,status){
                                console.log(response);
                                $scope.uploadedFile="Error";
                          });
           
                             
       //fileUpload.uploadFileToUrl(uf, uploadUrl);
        
        /*.then(function(data){
          $scope.uploadedFile = data;
       });*/

    };
}]);
