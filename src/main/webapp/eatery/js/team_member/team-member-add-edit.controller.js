(function () {
    'use strict';

    app.controller('TeamMemberAddEditController', TeamMemberAddEditController);
    TeamMemberAddEditController
        .$inject = [
        '$scope',
        '$stateParams',
        '$http',
        'toaster',
        'TeamFactory',
        'MemberFactory',
        'AuthServerProvider',
        'OutletsCompanyFactory'
    ];

    function TeamMemberAddEditController($scope, $stateParams, $http, toaster, TeamFactory, MemberFactory, AuthServerProvider, OutletsCompanyFactory) {
        var teamMemberId = $stateParams.id;
        if (teamMemberId) {
            $scope.saveButtonText = "Save Changes";
        } else {
            $scope.saveButtonText = "Send Invitation";
        }
        $scope.teamMember = {};
        $scope.outlets = {};

        $scope.supplierInviteDTO = {
            user: {}
        };

        $scope.save = save;
        $scope.addUser = addUser;
        $scope.createUser = createUser;
        $scope.updateUser = updateUser;
        $scope.selectedRole = selectedRole;

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
            OutletsCompanyFactory.query({},
                function(data) {
                    $scope.outlets = data;
                });
            $scope.company = AuthServerProvider.currentUserCompany();
        }

        function save() {
            if (teamMemberId) {
                TeamFactory.update($scope.teamMember,
                    function (data) {
                        console.log(data);
                        toaster.pop('success', 'Success', 'Team member updated');
                    }, function (e) {
                        console.error(e);
                        toaster.pop('error', 'Error', 'Please try again');
                    });
            }
            else {
                TeamFactory.save($scope.teamMember,
                    function (data) {
                        console.log(data);
                        toaster.pop('success', 'Success', 'Team member saved');
                    }, function (e) {
                        console.error(e);
                        toaster.pop('error', 'Error', 'Please try again');
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
            $scope.supplierInviteDTO.outlet = $scope.teamMember.userDTO.outlet;

            $http.post("/api/invite_supplier_member",
                $scope.supplierInviteDTO)
                .success(function (data) {
                    console.log(data);
                    toaster.pop('success', 'Success', 'User created');
                }).
                error(function (data) {
                    console.log(data);
                    toaster.pop('error', 'Error', 'Please try again');
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
            $scope.supplierInviteDTO.outlet = $scope.teamMember.userDTO.outlet;

            MemberFactory.update($scope.supplierInviteDTO,
                function (data) {
                    console.log('Saved ' + data.id);
                    toaster.pop('success', 'Success', 'User updated');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function selectedRole(){
            if($scope.role == "ROLE_EATERY_ADMIN")
                $scope.teamMember.userDTO.role = "ROLE_EATERY_ADMIN";
            else{
                $scope.teamMember.userDTO.role = "ROLE_EATERY";
                $scope.teamMember.userDTO.outlet = $scope.outlets[$scope.role];
            }
        }

    }

})();
