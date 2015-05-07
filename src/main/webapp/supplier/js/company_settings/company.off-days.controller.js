(function () {
    'use strict';

    app.controller('CompanyOffDaysController', CompanyOffDaysController);

    CompanyOffDaysController
        .$inject = [
        '$scope',
        '$modal',
        'OffDayFactory'
    ];

    function CompanyOffDaysController($scope, $modal, OffDayFactory) {
        var range = [];
        for (var i = 2014; i < 2016; i++) {
            range.push(i);
        }
        $scope.years = range;
        $scope.selectedYear = '';
        $scope.announcements = [];

        $scope.openModal = openModal;

        activate();

        function activate() {
            OffDayFactory.get({},
                function (data) {
                    $scope.announcements = data.dates;
                    console.log($scope.announcements);
                },
                function (e) {
                    console.log(e);
                });
        }

        function openModal(yearly, dayOff) {
            $modal.open({
                templateUrl: 'myModalContent.html',
                controller: 'CompanyOffDaysCreateController',
                resolve: {
                    yearly: function () {
                        return yearly;
                    },
                    dayOff: function () {
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
    }
})();
