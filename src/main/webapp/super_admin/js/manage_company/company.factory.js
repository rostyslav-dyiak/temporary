(function() {
    'use strict';

    app.factory('CompanyFactory', CompanyFactory);

    CompanyFactory
        .$inject = [
        '$resource',
        'AuthServerProvider'
    ];

    function CompanyFactory($resource, AuthServerProvider) {
        return $resource('/api/companies', {}, {
            'query': {
                method: 'GET',
                isArray: true,
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            },
            get: {
                method: 'GET',
                isArray: true,
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            }
        });
    }

})();
