(function () {
    'use strict';

    app.controller('CompanyProfileController', CompanyProfileController);

    CompanyProfileController
        .$inject = [
        '$scope',
        'AuthServerProvider',
        'CompanyFactory'
    ];

    function CompanyProfileController($scope, AuthServerProvider, CompanyFactory) {
        var master = {};
        $scope.company = {};

        $scope.removePhoto = removeImage;
        $scope.save = save;
        $scope.cancel = cancel;

        activate();

        function activate() {
            master = angular.copy(AuthServerProvider.currentUserCompany());
            $scope.company = angular.copy(AuthServerProvider.currentUserCompany());
        }

        function removeImage(image) {
            console.log("Saved type with id: " + company.id);
        }

        function save(company) {
            CompanyFactory.save(company,
                function (data) {
                    activate();
                }, function (e) {
                    console.log(e);
                });
        }

        function cancel() {
            $scope.company = angular.copy(master);
        }
    }
})();
