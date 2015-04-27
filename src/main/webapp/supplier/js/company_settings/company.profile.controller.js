(function () {
    'use strict';

    app.controller('CompanyProfileController', CompanyProfileController);

    CompanyProfileController
        .$inject = [
        '$scope',
        'CompanyFactory'
    ];

    function CompanyProfileController($scope, CompanyFactory) {
        var master = {};
        $scope.company = {};

        $scope.removeImage = removeImage;
        $scope.save = save;
        $scope.cancel = cancel;

        activate();

        function activate() {
            CompanyFactory.get({
                    id: 1
                },
                function (data) {
                    master = angular.copy(data);
                    $scope.company = angular.copy(data);
                }, function (e) {
                    console.log(e);
                });
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
            console.log("Saved type with id: " + company.id);
        }

        function cancel() {
            $scope.company = angular.copy(master);
        }
    }
})();
