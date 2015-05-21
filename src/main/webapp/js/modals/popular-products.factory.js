(function() {
    'use strict';

    app.factory('PopularProductsFactory', PopularProductsFactory);

    PopularProductsFactory
        .$inject = [
        '$resource'
    ];

    function PopularProductsFactory($resource) {
        return $resource('/eatery/api/supplier-products.json', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
