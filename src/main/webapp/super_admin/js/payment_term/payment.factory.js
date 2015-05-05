(function() {
    'use strict';

    app.factory('PaymentFactory', PaymentFactory);

    PaymentFactory
        .$inject = [
        '$resource'
    ];

    function PaymentFactory($resource) {
        return $resource('api/payment.json', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
