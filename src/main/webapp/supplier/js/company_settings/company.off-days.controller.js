(function () {
    'use strict';

    app.controller('CompanyOffDaysController', CompanyOffDaysController);

    CompanyOffDaysController
        .$inject = [
        '$scope',
        'CompanyFactory'
    ];

    function CompanyOffDaysController($scope, CompanyFactory) {
        var range = [];
        for (var i = 2014; i < 2016; i++) {
            range.push(i);
        }
        $scope.years = range;
        $scope.selectedYear = '';

        $scope.holidays = {};

        activate();

        function activate() {

        }

        function addOffDays(offdays) {
            for (var i = 0; i < offdays.length; i++) {
                holidays[offdays[i].date] = offdays[i];
            }
        }

        function addOYearlyHolidays(yearly) {
            for (var i = 0; i < yearly.length; i++) {
                yearly[i].yearly = true;
                if(holidays[yearly[i].date].length > 0) {
                    holidays[yearly[i].date] = holidays[yearly[i].date].push(yearly[i]);
                } else {
                    holidays[yearly[i].date] = yearly[i];
                }
            }
        }
    }
})();
