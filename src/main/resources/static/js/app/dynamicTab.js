myApp.controller('dynamicTabController' , function ($scope, $location, $http) {
        console.log("dynamic Tab1 start...!");
        console.log($location.path() ); // "/dynamicTab1"...
        var routePath = $location.path();
        console.log(routePath.substr(11));
        //Get the tab_name, and searchResult based on the above RoutePath
        $scope.searchDynamic = function () {
                       console.log("Dynamic Tab Search !");

                       var path = "cert/getDynamicTabByRoutePath"+ $location.path();

                       $http.get(path)
                              .then(function successCallback(response){
                                        console.log(response.data);
                                        $scope.response = response.data.searchResult; //response JSON
                                        $scope.tabName = response.data.tab_name;
                                        //Need to handle zero record case - check size before next
                                        $scope.thdata = response.data.searchResult[0]; //the first record

                               }, function errorCallback(response){
                                        console.log("Unable to perform get request!");
                              });
        };

        $scope.deleteDynamicTab = function () {
                       console.log("Dynamic Tab Deletion !");
                       var tabID = $location.path().substr(11);
                       var path = "admin/deleteDynamicTab/"+ tabID;

                       $http.delete(path)
                              .then(function successCallback(response){
                                   console.log(response.data);
                                   //Switch to default page and hide this page
                                   $location.path("/");
                                   $scope.$parent.hidedTabs[tabID] = true;
                              }, function errorCallback(response){
                                 console.log("Unable to perform get request");
                                 Swal.fire({
                                     type: 'warning',
                                     //title: 'Oops...',
                                     text: "Http Response Code: "+response.status,
                                 })
                              });


        };

        // invoke the query by default
        $scope.searchDynamic();


});