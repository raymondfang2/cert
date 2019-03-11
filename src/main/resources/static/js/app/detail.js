myApp.controller('detailController' , function ($scope, $http) {
        console.log("detailController start...!");

        //1. Data initialisation for drop down
        $scope.years = [];
        $scope.startYear = 2010;
        var current_year = new Date().getFullYear();
        $scope.endYear = current_year;
        for(var i = $scope.startYear; i <= current_year; i++){
            $scope.years.push(i);
        }

        //2. Search Button Click method
        $scope.searchCertExamRecords = function () {
              console.log("Search button pressed!");

              var path = "cert/getCertExamRecords/"+ $scope.startYear + "/" + $scope.endYear;

              $http.get(path)
                   .then(function successCallback(response){
                        $scope.response = response.data;
                        console.log($scope.response);
                   }, function errorCallback(response){
                        console.log("Unable to perform get request");
                   });
        };

        //3. downloadCsv method
        $scope.downloadCsv = function () {
            console.log("Download button pressed!");

            var path = "cert/downloadExamRecord/"+ $scope.startYear + "/" + $scope.endYear;
            window.location = path;

        }

        //4. Initial Function - just call the above method
        $scope.searchCertExamRecords();



});