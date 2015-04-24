(function() {
    'use strict';

    app.factory('CompanyOutletsFactory', CompanyOutletsFactory);

    CompanyOutletsFactory
        .$inject = [
        '$resource'
    ];

    function CompanyOutletsFactory($resource) {
        return $resource('/api/companies/:id/outlets', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
