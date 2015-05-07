(function () {
    'use strict';

    app.controller('OrderTimingController', OrderTimingController);

    OrderTimingController
        .$inject = [
        '$scope'
    ];

    function OrderTimingController($scope) {
        $scope.deliveryDates = [{
            date: 0,
            name: "On the day of"
        },{
            date: 1,
            name: "1 day prior to"
        },{
            date: 2,
            name: "2 days prior to"
        },{
            date: 3,
            name: "3 days prior to"
        },{
            date: 4,
            name: "4 days prior to"
        },{
            date: 5,
            name: "5 days prior to"
        },{
            date: 6,
            name: "6 days prior to"
        },{
            date: 7,
            name: "7 days prior to"
        }];
        $scope.orderTiming = {};

        $scope.saveOrderTiming = saveOrderTiming;

        function saveOrderTiming() {
            console.log('updating on server..');
        }
    }
})();
