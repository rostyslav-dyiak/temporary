(function() {
    'use strict';

    app.factory('SupplierForCategoryFactory', SupplierForCategoryFactory);

    SupplierForCategoryFactory
        .$inject = [
        '$resource'
    ];

    function SupplierForCategoryFactory($resource) {
        return $resource('api/suppliers.json', {}, {
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
