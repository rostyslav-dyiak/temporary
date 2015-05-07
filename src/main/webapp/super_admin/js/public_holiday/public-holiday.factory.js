(function() {
    'use strict';

    app.factory('PublicHolidayFactory', PublicHolidayFactory);

    PublicHolidayFactory
        .$inject = [
        '$resource'
    ];

    function PublicHolidayFactory($resource) {
        return $resource('/api/holidays/:id', {}, {
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
