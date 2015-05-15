(function() {
    'use strict';

    app.factory('SupplierProductFactory', SupplierProductFactory);

    SupplierProductFactory
        .$inject = [
        '$resource'
    ];

    function SupplierProductFactory($resource) {
        return $resource('api/supplier-products.json', {}, {
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
