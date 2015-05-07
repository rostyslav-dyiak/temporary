'use strict';

angular.module('kbappApp')
    .controller('SystemAnnouncementUserDetailController', function ($scope, $stateParams, SystemAnnouncementUser) {
        $scope.systemAnnouncementUser = {};
        $scope.load = function (id) {
            SystemAnnouncementUser.get({id: id}, function(result) {
              $scope.systemAnnouncementUser = result;
            });
        };
        $scope.load($stateParams.id);
    });
