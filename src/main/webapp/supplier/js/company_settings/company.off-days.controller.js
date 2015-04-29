(function () {
    'use strict';

    app.controller('CompanyOffDaysController', CompanyOffDaysController);

    CompanyOffDaysController
        .$inject = [
        '$scope',
        '$modal',
        'AuthServerProvider'
    ];

    function CompanyOffDaysController($scope, $modal, AuthServerProvider) {
        var range = [];
        for (var i = 2014; i < 2016; i++) {
            range.push(i);
        }
        $scope.years = range;
        $scope.selectedYear = '';
        $scope.holidays = {};

        $scope.openModal = openModal;

        activate();

        function activate() {
            $scope.company = AuthServerProvider.currentUserCompany();
            PublicHolidayFactory.query({},
                function (data) {
                    $scope.holidays = data;
                },
                function (e) {
                    console.log(e);
                });
        }

        function openModal(dayOff) {
            $modal.open({
                templateUrl: 'myModalContent.html',
                controller: 'CompanyOffDaysCreateController',
                resolve: {
                    dayOff: function() {
                        return dayOff;
                    },
                    deps: ['$ocLazyLoad',
                        function ($ocLazyLoad) {
                            return $ocLazyLoad.load([
                                'js/company_settings/off-day.factory.js',
                                'js/company_settings/orders.factory.js',
                                'js/company_settings/company.off-days.create.controller.js'
                            ]);
                        }
                    ]
                }
            });
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
