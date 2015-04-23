'use strict';

angular.module('kbappApp')
    .factory('SupplierDetails', function ($resource) {
        return $resource('api/supplierDetailss/:id', {}, {
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
