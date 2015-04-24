(function() {
    'use strict';

    app.factory('UserFactory', UserFactory);

    UserFactory
        .$inject = [
        '$resource'
    ];

    function UserFactory($resource) {
        return $resource('api/user.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
