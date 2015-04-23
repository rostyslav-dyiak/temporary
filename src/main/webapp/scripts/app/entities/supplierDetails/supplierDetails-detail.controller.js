'use strict';

angular.module('kbappApp')
    .controller('SupplierDetailsDetailController', function ($scope, $stateParams, SupplierDetails) {
        $scope.supplierDetails = {};
        $scope.load = function (id) {
            SupplierDetails.get({id: id}, function(result) {
              $scope.supplierDetails = result;
            });
        };
        $scope.load($stateParams.id);
    });
