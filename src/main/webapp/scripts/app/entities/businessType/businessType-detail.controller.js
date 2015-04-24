'use strict';

angular.module('kbappApp')
    .controller('BusinessTypeDetailController', function ($scope, $stateParams, BusinessType) {
        $scope.businessType = {};
        $scope.load = function (id) {
            BusinessType.get({id: id}, function(result) {
              $scope.businessType = result;
            });
        };
        $scope.load($stateParams.id);
    });
