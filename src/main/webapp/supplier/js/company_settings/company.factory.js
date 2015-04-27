(function() {
    'use strict';

    app.factory('CompanyFactory', CompanyFactory);

    CompanyFactory
        .$inject = [
        '$resource'
    ];

    function CompanyFactory($resource) {
        return $resource('api/companies', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
