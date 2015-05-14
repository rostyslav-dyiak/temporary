(function () {
    'use strict';

    app.factory('PricingGroupFactory', PricingGroupFactory);

    PricingGroupFactory
        .$inject = [
        '$resource'
    ];

    function PricingGroupFactory($resource) {
        return $resource('api/pricingGroups.json', {}, {
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
