(function () {
    'use strict';

    app.controller('ManageCompanyListController', ManageCompanyListController);
    ManageCompanyListController
        .$inject = [
        '$scope',
        'CompanyFactory',
        '$localStorage'
    ];

    function ManageCompanyListController($scope, CompanyFactory, $localStorage) {
        $scope.companies = {};
        var test = $localStorage['token'].token;

        $scope.itemsByPage = 10;
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
