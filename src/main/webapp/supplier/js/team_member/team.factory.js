(function() {
    'use strict';

    app.factory('TeamFactory', TeamFactory);

    TeamFactory
        .$inject = [
        '$resource'
    ];

    function TeamFactory($resource) {
        return $resource('/api/companies/:id/users', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
