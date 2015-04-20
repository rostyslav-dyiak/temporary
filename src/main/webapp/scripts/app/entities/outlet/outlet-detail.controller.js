'use strict';

angular.module('kbappApp')
    .controller('OutletDetailController', function ($scope, $stateParams, Outlet, Eatery) {
        $scope.outlet = {};
        $scope.load = function (id) {
            Outlet.get({id: id}, function(result) {
              $scope.outlet = result;
            });
        };
        $scope.load($stateParams.id);
    });
