'use strict';

angular.module('kbappApp')
    .controller('SupplierDetailsPublicHolidaysDetailController', function ($scope, $stateParams, SupplierDetailsPublicHolidays, SupplierDetails) {
        $scope.supplierDetailsPublicHolidays = {};
        $scope.load = function (id) {
            SupplierDetailsPublicHolidays.get({id: id}, function(result) {
              $scope.supplierDetailsPublicHolidays = result;
            });
        };
        $scope.load($stateParams.id);
    });
