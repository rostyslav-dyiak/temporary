(function () {
    'use strict';

    app.controller('ManageCompanyOutletsAddEditController', ManageCompanyOutletsAddEditController);
    ManageCompanyOutletsAddEditController
        .$inject = [
        '$scope',
        '$stateParams'
    ];

    function ManageCompanyOutletsAddEditController($scope, $stateParams) {
        var outletId = $stateParams.id;

        if (outletId == 0) {
            $scope.outlet = {
                id: 0,
                name: 'Mr Bean Bugins',
                created: new Date('10/03/2015'),
                status: 'Active'
            }
        } else if (outletId == 1) {
            $scope.outlet = {
                id: 0,
                name: 'Mr Bean Orchard',
                created: new Date('02/12/2013'),
                status: 'Active'
            }
        } else if (outletId == 2) {
            $scope.outlet = {
                id: 0,
                name: 'Mr Bean Novena',
                created: new Date('10/03/2015'),
                status: 'Active'
            }
        }
    }

})();
