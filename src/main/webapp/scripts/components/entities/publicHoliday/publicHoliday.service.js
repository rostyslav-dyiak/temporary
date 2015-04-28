'use strict';

angular.module('kbappApp')
    .factory('PublicHoliday', function ($resource) {
        return $resource('api/publicHolidays/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.date = new Date(data.date);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
