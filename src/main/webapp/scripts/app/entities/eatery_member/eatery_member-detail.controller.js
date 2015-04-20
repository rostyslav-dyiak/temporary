'use strict';

angular.module('kbappApp')
    .controller('Eatery_memberDetailController', function ($scope, $stateParams, Eatery_member) {
        $scope.eatery_member = {};
        $scope.load = function (id) {
            Eatery_member.get({id: id}, function(result) {
              $scope.eatery_member = result;
            });
        };
        $scope.load($stateParams.id);
    });
