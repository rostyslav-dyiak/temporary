(function () {
    'use strict';

    app.factory('SubCategoryFactory', SubCategoryFactory);

    SubCategoryFactory
        .$inject = [
        '$resource'
    ];

    function SubCategoryFactory($resource) {
        return $resource('/api/categorys/:id/subcategories', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'update': {
                method: 'PUT'
            },
            'get': {
                method: 'GET',
                isArray: false
            },
            'deleted': {
                method: 'DELETE'
            }
        });
    }
})();
