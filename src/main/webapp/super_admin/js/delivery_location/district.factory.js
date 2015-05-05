(function() {
    'use strict';

    app.factory('DistrictFactory', DistrictFactory);

    DistrictFactory
        .$inject = [
        '$resource'
    ];

    function DistrictFactory($resource) {
        return $resource('api/districts.json', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
