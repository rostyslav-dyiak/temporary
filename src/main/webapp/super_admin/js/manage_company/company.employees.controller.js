(function() {
    'use strict';

    app.controller('ManageCompanyEmployeesController', ManageCompanyEmployeesController);
    ManageCompanyEmployeesController
        .$inject = [
        '$scope',
        '$stateParams',
        'CompanyUsersFactory'
    ];

    function ManageCompanyEmployeesController($scope, $stateParams, CompanyUsersFactory) {
        var companyId = $stateParams.id;

        $scope.employees = [];

        activate();

        function activate() {
            CompanyUsersFactory.get({
                    companyId: companyId
                },
                function (data) {
                    $scope.employees = data;
                }, function (e) {
                    console.error(e);
                });
        }

        $scope.itemsByPage = 5;
    }
})();
