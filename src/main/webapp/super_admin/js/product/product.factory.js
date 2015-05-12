(function () {
    'use strict';

    app.factory('ProductFactory', ProductFactory);

    ProductFactory
        .$inject = [
        '$resource'
    ];

    function ProductFactory($resource) {
        return $resource('/api/products/:id', {}, {
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
