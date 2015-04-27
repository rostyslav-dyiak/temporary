(function() {
    'use strict';

    app.factory('ProductTypeFactory', ProductTypeFactory);

    ProductTypeFactory
        .$inject = [
        '$resource'
    ];

    function ProductTypeFactory($resource) {
        return $resource('/api/productTypes/:id', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },'update': {
                method: 'PUT'
            }
        });
    }

})();
