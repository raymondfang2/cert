myApp.controller('adminController', ['$scope', 'Upload', '$timeout', function ($scope, Upload, $timeout) {
    console.log("adminController start...!");
    $scope.progressHide=true;

    $scope.uploadFile = function(file) {
        $scope.progressHide=false;
        file.upload = Upload.upload({
          url: '/admin/uploadFile',
          data: {file: file},
        });

        file.upload.then(function (response) {
          $timeout(function () {
            file.result = response.data;
            $scope.progressHide=true;
          });
        }, function (response) {
          if (response.status > 0)
            $scope.errorMsg = response.status + ': ' + response.data;
        }, function (evt) {
          // Math.min is to fix IE which reports 200% sometimes
          file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
        });
    }
}]);

