(function() {
    'use strict';

    app.factory('CompanyFactory', CompanyFactory);

    CompanyFactory
        .$inject = [
        '$resource'
    ];

    function CompanyFactory($resource) {
        return $resource('/api/companies/:id', {}, {
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
