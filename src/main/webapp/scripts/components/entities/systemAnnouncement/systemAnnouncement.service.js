'use strict';

angular.module('kbappApp')
    .factory('SystemAnnouncement', function ($resource) {
        return $resource('api/systemAnnouncements/:id', {}, {
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
