(function () {
    'use strict';

    app.controller('OutletsAddEditController', OutletsAddEditController);
    OutletsAddEditController
        .$inject = [
        '$scope',
        '$stateParams',
        'OutletsFactory'
    ];

    function OutletsAddEditController($scope, $stateParams, OutletsFactory) {
        var outletId = $stateParams.id;
        var companyId = $stateParams.companyId;
        var master = {};

        $scope.outlet = {};

        $scope.save = save;
        $scope.cancel = cancel;

        activate();

        function activate() {
            if (outletId) {
                OutletsFactory.get({
                        id: outletId
                    },
                    function (data) {
                        master = angular.copy(data);
                        $scope.outlet = angular.copy(data);
                    }, function (e) {
                        console.error(e);
                    });
            }
        }

        function save(outlet) {
            outlet.company = {id: companyId};
            OutletsFactory.update(outlet,
                function (data) {
                    console.log('Saved ' + data.id)
                }, function (e) {
                    console.error(e);
                });
        }

        function cancel() {
            $scope.outlet = master;
        }
    }

})();
