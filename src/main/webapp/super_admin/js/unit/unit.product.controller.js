(function () {
    'use strict';

    app.controller('UnitProductController', UnitProductController);
    UnitProductController
        .$inject = [
        '$scope',
        '$stateParams',
        'UnitProductFactory'
    ];

    function UnitProductController($scope, $stateParams, UnitProductFactory) {
        $scope.unitId = $stateParams.unitId;
        $scope.unitProduct = {};
        $scope.unitProducts = [];
        $scope.rowCollection = [];

        activate();

        function activate() {
            UnitProductFactory.query({
                    unitId: $scope.unitId
                },
                function (data) {
                    $scope.unitProducts = data;
                    $scope.rowCollection = data;
                }, function (e) {
                });
        }
    }

})();
