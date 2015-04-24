(function() {
    'use strict';

    app.factory('TeamFactory', TeamFactory);

    TeamFactory
        .$inject = [
        '$resource'
    ];

    function TeamFactory($resource) {
        return $resource('api/team-members.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
