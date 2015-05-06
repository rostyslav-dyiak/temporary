(function () {
    'use strict';

    app.controller('CompanyOffDaysCreateController', CompanyOffDaysCreateController);

    CompanyOffDaysCreateController
        .$inject = [
        '$scope',
        '$modalInstance',
        'dayOff',
        'OffDayFactory',
        'OrdersFactory'
    ];

    function CompanyOffDaysCreateController($scope, $modalInstance, dayOff, OffDayFactory, OrdersFactory) {
        $scope.dayOff = {};
        $scope.orders = [];
        $scope.rowCollection = [];

        $scope.refreshOrders = refreshOrders;
        $scope.save = save;
        $scope.cancel = cancel;


        activate();

        function activate() {
            if (dayOff) {
                $scope.dayOff = dayOff;
            } else {
                $scope.dayOff = {date: new Date()};
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
            //OffDayFactory.save($scope.dayOff,
            //    function () {
            //        $modalInstance.close();
            //    }, function (e) {
            //        console.error(e);
            //    });
            $modalInstance.close();
        }

        function cancel() {
            $modalInstance.dismiss('cancel');
        }
    }
})();
