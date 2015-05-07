(function () {
    'use strict';

    app.controller('SystemAnnouncementController', SystemAnnouncementController);
    SystemAnnouncementController
        .$inject = [
        '$scope',
        'toaster',
        'SystemAnnouncementFactory'
    ];

    function SystemAnnouncementController($scope, toaster, SystemAnnouncementFactory) {
        $scope.announcements = [];
        $scope.rowCollection = [];
        $scope.rowCollectionViewers = [];
        $scope.rowCollectionSent = [];
        $scope.selectedAnnouncement = {};

        $scope.selectAnnouncement = selectAnnouncement;
        $scope.delete = deleteAnnouncement;

        activate();

        function activate() {
            SystemAnnouncementFactory.query({},
                function (data) {
                    $scope.announcements = data.announcements;
                    $scope.rowCollection = data.announcements;
                }, function (e) {
                    console.error(e);
                });
        }

        function selectAnnouncement(announcement) {
            if($scope.selectedAnnouncement.id == announcement.id) {
                $scope.selectedAnnouncement = {};
            } else {
                $scope.selectedAnnouncement = announcement;
                $scope.rowCollectionSent = announcement.sent;
                $scope.rowCollectionViewers = announcement.viewed;
            }
        }

        function deleteAnnouncement() {
            SystemAnnouncementFactory.delete({
                    id: $scope.selectedAnnouncement.id
                },
                function () {
                    toaster.pop('success', 'Success', 'System Announcement has been removed');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }
    }

})();
