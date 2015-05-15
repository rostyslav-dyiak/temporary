(function () {
    'use strict';

    app.factory('PricingProductTableFactory', PricingProductTableFactory);

    PricingProductTableFactory
        .$inject = [
        '$resource'
    ];

    function PricingProductTableFactory($resource) {
        return $resource('/api/products', {}, {
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
