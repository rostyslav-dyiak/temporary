(function () {
    'use strict';

    app.factory('ProductCustomersFactory', ProductCustomersFactory);

    ProductCustomersFactory
        .$inject = [
        '$resource'
    ];

    function ProductCustomersFactory($resource) {
        return $resource('api/product-customers.json', {}, {
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
