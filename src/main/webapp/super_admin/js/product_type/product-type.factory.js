(function() {
    'use strict';

    app.factory('ProductTypeFactory', ProductTypeFactory);

    ProductTypeFactory
        .$inject = [
        '$resource'
    ];

    function ProductTypeFactory($resource) {
        return $resource('api/product-type.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
