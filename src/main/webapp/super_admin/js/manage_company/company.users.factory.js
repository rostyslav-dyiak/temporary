(function () {
    'use strict';

    app.factory('CompanyUsersFactory', CompanyUsersFactory);

    CompanyUsersFactory
        .$inject = [
        '$resource'
    ];

    function CompanyUsersFactory($resource) {
        return $resource('/api/companies/:companyId/users', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'get': {
                isArray: true
            },
            'update': {
                method: 'PUT'
            }
        });
    }
})();


