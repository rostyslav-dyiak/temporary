(function () {
    'use strict';

    app.controller('CompanyOffDaysCreateController', CompanyOffDaysCreateController);

    CompanyOffDaysCreateController
        .$inject = [
        '$scope'
    ];

    function CompanyOffDaysCreateController($scope) {
        var range = [];
        for (var i = 2014; i < 2016; i++) {
            range.push(i);
        }
        $scope.years = range;
        $scope.save = save;
        $scope.cancel = cancel;

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

        function save() {

        }

        function cancel() {

        }
    }
})();
