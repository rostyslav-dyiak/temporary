(function() {
    'use strict';

    app.factory('CategoryFactory', CategoryFactory);

    CategoryFactory
        .$inject = [
        '$resource'
    ];

    function CategoryFactory($resource) {
        return $resource('/api/categorys/:id', {}, {
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
