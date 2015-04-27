(function () {
    'use strict';

    app.factory('OutletsFactory', OutletsFactory);

    OutletsFactory
        .$inject = [
        '$resource'
    ];

    function OutletsFactory($resource) {
        return $resource('/api/outlets/:id', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
