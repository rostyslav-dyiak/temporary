(function() {
    'use strict';

    app.controller('ManageCompanyEmployeesController', ManageCompanyEmployeesController);
    ManageCompanyEmployeesController
        .$inject = [
        '$scope',
        'EmployeesFactory'
    ];

    function ManageCompanyEmployeesController($scope, EmployeesFactory) {
        activate();

        function activate() {
            EmployeesFactory.get({},
                function (data) {
                    $scope.employees = data.employees;
                }, function (e) {
                    console.error(e);
                });
        }

        $scope.itemsByPage = 5;
    }
})();
