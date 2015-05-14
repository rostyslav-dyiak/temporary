(function () {
    'use strict';

    app.factory('ProductUncategorizedFactory', ProductUncategorizedFactory);

    ProductUncategorizedFactory
        .$inject = [
        '$resource'
    ];

    function ProductUncategorizedFactory($resource) {
        return $resource('/api/products/uncategorized', {}, {
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
