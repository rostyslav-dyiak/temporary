(function () {
    'use strict';

    app.controller('TeamMemberAddEditController', TeamMemberAddEditController);
    TeamMemberAddEditController
        .$inject = [
        '$scope',
        '$stateParams',
        '$http',
        'TeamFactory',
        'MemberFactory',
        'AuthServerProvider'
    ];

    function TeamMemberAddEditController($scope, $stateParams, $http, TeamFactory, MemberFactory, AuthServerProvider) {
        var teamMemberId = $stateParams.id;

        $scope.teamMember = {};
        $scope.outlets = {};

        $scope.supplierInviteDTO = {
            user: {}
        };

        $scope.save = save;
        $scope.addUser = addUser;
        $scope.createUser = createUser;
        $scope.updateUser = updateUser;

        activate();
        function activate() {
            if (teamMemberId) {
                TeamFactory.get({
                        id: teamMemberId
                    },
                    function (data) {
                        $scope.teamMember = data;
                    }, function (e) {
                        console.error(e);
                    });
            }
            $scope.company = AuthServerProvider.currentUserCompany();
        }

        function save() {
            if (teamMemberId) {
                TeamFactory.update($scope.teamMember,
                    function (data) {
                        console.log(data);
                    }, function (e) {
                        console.error(e);
                    });
            }
            else{
                TeamFactory.save($scope.teamMember,
                    function (data) {
                        console.log(data);
                    }, function (e) {
                        console.error(e);
                    });
            }
        }

        function addUser() {
            if (!teamMemberId) {
                createUser();
            } else {
                updateUser();
            }

        }

        function createUser() {
            $scope.supplierInviteDTO.company = $scope.company;
            $scope.supplierInviteDTO.salutation = $scope.teamMember.userDTO.salutation;
            $scope.supplierInviteDTO.firstName = $scope.teamMember.userDTO.firstName;
            $scope.supplierInviteDTO.title = $scope.teamMember.userDTO.title;
            $scope.supplierInviteDTO.status = $scope.teamMember.company.status;
            $scope.supplierInviteDTO.email = $scope.teamMember.userDTO.email;
            $scope.supplierInviteDTO.role = $scope.teamMember.userDTO.role;
            $scope.supplierInviteDTO.contactNumber = $scope.teamMember.company.contactNumber;

            $http.post("/api/invite_supplier_member",
                $scope.supplierInviteDTO)
                .success(function (data) {
                    console.log(data)
                }).
                error(function (data) {
                    console.log(data)
                });
        }

        function updateUser() {
            $scope.supplierInviteDTO.company = $scope.company;
            $scope.supplierInviteDTO.salutation = $scope.teamMember.userDTO.salutation;
            $scope.supplierInviteDTO.firstName = $scope.teamMember.userDTO.firstName;
            $scope.supplierInviteDTO.title = $scope.teamMember.userDTO.title;
            $scope.supplierInviteDTO.status = $scope.teamMember.company.status;
            $scope.supplierInviteDTO.email = $scope.teamMember.userDTO.email;
            $scope.supplierInviteDTO.role = $scope.teamMember.userDTO.role;
            $scope.supplierInviteDTO.contactNumber = $scope.teamMember.company.contactNumber;

            MemberFactory.update($scope.supplierInviteDTO,
                function (data) {
                    console.log('Saved ' + data.id)
                }, function (e) {
                    console.error(e);
                });
        }

    }

})();
