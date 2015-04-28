(function() {
    'use strict';

    app.factory('TeamFactory', TeamFactory);

    TeamFactory
        .$inject = [
        '$resource'
    ];

    function TeamFactory($resource) {
        return $resource('/api/companies/users', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
