'use strict';

angular.module('kbappApp')
    .controller('CompanyController', function ($scope, Company, ParseLinks) {
        $scope.companys = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Company.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.companys = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Company.update($scope.company,
                function () {
                    $scope.loadAll();
                    $('#saveCompanyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Company.get({id: id}, function(result) {
                $scope.company = result;
                $('#saveCompanyModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Company.get({id: id}, function(result) {
                $scope.company = result;
                $('#deleteCompanyConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Company.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteCompanyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.company = {name: null, email: null, contactNumber: null, businessType: null, status: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
