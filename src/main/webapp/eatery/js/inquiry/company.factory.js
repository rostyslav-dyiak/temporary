(function() {
    'use strict';

    app.factory('CompanyFactory', CompanyFactory);

    CompanyFactory
        .$inject = [
        '$resource'
    ];

    function CompanyFactory($resource) {
        return $resource('/api/companies/:type/:id', {}, {
            'get': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
