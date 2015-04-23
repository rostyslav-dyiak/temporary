'use strict';

angular.module('kbappApp')
    .controller('EateryDetailsDetailController', function ($scope, $stateParams, EateryDetails) {
        $scope.eateryDetails = {};
        $scope.load = function (id) {
            EateryDetails.get({id: id}, function(result) {
              $scope.eateryDetails = result;
            });
        };
        $scope.load($stateParams.id);
    });
