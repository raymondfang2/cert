var testApp = angular.module('testApp', []);

testApp.controller('testController' , function ($scope, $http) {
    //$scope.home = "This is the homepage";


        console.log("I've been pressed!");
        $http.get("time-entries/list")
            .then(function successCallback(response){
                $scope.response = response.data;
                console.log($scope.response);
            }, function errorCallback(response){
                             console.log("Unable to perform get request");
                         });


});