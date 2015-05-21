(function() {
    'use strict';

    app.factory('SupplierCategoryFactory', SupplierCategoryFactory);

    SupplierCategoryFactory
        .$inject = [
        '$resource'
    ];

    function SupplierCategoryFactory($resource) {
        return $resource('/eatery/api/supplier-categories.json', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'update': {
                method: 'PUT'
            }
        });
    }

})();
