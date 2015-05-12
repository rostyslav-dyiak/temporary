(function () {
    'use strict';

    app.factory('UnitProductFactory', UnitProductFactory);

    UnitProductFactory
        .$inject = [
        '$resource'
    ];

    function UnitProductFactory($resource) {
        return $resource('/api/units/:unitId/products', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }
})();
