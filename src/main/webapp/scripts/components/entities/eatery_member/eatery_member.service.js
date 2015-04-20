'use strict';

angular.module('kbappApp')
    .factory('Eatery_member', function ($resource) {
        return $resource('api/eatery_members/:id', {}, {
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
