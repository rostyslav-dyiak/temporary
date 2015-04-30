(function() {

    'use strict';

    angular.module('app')
        .constant('USER_ROLES', {
            all: '*',
            eatery: 'ROLE_EATERY',
            eateryAdmin: 'ROLE_EATERY_ADMIN',
            supplier: 'ROLE_SUPPLIER',
            supplierAdmin: 'ROLE_SUPPLIER_ADMIN',
            superAdmin: 'ROLE_SUPER_ADMIN'
        });

})();
