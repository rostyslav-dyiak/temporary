(function () {
    'use strict';

    app.controller('CompanyPublicHolidayController', CompanyPublicHolidayController);

    CompanyPublicHolidayController
        .$inject = [
        '$scope',
        'AuthServerProvider',
        'PublicHolidayFactory'
    ];

    function CompanyPublicHolidayController($scope, AuthServerProvider, PublicHolidayFactory) {
        $scope.company = {};
        $scope.holidays = {};
        $scope.itemsByPage = 10;

        $scope.toggle = toggle;

        activate();

        function activate() {
            $scope.holidays = AuthServerProvider.currentUserCompany();
        }

        function toggle() {

        }
    }
})();
