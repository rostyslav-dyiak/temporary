'use strict';

angular.module('kbappApp')
    .controller('SupplierDetailsController', function ($scope, SupplierDetails, ParseLinks) {
        $scope.supplierDetailss = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            SupplierDetails.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.supplierDetailss = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            SupplierDetails.update($scope.supplierDetails,
                function () {
                    $scope.loadAll();
                    $('#saveSupplierDetailsModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            SupplierDetails.get({id: id}, function(result) {
                $scope.supplierDetails = result;
                $('#saveSupplierDetailsModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            SupplierDetails.get({id: id}, function(result) {
                $scope.supplierDetails = result;
                $('#deleteSupplierDetailsConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            SupplierDetails.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSupplierDetailsConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.supplierDetails = {code: null, busRegNumber: null, busDescription: null, address: null, faxNumber: null, gstRegistered: null, gstRegistrationNumber: null, logoId: null, publicPricingVisible: null, mainPictureId: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
