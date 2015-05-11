(function () {
    'use strict';

    app.controller('CompanyPublicHolidayController', CompanyPublicHolidayController);

    CompanyPublicHolidayController
        .$inject = [
        '$scope',
        'toaster',
        'AuthServerProvider',
        'PublicHolidayFactory'
    ];

    function CompanyPublicHolidayController($scope, toaster, AuthServerProvider, PublicHolidayFactory) {
        $scope.company = {};
        $scope.announcements = [];
        $scope.rowCollection = [];

        $scope.toggle = toggle;

        activate();

        function activate() {
            $scope.company = AuthServerProvider.currentUserCompany();
            PublicHolidayFactory.query({},
                function (data) {
                    $scope.announcements = data;
                    $scope.rowCollection = data;
                },
                function (e) {
                    toaster.pop('error', 'Error', 'Error in initialization');
                });
        }

        function toggle(holiday) {

        }
    }
})();
