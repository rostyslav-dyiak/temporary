(function () {
    'use strict';

    app.controller('EateryProfileController', EateryProfileController);

    EateryProfileController
        .$inject = [
        '$scope',
        '$modalInstance',
        'eatery'
    ];

    function EateryProfileController($scope, $modalInstance, eatery) {
        $scope.eatery = {};
        $scope.rowCollection = {};

        $scope.dismiss = dismiss;

        activate();

        function activate() {
            $scope.eatery = eatery;
            $scope.rowCollection = $scope.eatery.eatery.outlets;
        }

        function dismiss() {
            $modalInstance.dismiss('cancel');
        }
    }
})();
