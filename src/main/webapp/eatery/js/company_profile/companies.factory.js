(function() {
    'use strict';

    app.factory('CompanyFactory', CompanyFactory);

    CompanyFactory
        .$inject = [
        '$resource'
    ];

    function CompanyFactory($resource) {
        return $resource('api/company.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
