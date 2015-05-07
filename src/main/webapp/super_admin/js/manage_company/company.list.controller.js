(function () {
    'use strict';

    app.controller('ManageCompanyListController', ManageCompanyListController);
    ManageCompanyListController
        .$inject = [
        '$scope',
        'CompanyFactory'
    ];

    function ManageCompanyListController($scope, CompanyFactory) {
        $scope.companies = [];
        $scope.rowCollection = [];

        activate();

        function activate() {
            CompanyFactory.query({},
                function (data) {
                    $scope.companies = data;
                    $scope.rowCollection = data;

                }, function (e) {
                    console.error(e);
                });
        }
    }

})();
