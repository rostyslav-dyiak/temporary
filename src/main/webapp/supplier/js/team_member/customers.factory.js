(function() {
    'use strict';

    app.factory('CustomersFactory', CustomersFactory);

    CustomersFactory
        .$inject = [
        '$resource'
    ];

    function CustomersFactory($resource) {
        return $resource('api/customers.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
