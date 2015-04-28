'use strict';

angular.module('kbappApp')
    .controller('PublicHolidayController', function ($scope, PublicHoliday, ParseLinks) {
        $scope.publicHolidays = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            PublicHoliday.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.publicHolidays = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            PublicHoliday.update($scope.publicHoliday,
                function () {
                    $scope.loadAll();
                    $('#savePublicHolidayModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            PublicHoliday.get({id: id}, function(result) {
                $scope.publicHoliday = result;
                $('#savePublicHolidayModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            PublicHoliday.get({id: id}, function(result) {
                $scope.publicHoliday = result;
                $('#deletePublicHolidayConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            PublicHoliday.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePublicHolidayConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.publicHoliday = {description: null, date: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
