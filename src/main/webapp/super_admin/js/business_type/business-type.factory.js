(function() {
    'use strict';

    app.factory('BusinessTypeFactory', BusinessTypeFactory);

    BusinessTypeFactory
        .$inject = [
        '$resource'
    ];

    function BusinessTypeFactory($resource) {
        return $resource('api/business-type.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
