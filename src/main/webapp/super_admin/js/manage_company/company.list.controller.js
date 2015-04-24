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

        $scope.itemsByPage = 10;
        activate();

        function activate() {
            CompanyFactory.get({},
                function (data) {
                    $scope.companies = data;
                }, function (e) {
                    console.error(e);
                });
        }
    }

})();
