'use strict';

angular.module('kbappApp')
    .controller('PictureController', function ($scope, Picture) {
        $scope.pictures = [];
        $scope.loadAll = function() {
            Picture.query(function(result) {
               $scope.pictures = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Picture.update($scope.picture,
                function () {
                    $scope.loadAll();
                    $('#savePictureModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Picture.get({id: id}, function(result) {
                $scope.picture = result;
                $('#savePictureModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Picture.get({id: id}, function(result) {
                $scope.picture = result;
                $('#deletePictureConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Picture.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePictureConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.picture = {url: null, description: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
