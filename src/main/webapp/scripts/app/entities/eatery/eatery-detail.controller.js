'use strict';

angular.module('kbappApp')
    .controller('EateryDetailController', function ($scope, $stateParams, Eatery, Picture) {
        $scope.eatery = {};
        $scope.load = function (id) {
            Eatery.get({id: id}, function(result) {
              $scope.eatery = result;
            });
        };
        $scope.load($stateParams.id);
    });
