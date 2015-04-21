(function() {
    'use strict';

    app.controller('ManageCompanyEmployeesController', ManageCompanyEmployeesController);
    ManageCompanyEmployeesController
        .$inject = [
        '$scope'
    ];

    function ManageCompanyEmployeesController($scope) {
        $scope.rowCollection = [{
            id: 0,
            email: 'niles@gmail.com',
            name: 'Mr Niles',
            title: 'Manager',
            contactNumber: '90182681',
            access: 'Admin'
        },{
            id: 1,
            email: 'niles@gmail.com',
            name: 'Mr Sabrina',
            title: 'Manager',
            contactNumber: '90182681',
            access: 'Orchard Outlet'
        },{
            id: 2,
            email: 'niles@gmail.com',
            name: 'Mr Tony',
            title: 'Manager',
            contactNumber: '90182681',
            access: 'Bugis Outlet'
        },{
            id: 3,
            email: 'niles@gmail.com',
            name: 'Mr niles',
            title: 'Manager',
            contactNumber: '90182681',
            access: 'Bugis Outlet'
        },{
            id: 4,
            email: 'niles@gmail.com',
            name: 'Mr niles',
            title: 'Manager',
            contactNumber: '90182681',
            access: 'Orchard'
        },{
            id: 5,
            email: 'niles@gmail.com',
            name: 'Mr niles',
            title: 'Manager',
            contactNumber: '90182681',
            access: 'Orchard'
        },{
            id: 6,
            email: 'niles@gmail.com',
            name: 'Mr niles',
            title: 'Manager',
            contactNumber: '90182681',
            access: 'Orchard'
        }];

        //  pagination
        $scope.itemsByPage = 5;
    }

})();
/**
 * Created by oleh on 21.04.15.
 */
