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
        $scope.companyId = $stateParams.companyId;
        $scope.outlets = [];
        $scope.rowCollection = [];

        activate();
        function activate() {
            CompanyOutletsFactory.query({
                    companyId: $scope.companyId
                },
                function (data) {
                    $scope.outlets = data;
                    $scope.rowCollection = data;
                }, function (e) {
                    console.error(e);
                });
        }

    }
})();
