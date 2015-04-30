(function() {
    'use strict';

    app.factory('CustomersFactory', CustomersFactory);

    CustomersFactory
        .$inject = [
        '$resource'
    ];

    function CustomersFactory($resource) {
        return $resource('api/companies/:id/outlets', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
