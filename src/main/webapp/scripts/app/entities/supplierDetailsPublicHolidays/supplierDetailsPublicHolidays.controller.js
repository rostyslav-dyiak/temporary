'use strict';

angular.module('kbappApp')
    .controller('SupplierDetailsPublicHolidaysController', function ($scope, SupplierDetailsPublicHolidays, SupplierDetails, ParseLinks) {
        $scope.supplierDetailsPublicHolidayss = [];
        $scope.supplierdetailss = SupplierDetails.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            SupplierDetailsPublicHolidays.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.supplierDetailsPublicHolidayss = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            SupplierDetailsPublicHolidays.update($scope.supplierDetailsPublicHolidays,
                function () {
                    $scope.loadAll();
                    $('#saveSupplierDetailsPublicHolidaysModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            SupplierDetailsPublicHolidays.get({id: id}, function(result) {
                $scope.supplierDetailsPublicHolidays = result;
                $('#saveSupplierDetailsPublicHolidaysModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            SupplierDetailsPublicHolidays.get({id: id}, function(result) {
                $scope.supplierDetailsPublicHolidays = result;
                $('#deleteSupplierDetailsPublicHolidaysConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            SupplierDetailsPublicHolidays.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSupplierDetailsPublicHolidaysConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.supplierDetailsPublicHolidays = {supplierDetailsId: null, publicHolidayId: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
