'use strict';

angular.module('kbappApp')
    .controller('ProductDetailController', function ($scope, $stateParams, Product, Category) {
        $scope.product = {};
        $scope.load = function (id) {
            Product.get({id: id}, function(result) {
              $scope.product = result;
            });
        };
        $scope.load($stateParams.id);
    });
