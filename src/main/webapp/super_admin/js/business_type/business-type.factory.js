(function() {
    'use strict';

    app.factory('BusinessTypeFactory', BusinessTypeFactory);

    BusinessTypeFactory
        .$inject = [
        '$resource'
    ];

    function BusinessTypeFactory($resource) {
        return $resource('/api/businessTypes/:id', {}, {
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
