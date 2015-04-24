'use strict';

angular.module('kbappApp')
    .controller('BusinessTypeController', function ($scope, BusinessType) {
        $scope.businessTypes = [];
        $scope.loadAll = function() {
            BusinessType.query(function(result) {
               $scope.businessTypes = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            BusinessType.update($scope.businessType,
                function () {
                    $scope.loadAll();
                    $('#saveBusinessTypeModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            BusinessType.get({id: id}, function(result) {
                $scope.businessType = result;
                $('#saveBusinessTypeModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            BusinessType.get({id: id}, function(result) {
                $scope.businessType = result;
                $('#deleteBusinessTypeConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            BusinessType.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteBusinessTypeConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.businessType = {name: null, description: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
