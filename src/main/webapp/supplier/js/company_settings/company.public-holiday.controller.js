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
        $scope.holidays = [];
        $scope.rowCollection = [];

        $scope.toggle = toggle;

        activate();

        function activate() {
            $scope.company = AuthServerProvider.currentUserCompany();
            PublicHolidayFactory.query({},
                function (data) {
                    $scope.holidays = data;
                    $scope.rowCollection = data;
                },
                function (e) {
                    console.error(e);
                });
        }

        function toggle(holiday) {

        }
    }
})();
