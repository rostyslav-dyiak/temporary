'use strict';

angular.module('kbappApp')
    .controller('Eatery_memberController', function ($scope, Eatery_member) {
        $scope.eatery_members = [];
        $scope.loadAll = function() {
            Eatery_member.query(function(result) {
               $scope.eatery_members = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Eatery_member.update($scope.eatery_member,
                function () {
                    $scope.loadAll();
                    $('#saveEatery_memberModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Eatery_member.get({id: id}, function(result) {
                $scope.eatery_member = result;
                $('#saveEatery_memberModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Eatery_member.get({id: id}, function(result) {
                $scope.eatery_member = result;
                $('#deleteEatery_memberConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Eatery_member.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteEatery_memberConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.eatery_member = {title: null, salutation: null, contact_number: null, email: null, status: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
