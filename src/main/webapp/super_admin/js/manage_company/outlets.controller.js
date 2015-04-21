(function() {
    'use strict';

    app.controller('ManageCompanyOutletsController', ManageCompanyOutletsController);
    ManageCompanyOutletsController
        .$inject = [
        '$scope'
    ];

    function ManageCompanyOutletsController($scope) {
        $scope.rowCollection = [{
            id: 0,
            name: 'Mr Bean Bugins',
            created: new Date('10/03/2015'),
            status: 'Active'
        },{
            id: 1,
            name: 'Mr Bean Orchard',
            created: new Date('02/12/2013'),
            status: 'Active'
        },{
            id: 2,
            name: 'Mr Bean Novena',
            created: new Date('10/03/2015'),
            status: 'Active'
        }];

        //  pagination
        $scope.itemsByPage = 5;
    }

})();
/**
 * Created by oleh on 21.04.15.
 */
