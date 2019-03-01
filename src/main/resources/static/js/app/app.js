var myApp = angular.module('certApp', ["chart.js"]);

myApp.controller('certController' , function ($scope, $http) {
        console.log("I've been pressed3!");

        //1. Data initialisation for drop down
        $scope.years = [];
        $scope.startYear = 2010;
        var current_year = new Date().getFullYear();
        $scope.endYear = current_year;
        for(var i = $scope.startYear; i <= current_year; i++){
            $scope.years.push(i);
        }
        $scope.regions = ['ALL','Americas','EMEA','Japan','Pacific'];
        $scope.region = 'ALL';



        //2. Search Button Click method
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
                        $scope.getTotal();
                        $scope.getPieData("Overall");
                        console.log($scope.response);
                   }, function errorCallback(response){
                        console.log("Unable to perform get request");
                   });
        };

        //3. Calculation of total method
        $scope.getTotal = function () {
            //The last row in table --> overall information
                    //console.log("getTotal");
                    $scope.overallPass = 0;
                    $scope.overallFail = 0;
                    $scope.overallRefused = 0;

                    console.log($scope.response);
                    $scope.response.forEach((element) => {
                        $scope.overallPass += element.passCount;
                        $scope.overallFail += element.failCount;
                        $scope.overallRefused += element.refusedCount;
                    });


        }

        //4. Chart method
        $scope.getPieData = function (flag) {
                    $scope.labels = ["Pass", "Fail", "Refused"];
                    $scope.options = {
                            legend: { display: true },
                            responsive: true
                    }
                    if (flag==='Overall') {
                        $scope.data = [$scope.overallPass, $scope.overallFail, $scope.overallRefused];
                    }
                    else {
                        $scope.data = [100,100,100]
                        //TODO: onMouseOver or on click on the row to show different
                    }
        }

        //5. Initial Function - to be merged with Search method later
        var path4All = "cert/getCertSummary/"+ $scope.startYear + "/" + $scope.endYear;
        $http.get(path4All)
            .then(function successCallback(response){
                $scope.response = response.data;
                $scope.getTotal();
                $scope.getPieData("Overall");
                //console.log($scope.response);
            }, function errorCallback(response){
                console.log("Unable to perform get request");
            });

        //6. MouseOver

});