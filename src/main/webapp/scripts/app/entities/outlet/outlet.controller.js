'use strict';

angular.module('kbappApp')
    .controller('OutletController', function ($scope, Outlet, Eatery) {
        $scope.outlets = [];
        $scope.eaterys = Eatery.query();
        $scope.loadAll = function() {
            Outlet.query(function(result) {
               $scope.outlets = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Outlet.update($scope.outlet,
                function () {
                    $scope.loadAll();
                    $('#saveOutletModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Outlet.get({id: id}, function(result) {
                $scope.outlet = result;
                $('#saveOutletModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Outlet.get({id: id}, function(result) {
                $scope.outlet = result;
                $('#deleteOutletConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Outlet.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteOutletConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.outlet = {title: null, delivery_address: null, contact_number: null, postal_code: null, email: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
