(function () {
    'use strict';

    app.controller('PublicHolidayController', PublicHolidayController);
    PublicHolidayController
        .$inject = [
        '$scope',
        'PublicHolidayFactory'
    ];

    function PublicHolidayController($scope, PublicHolidayFactory) {
        $scope.holidays = [];
        $scope.rowCollection = [];
        $scope.years = [];
        $scope.selectedYear = '';

        activate();

        function activate() {
            PublicHolidayFactory.query({},
                function (data) {
                    $scope.holidays = data;
                    $scope.rowCollection = data;
                    generateYears();

                }, function (e) {
                    console.error(e);
                });
        }

        function generateYears() {
            for (var i = 0; i < $scope.holidays.length; i++) {
                var year = new Date($scope.holidays[i].date).getFullYear();
                if ($scope.years.indexOf(year) < 0) {
                    $scope.years.push(year);
                }
            }
        }
    }

})();
