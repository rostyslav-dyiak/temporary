'use strict';

angular.module('kbappApp')
    .factory('SupplierDetailsPublicHolidays', function ($resource) {
        return $resource('api/supplierDetailsPublicHolidayss/:id', {}, {
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
