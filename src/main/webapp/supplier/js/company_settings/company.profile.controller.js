(function () {
    'use strict';

    app.controller('CompanyProfileController', CompanyProfileController);

    CompanyProfileController
        .$inject = [
        '$scope',
        '$http',
        'AuthServerProvider',
        'CompanyFactory'
    ];

    function CompanyProfileController($scope,$http, AuthServerProvider, CompanyFactory) {
        var master = {};
        $scope.company = {};

        $scope.removePhoto = removeImage;
        $scope.update = update;
        $scope.cancel = cancel;

        activate();

        function activate() {
            master = angular.copy(AuthServerProvider.currentUserCompany());
            $scope.company = angular.copy(AuthServerProvider.currentUserCompany());
        }

        function removeImage(image) {
            console.log("Saved type with id: " + image);
        }

        function update() {
            CompanyFactory.update($scope.company,
                function (data) {
                    $http.get("/api/account")
                        .success(function (data) {
                            AuthServerProvider.setUser(data);
                        })
                        .error(function (data) {
                            console.log(data);
                        });
                }, function (e) {
                    console.log(e);
                });
        }

        function cancel() {
            $scope.company = angular.copy(master);
        }
    }
})();
