myApp.controller('dynamicTabController1' , function ($scope,$location, $http) {
        console.log("dynamic Tab1 start...!");
        console.log($location.path() ); // "/dynamicTab1"...

        //Get the tab_name, and searchResult
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
                                        console.log("Unable to perform get request");
                              });
        };

        //3. invoke the query
        $scope.searchDynamic();


});