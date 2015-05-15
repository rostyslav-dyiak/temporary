(function () {
    'use strict';

    app.factory('PricingEateryFactory', PricingEateryFactory);

    PricingEateryFactory
        .$inject = [
        '$resource'
    ];

    function PricingEateryFactory($resource) {
        return $resource('api/pricing-eatery.json', {}, {
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
