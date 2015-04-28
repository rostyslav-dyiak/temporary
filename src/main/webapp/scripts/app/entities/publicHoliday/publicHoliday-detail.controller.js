'use strict';

angular.module('kbappApp')
    .controller('PublicHolidayDetailController', function ($scope, $stateParams, PublicHoliday) {
        $scope.publicHoliday = {};
        $scope.load = function (id) {
            PublicHoliday.get({id: id}, function(result) {
              $scope.publicHoliday = result;
            });
        };
        $scope.load($stateParams.id);
    });
