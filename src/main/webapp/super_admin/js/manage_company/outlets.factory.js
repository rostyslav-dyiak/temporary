(function () {
    'use strict';

    app.factory('OutletsFactory', OutletsFactory);

    OutletsFactory
        .$inject = [
        '$resource',
        'AuthServerProvider'
    ];

    function OutletsFactory($resource, AuthServerProvider) {
        return $resource('/api/outlets', {}, {
            'query': {
                method: 'GET',
                isArray: false,
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            },
            'save': {
                method: 'POST',
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            }
        });
    }

})();
