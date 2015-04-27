(function() {
    'use strict';

    app.factory('CompanyOutletsFactory', CompanyOutletsFactory);

    CompanyOutletsFactory
        .$inject = [
        '$resource'
    ];

    function CompanyOutletsFactory($resource) {
        return $resource('/api/companies/:companyId/outlets', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
