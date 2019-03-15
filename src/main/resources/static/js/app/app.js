var myApp = angular.module('certApp', ["chart.js","ngRoute"]);

//Each Tab will be a html with a controller
myApp.config(function($routeProvider) {
  $routeProvider
  .when("/", {
    templateUrl : "views/summary.html",
    controller : "summaryController"
  })
  .when("/detail", {
    templateUrl : "views/detail.html",
    controller : "detailController"
  })
  .when("/dynamicQuery", {
      templateUrl : "views/dynamicQuery.html",
      controller : "dynamicQueryController"
    })
  .when("/administration", {
    templateUrl : "views/administration.html"
  })
  .when("/dynamicTab1", {
      templateUrl : "views/dynamicTab1.html",
      controller : "dynamicTabController1"
    })
  .otherwise({
      templateUrl : "views/summary.html",
      controller : "summaryController"
   });
});


//For tab control
myApp.controller('appController' , function ($scope,$location, $http) {
       console.log("tabController start..");

       //Initial the active tab
       document.getElementById("summaryTab").className += " active";


       //method for get visible dTabs
       $scope.getVisibleDTabs = function () {
               console.log("getVisibeDTabs start..");
               var path = "cert/getDynamicTabIDs";

               $http.get(path)
                         .then(function successCallback(response){
                               $scope.response = response.data;
                               console.log($scope.response);
                               angular.forEach($scope.response, function(tabID) {
                                         $scope.hidedTabs[tabID] = false;
                               });
                         }, function errorCallback(response){
                                       console.log("Unable to perform get request");
                         });
       };

       //method for changing tab
       $scope.changeTab = function (evt, pageName) {
            console.log("changeTab start.."+pageName);
            var i, tabcontent, tablinks;

            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            //document.getElementById(cityName).style.display = "block";
            $location.path( "/"+pageName );
            console.log("before evt start..");
            evt.currentTarget.className += " active";
       };

       //get Visible DTabs and make is not hidden
        $scope.hidedTabs = [true, true, true, true, true]; //default 5 hidden dTabs
        $scope.getVisibleDTabs();
})