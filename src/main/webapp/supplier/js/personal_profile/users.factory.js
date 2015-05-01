(function() {
    'use strict';

    app.factory('UserFactory', UserFactory);

    UserFactory
        .$inject = [
        '$resource'
    ];

    function UserFactory($resource) {
        return $resource('/api/users/:id', {}, {
            'query': {
                method: 'GET',
                isArray: false
            },
            'update': {
                method : 'PUT'
            }
        });
    }

})();
