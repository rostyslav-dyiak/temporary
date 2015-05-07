(function () {
    'use strict';

    app.controller('PublicHolidayController', PublicHolidayController);
    PublicHolidayController
        .$inject = [
        '$scope',
        'PublicHolidayFactory'
    ];

    function PublicHolidayController($scope, PublicHolidayFactory) {
        $scope.announcements = [];
        $scope.rowCollection = [];
        $scope.years = [];
        $scope.selectedYear = '';

        activate();

        function activate() {
            PublicHolidayFactory.query({},
                function (data) {
                    $scope.announcements = data;
                    $scope.rowCollection = data;
                    generateYears();

                }, function (e) {
                    console.error(e);
                });
        }

        function generateYears() {
            for (var i = 0; i < $scope.announcements.length; i++) {
                var year = new Date($scope.announcements[i].date).getFullYear();
                if ($scope.years.indexOf(year) < 0) {
                    $scope.years.push(year);
                }
            }
        }
    }

})();
