'use strict';

angular.module('kbappApp')
    .controller('EateryController', function ($scope, Eatery, Picture) {
        $scope.eaterys = [];
        $scope.pictures = Picture.query();
        $scope.loadAll = function() {
            Eatery.query(function(result) {
               $scope.eaterys = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Eatery.update($scope.eatery,
                function () {
                    $scope.loadAll();
                    $('#saveEateryModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Eatery.get({id: id}, function(result) {
                $scope.eatery = result;
                $('#saveEateryModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Eatery.get({id: id}, function(result) {
                $scope.eatery = result;
                $('#deleteEateryConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Eatery.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteEateryConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.eatery = {company_name: null, bus_registration_number: null, billing_number: null, postal_code: null, email: null, company_contact_number: null, company_fax_number: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
