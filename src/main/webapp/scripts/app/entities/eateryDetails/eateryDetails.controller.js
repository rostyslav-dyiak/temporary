'use strict';

angular.module('kbappApp')
    .controller('EateryDetailsController', function ($scope, EateryDetails, ParseLinks) {
        $scope.eateryDetailss = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            EateryDetails.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.eateryDetailss = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            EateryDetails.update($scope.eateryDetails,
                function () {
                    $scope.loadAll();
                    $('#saveEateryDetailsModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            EateryDetails.get({id: id}, function(result) {
                $scope.eateryDetails = result;
                $('#saveEateryDetailsModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            EateryDetails.get({id: id}, function(result) {
                $scope.eateryDetails = result;
                $('#deleteEateryDetailsConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            EateryDetails.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteEateryDetailsConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.eateryDetails = {code: null, BusRegNumber: null, BillingAddress: null, PostalCode: null, FaxNumber: null, logoId: null, topRightPictureId: null, contactPersonId: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
