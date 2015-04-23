(function () {
    'use strict';

    app.controller('CompanyProfileCompanyController', CompanyProfileCompanyController);

    CompanyProfileCompanyController
        .$inject = [
        '$scope',
        'CompanyFactory',
        'ContactPersonFactory'
    ];

    function CompanyProfileCompanyController($scope, CompanyFactory, ContactPersonFactory) {
        $scope.company = {};
        $scope.contactPersons = {};

        $scope.save = save;
        $scope.removeLogo = removeLogo;
        $scope.removeOptionalImage = removeOptionalImage;

        activate();

        function activate() {
            CompanyFactory.get({
                    id: 1
                },
                function (data) {
                    $scope.company = data.company;
                }, function (e) {
                    console.error(e);
                });
            ContactPersonFactory.get({},
                function (data) {
                    $scope.contactPersons = data.contactPersons;
                }, function (e) {
                    console.error(e);
                });
        }


        function save(id) {
            console.log("Saved type with id: " + id);
        }

        function removeLogo() {
            console.log("Removed logo");
        }

        function removeOptionalImage() {
            console.log("Removed logo");
        }
    }
})();
