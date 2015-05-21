(function () {
    'use strict';

    app.controller('PersonalProfileController', DeliveryOrderController);

    DeliveryOrderController
        .$inject = [
        '$scope',
        '$modalInstance',
        'deliveryOrder'
    ];

    function DeliveryOrderController($scope, $modalInstance, deliveryOrder) {
        $scope.deliveryOrder = {};

        $scope.dismiss = dismiss;

        activate();

        function activate() {
            $scope.deliveryOrder = deliveryOrder;
        }

        function dismiss() {
            $modalInstance.dismiss('cancel');
        }
    }
})();
