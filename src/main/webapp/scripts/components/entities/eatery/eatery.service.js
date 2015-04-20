'use strict';

angular.module('kbappApp')
    .factory('Eatery', function ($resource) {
        return $resource('api/eaterys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
