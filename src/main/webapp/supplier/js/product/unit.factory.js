(function () {
    'use strict';

    app.factory('UnitFactory', UnitFactory);

    UnitFactory
        .$inject = [
        '$resource'
    ];

    function UnitFactory($resource) {
        return $resource('/api/units/:id', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'update': {
                method: 'PUT'
            },
            'get': {
                method: 'GET',
                isArray: false
            }
        });
    }
})();
