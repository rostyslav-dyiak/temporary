'use strict';

angular.module('kbappApp')
    .controller('PictureDetailController', function ($scope, $stateParams, Picture) {
        $scope.picture = {};
        $scope.load = function (id) {
            Picture.get({id: id}, function(result) {
              $scope.picture = result;
            });
        };
        $scope.load($stateParams.id);
    });
