'use strict';

angular.module('kbappApp')
    .controller('SystemAnnouncementDetailController', function ($scope, $stateParams, SystemAnnouncement) {
        $scope.systemAnnouncement = {};
        $scope.load = function (id) {
            SystemAnnouncement.get({id: id}, function(result) {
              $scope.systemAnnouncement = result;
            });
        };
        $scope.load($stateParams.id);
    });
