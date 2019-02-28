var myApp = angular.module('certApp', []);

myApp.controller('certController' , function ($scope, $http) {
     console.log("I've been pressed3!");
        $http.get("cert/getCertSummary/2012/2018")
            .then(function successCallback(response){
                $scope.response = response.data;
                console.log($scope.response);
            }, function errorCallback(response){
                console.log("Unable to perform get request");
            });


});