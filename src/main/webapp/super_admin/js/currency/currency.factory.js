(function() {
    'use strict';

    app.factory('CurrencyFactory', CurrencyFactory);

    CurrencyFactory
        .$inject = [
        '$resource'
    ];

    function CurrencyFactory($resource) {
        return $resource('api/currency.json', {}, {
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
