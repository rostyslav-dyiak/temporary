(function () {
    'use strict';

    app.factory('TaxTypeFactory', TaxTypeFactory);

    TaxTypeFactory
        .$inject = [
        '$resource'
    ];

    function TaxTypeFactory($resource) {
        return $resource('/api/taxTypes/:id', {}, {
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
