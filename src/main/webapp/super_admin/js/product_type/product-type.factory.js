(function() {
    'use strict';

    app.factory('ProductTypeFactory', ProductTypeFactory);

    ProductTypeFactory
        .$inject = [
        '$resource',
        'AuthServerProvider'
    ];

    function ProductTypeFactory($resource,AuthServerProvider) {
        return $resource('http://127.0.0.1:8080/api/productTypes/:id', {}, {
            'query': {
                method: 'GET',
                isArray: true,
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            },
            'save': {
                method: 'POST',
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            },update: {
                method: 'PUT',
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            },delete: {
                method: 'DELETE',
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            }
        });
    }

})();
