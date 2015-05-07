'use strict';

angular.module('kbappApp')
    .controller('SystemAnnouncementUserController', function ($scope, SystemAnnouncementUser, ParseLinks) {
        $scope.systemAnnouncementUsers = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            SystemAnnouncementUser.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.systemAnnouncementUsers = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            SystemAnnouncementUser.update($scope.systemAnnouncementUser,
                function () {
                    $scope.loadAll();
                    $('#saveSystemAnnouncementUserModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            SystemAnnouncementUser.get({id: id}, function(result) {
                $scope.systemAnnouncementUser = result;
                $('#saveSystemAnnouncementUserModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            SystemAnnouncementUser.get({id: id}, function(result) {
                $scope.systemAnnouncementUser = result;
                $('#deleteSystemAnnouncementUserConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            SystemAnnouncementUser.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSystemAnnouncementUserConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.systemAnnouncementUser = {systemAnnouncementId: null, userId: null, status: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
