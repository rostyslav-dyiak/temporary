(function() {
    'use strict';

    app.factory('PaymentFactory', PaymentFactory);

    PaymentFactory
        .$inject = [
        '$resource'
    ];

    function PaymentFactory($resource) {
        return $resource('/api/paymentTerms/:id', {}, {
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
