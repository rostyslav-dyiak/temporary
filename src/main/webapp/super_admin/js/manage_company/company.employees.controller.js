(function() {
    'use strict';

    app.controller('ManageCompanyEmployeesController', ManageCompanyEmployeesController);
    ManageCompanyEmployeesController
        .$inject = [
        '$scope',
        '$stateParams',
        'CompanyFactory'
    ];

    function ManageCompanyEmployeesController($scope, $stateParams, CompanyFactory) {
        var companyId = $stateParams.id;

        $scope.employees = {};

        activate();

        function activate() {
            CompanyFactory.get({
                    id: companyId
                },
                function (data) {
                    $scope.employees = data.users;
                }, function (e) {
                    console.error(e);
                });
        }

        $scope.itemsByPage = 5;
    }
})();
