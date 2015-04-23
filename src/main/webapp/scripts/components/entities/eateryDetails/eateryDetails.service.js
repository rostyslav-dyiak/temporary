'use strict';

angular.module('kbappApp')
    .factory('EateryDetails', function ($resource) {
        return $resource('api/eateryDetailss/:id', {}, {
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
