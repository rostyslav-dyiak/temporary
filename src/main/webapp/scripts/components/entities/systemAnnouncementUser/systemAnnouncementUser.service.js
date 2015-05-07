'use strict';

angular.module('kbappApp')
    .factory('SystemAnnouncementUser', function ($resource) {
        return $resource('api/systemAnnouncementUsers/:id', {}, {
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
