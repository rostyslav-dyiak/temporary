(function() {
    'use strict';

    app.factory('OutletsCompanyFactory', OutletsCompanyFactory);

    OutletsCompanyFactory
        .$inject = [
        '$resource'
    ];

    function OutletsCompanyFactory($resource) {
        return $resource('/api/outlets/company', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
