var testApp = angular.module('testApp', []);

testApp.controller('testController' , function ($scope, $http) {
    //$scope.home = "This is the homepage";

    $scope.getRequest = function () {
        console.log("I've been pressed!");
        $http.get("time-entries/1")
            .then(function successCallback(response){
                $scope.response = response.data;
                console.log($scope.response);
            }, function errorCallback(response){
                console.log("Unable to perform get request");
            });
    };

});