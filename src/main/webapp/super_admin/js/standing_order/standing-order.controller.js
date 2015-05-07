(function () {
    'use strict';

    app.controller('StandingOrderController', StandingOrderController);

    StandingOrderController
        .$inject = [
        '$scope',
        'toaster'
    ];

    function StandingOrderController($scope, toaster) {
        $scope.numberOfHours = '';

        $scope.save = save;

        function save() {
            toaster.pop('success', 'Success', 'Standing order conversation has been updated');
        }
    }
})();
