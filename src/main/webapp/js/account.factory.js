(function() {
    'use strict';

    app.factory('AccountFactory', AccountFactory);

    AccountFactory
        .$inject = [
        '$resource'
    ];

    function AccountFactory($resource) {
        return $resource('/api/account', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
