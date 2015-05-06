'use strict';

angular.module('kbappApp')
    .controller('SystemAnnouncementController', function ($scope, SystemAnnouncement, ParseLinks) {
        $scope.systemAnnouncements = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            SystemAnnouncement.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.systemAnnouncements = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            SystemAnnouncement.update($scope.systemAnnouncement,
                function () {
                    $scope.loadAll();
                    $('#saveSystemAnnouncementModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            SystemAnnouncement.get({id: id}, function(result) {
                $scope.systemAnnouncement = result;
                $('#saveSystemAnnouncementModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            SystemAnnouncement.get({id: id}, function(result) {
                $scope.systemAnnouncement = result;
                $('#deleteSystemAnnouncementConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            SystemAnnouncement.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSystemAnnouncementConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.systemAnnouncement = {assignmentType: null, subject: null, message: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
