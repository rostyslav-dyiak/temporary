(function() {
    'use strict';

    app.factory('UserFactory', UserFactory);

    UserFactory
        .$inject = [
        '$resource'
    ];

    function UserFactory($resource) {
        return $resource('', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
