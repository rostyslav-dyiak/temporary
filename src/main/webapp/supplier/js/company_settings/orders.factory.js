(function() {
    'use strict';

    app.factory('OrdersFactory', OrdersFactory);

    OrdersFactory
        .$inject = [
        '$resource'
    ];

    function OrdersFactory($resource) {
        return $resource('api/orders.json', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
