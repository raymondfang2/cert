var myApp = angular.module('certApp', []);

myApp.controller('certController' , function ($scope, $http) {
        console.log("I've been pressed3!");

        //Data for drop down
        $scope.years = [];
        $scope.startYear = 2010;
        var current_year = new Date().getFullYear();
        $scope.endYear = current_year;
        for(var i = $scope.startYear; i <= current_year; i++){
            $scope.years.push(i);
        }
        $scope.regions = ['ALL','Americas','EMEA','Japan','Pacific'];
        $scope.region = 'ALL';

        //Search Button Click
        $scope.searchCertSummary = function () {
              console.log("I've been pressed1!");
              var path = "cert/getCertSummaryByRegion/";
              if ($scope.region==='ALL') {
                path = "cert/getCertSummary/"+ $scope.startYear + "/" + $scope.endYear;
              }
              else {
                path = path + $scope.startYear + "/" + $scope.endYear+ "/" +$scope.region;
              }
              $http.get(path)
                   .then(function successCallback(response){
                        $scope.response = response.data;
                        console.log($scope.response);
                   }, function errorCallback(response){
                        console.log("Unable to perform get request");
                   });
        };

        //Initial Method - to be merged with Search method later
        $http.get("cert/getCertSummary/2012/2018")
            .then(function successCallback(response){
                $scope.response = response.data;
                console.log($scope.response);
            }, function errorCallback(response){
                console.log("Unable to perform get request");
            });



});