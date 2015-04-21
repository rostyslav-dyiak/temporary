(function() {
    'use strict';

    app.controller('ManageCompanyListController', ManageCompanyListController);
    ManageCompanyListController
        .$inject = [
            '$scope',
            '$filter'
        ];

    function ManageCompanyListController($scope, $filter) {
        $scope.rowCollection = [{
            id: 0,
            code: 'FX',
            logo: 'https://www.edubuntu.org/sites/default/files/images/ubuntu-logo-only_32.png',
            name: 'FoodService',
            type: 'Suplier',
            created: new Date('10/03/2015'),
            status: 'Active'
        }, {
            id: 1,
            code: 'MB',
            logo: 'https://www.edubuntu.org/sites/default/files/images/ubuntu-logo-only_32.png',
            name: 'Mr Bean',
            type: 'Eatery',
            created: new Date('02/12/2013'),
            status: 'Active'
        }, {
            id: 2,
            code: 'CJ',
            logo: 'https://www.edubuntu.org/sites/default/files/images/ubuntu-logo-only_32.png',
            name: 'Cafe J',
            type: 'Eatery',
            created: '-',
            status: 'Inactive'
        }];


        $scope.predicates = ['code', 'logo', 'name', 'type', 'created', 'status'];
        $scope.selectedPredicate = $scope.predicates[0];

        //  pagination
        $scope.itemsByPage = 10;
    }

})();
