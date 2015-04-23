'use strict';

angular.module('kbappApp')
    .controller('OutletController', function ($scope, Outlet, ParseLinks) {
        $scope.outlets = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Outlet.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.outlets = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
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
            $scope.outlet = {name: null, deliveryAddress: null, postalCode: null, contactNumber: null, email: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
