(function () {
    'use strict';

    app.controller('OutletsAddEditController', OutletsAddEditController);
    OutletsAddEditController
        .$inject = [
        '$scope',
        '$stateParams',
        'toaster',
        'OutletsFactory'
    ];

    function OutletsAddEditController($scope, $stateParams, toaster, OutletsFactory) {
        var outletId = $stateParams.id;
        var companyId = $stateParams.companyId;
        var master = {};

        $scope.outlet = {};

        $scope.save = save;

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
                    console.log('Saved ' + data.id);
                    toaster.pop('success', 'Success', 'Outlet created');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }
    }

})();
