(function() {
    'use strict';

    app.factory('DistrictFactory', DistrictFactory);

    DistrictFactory
        .$inject = [
        '$resource'
    ];

    function DistrictFactory($resource) {
        return $resource('/api/districts/:id', {}, {
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
