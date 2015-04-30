(function() {
    'use strict';

    app.factory('TeamFactory', TeamFactory);

    TeamFactory
        .$inject = [
        '$resource'
    ];

    function TeamFactory($resource) {
        return $resource('/api/companies/users/:id', {}, {
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
