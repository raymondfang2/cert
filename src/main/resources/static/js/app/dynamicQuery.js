myApp.controller('dynamicQueryController' , function ($scope, $http) {
        console.log("dynamic start...!");
       //sample query
        $scope.sql="select candidate_email, exam_code, site_country, exam_date, score, grade from cert_exam_result limit 10 #sample only ";
        $scope.response = [{}]; //clear the cache data

        //2. Search Button Click method
        $scope.searchDynamic = function () {
               console.log("Dynamic Search button pressed!");



               $http({
                       method: 'GET',
                       url: 'cert/getDynamicQueryResult',
                       params: {dsql: $scope.sql}
                    }).then(function successCallback(response){
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

            Swal.fire({
              text: 'Please provide a Report Name',
              input: 'text',
              inputAttributes: {
                autocapitalize: 'off'
              },
              showCancelButton: true,
              confirmButtonText: 'Publish',
              showLoaderOnConfirm: true,
              preConfirm: (inputValue) => {
                    if (inputValue==="")
                    Swal.showValidationMessage(
                      'report name is empty!'
                    )

              },
              allowOutsideClick: false
            }).then((result) => {
              if (result.value) {
                //Call backend to publish using AngularJS
                                   $http({
                                          method: 'GET',
                                          url: 'cert/addDynamicTab',
                                          params: {tabName: result.value, dsql: $scope.sql}
                                        })
                                          .then(function successCallback(response){
                                          $scope.newTabID = response.data; //response JSON
                                          console.log($scope.newTabID);
                                          if ($scope.newTabID=="-1") {
                                             //alert("Reach the max no of dynamic report, you must delete old one before publih");
                                             Swal.fire({
                                               type: 'warning',
                                               //title: 'Oops...',
                                               text: 'Reach the max no of reports, please delete one of the old reports!',
                                             })
                                             //swal.fire("Reach Max Dynamic Report No!", "Please delete one of the old before publish", "warning");
                                          }
                                          else {
                                             $scope.$parent.hidedTabs[$scope.newTabID] = false;
                                             $scope.dTabNames[$scope.newTabID] = result.value;
                                          }
                                    }, function errorCallback(response){
                                          console.log("Unable to perform get request");
                                    });

              }
            })  //End of Swal


        }; //End of PublishDynamic

});