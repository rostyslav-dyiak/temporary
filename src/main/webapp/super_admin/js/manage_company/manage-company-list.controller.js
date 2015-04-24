(function () {
    'use strict';

    app.controller('ManageCompanyListController', ManageCompanyListController);
    ManageCompanyListController
        .$inject = [
        '$scope',
        'ManageCompanyListFactory'
    ];

    function ManageCompanyListController($scope, ManageCompanyListFactory) {
        activate();
        $scope.company = {};
        function activate() {
            ManageCompanyListFactory.get({},
                function (data) {
                    $scope.companies = data.companies;
                }, function (e) {
                    console.error(e);
                });
        }

        $scope.predicates = ['code', 'logo', 'name', 'type', 'created', 'status'];
        $scope.selectedPredicate = $scope.predicates[0];

        //  pagination
        $scope.itemsByPage = 10;
    }

})();
