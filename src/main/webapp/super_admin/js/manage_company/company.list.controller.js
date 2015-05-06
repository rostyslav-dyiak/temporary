(function () {
    'use strict';

    app.controller('ManageCompanyListController', ManageCompanyListController);
    ManageCompanyListController
        .$inject = [
        '$scope',
        '$filter',
        'CompanyFactory'
    ];

    function ManageCompanyListController($scope, $filter, CompanyFactory) {
        $scope.companies = {};
        $scope.rowCollection = {};
        $scope.predicates = ['code', 'name'];

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
