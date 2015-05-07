(function () {
    'use strict';

    app.controller('SystemAnnouncementAddUpdateController', SystemAnnouncementAddUpdateController);

    SystemAnnouncementAddUpdateController
        .$inject = [
        '$scope',
        '$stateParams',
        'toaster',
        'SystemAnnouncementFactory',
        'UserFactory'
    ];

    function SystemAnnouncementAddUpdateController($scope, $stateParams, toaster, SystemAnnouncementFactory, UserFactory) {
        var systemAnnouncementId = $stateParams.id;
        var usersMaster = [];

        $scope.systemAnnouncement = {
            isNormalUser: true,
            isAdmin: true
        };
        $scope.users = [];
        $scope.rowCollection = [];
        $scope.assignmentTypes = ['ALL', 'SUPPLIERS', 'EATERIES', 'CUSTOM'];

        $scope.save = save;
        $scope.filterUsers = filterUsers;
        $scope.toggleUser = toggleUser;

        activate();

        function activate() {
            UserFactory.query({},
                function (data) {
                    usersMaster = data;
                    if (systemAnnouncementId) {
                        SystemAnnouncementFactory.get({
                                id: systemAnnouncementId
                            },
                            function (data) {
                                $scope.systemAnnouncement = data;
                                filterUsers();
                            }, function (e) {
                                console.error(e);
                            });
                    }
                }, function (e) {
                    console.log(e);
                });

        }

        function save() {
            if (systemAnnouncementId) {
                SystemAnnouncementFactory.update($scope.systemAnnouncement,
                    function () {
                        toaster.pop('success', 'Success', 'System Announcement has been sent and updated');
                        activate();
                    }, function (e) {
                        console.error(e);
                        toaster.pop('error', 'Error', 'Please try again');
                    });
            } else {
                SystemAnnouncementFactory.save($scope.systemAnnouncement,
                    function () {
                        toaster.pop('success', 'Success', 'System Announcement has been sent and updated');
                        activate();
                    }, function (e) {
                        console.error(e);
                        toaster.pop('error', 'Error', 'Please try again');
                    });
            }
        }

        function toggleUser(id) {
            if(!$scope.systemAnnouncement.users) {
                $scope.systemAnnouncement.users = [];
            }
            var index = $scope.systemAnnouncement.users.indexOf(id);
            if(index > -1) {
                $scope.systemAnnouncement.users.splice(index, 1);
            } else {
                $scope.systemAnnouncement.users.push(id);
            }
        }

        function filterUsers() {
            $scope.users = [];
            for(var i = 0; i < usersMaster.length; i++) {
                if($scope.systemAnnouncement.assignmentType == 'EATERIES' && usersMaster[i].authorities[0].name.indexOf('EATERY') > -1) {
                    checkAdminRole(usersMaster[i]);
                } else if($scope.systemAnnouncement.assignmentType == 'SUPPLIERS' && usersMaster[i].authorities[0].name.indexOf('SUPPLIER') > -1) {
                    checkAdminRole(usersMaster[i]);
                } else if($scope.systemAnnouncement.assignmentType == 'CUSTOM') {
                    checkAdminRole(usersMaster[i]);
                }
            }
            $scope.rowCollection = angular.copy($scope.users);
        }

        function checkAdminRole(user) {
            if($scope.systemAnnouncement.isNormalUser && !user.authorities[0].isAdmin) {
                $scope.users.push(user);
            }
            if($scope.systemAnnouncement.isAdmin && user.authorities[0].isAdmin) {
                $scope.users.push(user);
            }
        }

    }
})();
