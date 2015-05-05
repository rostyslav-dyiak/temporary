(function () {
    'use strict';

    app.controller('ManageCompanyListController', ManageCompanyListController);
    ManageCompanyListController
        .$inject = [
        '$scope',
        'CompanyFactory'
    ];

    function ManageCompanyListController($scope, CompanyFactory) {
        $scope.companies = {};

        $scope.itemsByPage = 3;
        activate();

        function activate() {
            CompanyFactory.query({},
                function (data) {
                    $scope.companies = data;
                }, function (e) {
                    console.error(e);
                });
        }
    }

})();
