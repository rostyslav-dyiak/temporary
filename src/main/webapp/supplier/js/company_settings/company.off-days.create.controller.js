(function () {
    'use strict';

    app.controller('CompanyOffDaysCreateController', CompanyOffDaysCreateController);

    CompanyOffDaysCreateController
        .$inject = [
        '$scope',
        '$modalInstance',
        'toaster',
        'yearly',
        'dayOff',
        'OffDayFactory',
        'OrdersFactory'
    ];

    function CompanyOffDaysCreateController($scope, $modalInstance, toaster, yearly, dayOff, OffDayFactory, OrdersFactory) {
        $scope.yearly = yearly ? yearly : false;
        $scope.dayOff = {};
        $scope.orders = [];
        $scope.rowCollection = [];
        $scope.dateMin = new Date();
        $scope.dateMax = new Date(new Date().getFullYear(), 11, 31);

        $scope.refreshOrders = refreshOrders;
        $scope.save = save;
        $scope.cancel = cancel;


        activate();

        function activate() {
            if (dayOff) {
                $scope.dayOff = dayOff;
            } else {
                $scope.dayOff = {
                    date: new Date(),
                    absenceType: 'DAYOFF'
                };
            }
            refreshOrders();
        }

        function refreshOrders() {
            OrdersFactory.query({
                    date: $scope.dayOff.date
                },
                function (data) {
                    $scope.orders = data;
                    $scope.rowCollection = data;
                },
                function (e) {
                    console.error(e);
                });
        }

        function save() {
            if (dayOff) {
                OffDayFactory.update($scope.dayOff,
                    function () {
                        toaster.pop('success', 'Success', 'Off-day has been updated');
                        $modalInstance.close();
                    }, function (e) {
                        console.error(e);
                        toaster.pop('error', 'Error', 'Please try again');
                    });
            } else {
                OffDayFactory.save($scope.dayOff,
                    function () {
                        toaster.pop('success', 'Success', 'Off-day has been created');
                        $modalInstance.close();
                    }, function (e) {
                        console.error(e);
                        toaster.pop('error', 'Error', 'Please try again');
                    });
            }
        }

        function cancel() {
            $modalInstance.dismiss('cancel');
        }
    }
})();
