(function() {
    'use strict';

    app.factory('BusinessTypeFactory', BusinessTypeFactory);

    BusinessTypeFactory
        .$inject = [
        '$resource',
        'AuthServerProvider'
    ];

    function BusinessTypeFactory($resource, AuthServerProvider) {
    	
        return $resource('http://127.0.0.1:8080/api/businessTypes', {}, {
        	'query': {
                method: 'GET',
                isArray: true,
                headers: {
                    'x-auth-token': AuthServerProvider.getToken().token
                }
            }
        });
    }

})();
