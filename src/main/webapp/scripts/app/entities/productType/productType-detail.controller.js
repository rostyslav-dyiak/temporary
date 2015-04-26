'use strict';

angular.module('kbappApp')
    .controller('ProductTypeDetailController', function ($scope, $stateParams, ProductType) {
        $scope.productType = {};
        $scope.load = function (id) {
            ProductType.get({id: id}, function(result) {
              $scope.productType = result;
            });
        };
        $scope.load($stateParams.id);
    });
