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
  .when("/administration", {
    templateUrl : "views/administration.html"
  })
  .otherwise({
      templateUrl : "views/summary.html",
      controller : "summaryController"
   });
});


//For tab control
myApp.controller('tabController' , function ($scope,$location, $http) {
       console.log("tabController start..");
       //Initial the active tab
       document.getElementById("summaryTab").className += " active";

       //method for changing tab
       $scope.changeTab = function (evt, pageName) {
            console.log("changeTab start.."+pageName);
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            //document.getElementById(cityName).style.display = "block";
            $location.path( "/"+pageName );
            console.log("before evt start..");
            evt.currentTarget.className += " active";
       }
})