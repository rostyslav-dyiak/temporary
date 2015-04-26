'use strict';

angular.module('kbappApp')
    .controller('ProductTypeController', function ($scope, ProductType) {
        $scope.productTypes = [];
        $scope.loadAll = function() {
            ProductType.query(function(result) {
               $scope.productTypes = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            ProductType.update($scope.productType,
                function () {
                    $scope.loadAll();
                    $('#saveProductTypeModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            ProductType.get({id: id}, function(result) {
                $scope.productType = result;
                $('#saveProductTypeModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            ProductType.get({id: id}, function(result) {
                $scope.productType = result;
                $('#deleteProductTypeConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            ProductType.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteProductTypeConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.productType = {name: null, description: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
