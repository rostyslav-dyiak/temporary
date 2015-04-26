(function() {
    'use strict';

    app.factory('ProductTypeFactory', ProductTypeFactory);

    ProductTypeFactory
        .$inject = [
        '$resource',
        'AuthServerProvider'
    ];

    function ProductTypeFactory($resource,AuthServerProvider) {
        return $resource('http://127.0.0.1:8080/api/productTypes', {}, {
            'query': {
                method: 'GET',
                isArray: true,
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            }
        });
    }

})();
