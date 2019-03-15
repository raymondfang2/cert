myApp.controller('dynamicQueryController' , function ($scope, $http) {
        console.log("dynamic start...!");
       //sample query
        $scope.sql="select candidate_email, exam_code, site_country, exam_date, score, grade from cert_exam_result limit 10 #sample only ";

        //2. Search Button Click method
        $scope.searchDynamic = function () {
               console.log("Dynamic Search button pressed!");

               var path = "cert/getDynamicQueryResult/"+ $scope.sql;

               $http.get(path)
                      .then(function successCallback(response){
                                $scope.response = response.data; //response JSON
                                //Need to handle zero record case - check size before next
                                $scope.thdata = response.data[0]; //the first record
                                console.log($scope.thdata);
                       }, function errorCallback(response){
                                console.log("Unable to perform get request");
                      });
        };

        //3. Publish Dynamic
        $scope.publihDynamic = function () {
               console.log("Dynamic Publish button pressed!");

               var path = "cert/addDynamicTab/0/MyReport/"+ $scope.sql;

               $http.get(path)
                           .then(function successCallback(response){
                           $scope.recordsInsert = response.data; //response JSON
                           console.log($scope.recordsInsert);
                           //show the Tab, in parent scope?
                           $scope.$parent.hidedTabs[0] = false;
                     }, function errorCallback(response){
                           console.log("Unable to perform get request");
                     });
        };

});