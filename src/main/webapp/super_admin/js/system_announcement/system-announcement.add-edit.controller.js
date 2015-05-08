(function () {
    'use strict';

    app.controller('SystemAnnouncementAddUpdateController', SystemAnnouncementAddUpdateController);

    SystemAnnouncementAddUpdateController
        .$inject = [
        '$scope',
        '$stateParams',
        'toaster',
        'UserFactory',
        'SystemAnnouncementFactory'
    ];

    function SystemAnnouncementAddUpdateController($scope, $stateParams, toaster, UserFactory, SystemAnnouncementFactory) {
        var systemAnnouncementId = $stateParams.id;
        var usersMaster = [];

        $scope.systemAnnouncement = {
            isNormalUser: true,
            isAdmin: true
        };
        $scope.users = [];
        $scope.rowCollection = [];
        $scope.assignmentTypes = {
            'ALL': 'ALL',
            'SUPPLIERS': 'ALL SUPPLIERS',
            'EATERIES': 'ALL EATERIES',
            'CUSTOM': 'CUSTOM'
        };

        $scope.save = save;
        $scope.filterUsers = filterUsers;
        $scope.toggleUser = toggleUser;
        $scope.selectAll = selectAll;
        $scope.deselectAll = deselectAll;

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
                                $scope.systemAnnouncement = {
                                    id: data.id,
                                    assignmentType: data.assignmentType,
                                    subject: data.subject,
                                    message: data.content,
                                    isNormalUser: data.isNormalUser,
                                    isAdmin: data.isAdmin,
                                    users: getUsersId(data.sent)
                                };
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
            SystemAnnouncementFactory.update($scope.systemAnnouncement,
                function () {
                    toaster.pop('success', 'Success', 'System Announcement has been sent and updated');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });

        }

        function selectAll() {
            if (!$scope.systemAnnouncement.users) {
                $scope.systemAnnouncement.users = [];
            }
            for (var i = 0; i < $scope.users.length; i++) {
                $scope.systemAnnouncement.users.push($scope.users[i].id);
            }
            $scope.selectedAll = true;
            $scope.deselectedAll = false;
        }

        function deselectAll() {
            $scope.systemAnnouncement.users = [];
            $scope.selectedAll = false;
            $scope.deselectedAll = true;
        }

        function toggleUser(id) {
            if (!$scope.systemAnnouncement.users) {
                $scope.systemAnnouncement.users = [];
            }
            var index = $scope.systemAnnouncement.users.indexOf(id);
            if (index > -1) {
                $scope.systemAnnouncement.users.splice(index, 1);
            } else {
                $scope.systemAnnouncement.users.push(id);
            }
        }

        function filterUsers() {
            $scope.users = [];

            if ($scope.systemAnnouncement.assignmentType == 'CUSTOM') {
                for (var i = 0; i < usersMaster.length; i++) {
                    checkAdminRole(usersMaster[i]);
                }

                if (!$scope.systemAnnouncement.users) {
                    $scope.systemAnnouncement.users = [];
                }
                for (var i = 0; i < $scope.users.length; i++) {
                    if ($scope.systemAnnouncement.users.indexOf($scope.users[i].id) > -1) {
                        temp.push($scope.users[i].id);
                    }
                }
                $scope.rowCollection = angular.copy($scope.users);
                $scope.systemAnnouncement.users = angular.copy(temp);
            } else {
                $scope.systemAnnouncement.users = [];
            }
        }

        function checkAdminRole(user) {
            if ($scope.systemAnnouncement.isNormalUser && !user.authorities[0].isAdmin) {
                $scope.users.push(user);
            }
            if ($scope.systemAnnouncement.isAdmin && user.authorities[0].isAdmin) {
                $scope.users.push(user);
            }
        }

        function getUsersId(users) {
            var result = [];
            if(users) {
                for (var i = 0; i < users.length; i++) {
                    result.push(users[i]);
                }
            }
            return result;
        }

    }
})();
