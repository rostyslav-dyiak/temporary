(function() {
    'use strict';

    app.controller('ManageCompanyOutletsController', ManageCompanyOutletsController);
    ManageCompanyOutletsController
        .$inject = [
        '$scope',
        '$stateParams',
        'CompanyOutletsFactory'
    ];

    function ManageCompanyOutletsController($scope, $stateParams, CompanyOutletsFactory) {
        $scope.companyId = $stateParams.id;
        $scope.outlets = {};

        $scope.itemsByPage = 5;

        activate();
        function activate() {
            CompanyOutletsFactory.get({
                    id: $scope.companyId
                },
                function (data) {
                    $scope.outlets = data;
                }, function (e) {
                    console.error(e);
                });
        }

    }
})();
