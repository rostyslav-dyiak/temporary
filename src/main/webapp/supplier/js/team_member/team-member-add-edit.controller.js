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
        'AuthServerProvider'
    ];

    function TeamMemberAddEditController($scope, $stateParams, $http, toaster, TeamFactory, MemberFactory, AuthServerProvider) {
        $scope.teamMemberId = $stateParams.id;
        var master = {};
        if ($scope.teamMemberId) {
            $scope.saveButtonText = "Save Changes";
        } else {
            $scope.saveButtonText = "Send Invitation";
        }
        $scope.teamMember = {};
        $scope.outlets = {};

        $scope.supplierInviteDTO = {
            user: {}
        };

        $scope.addUser = addUser;
        $scope.createUser = createUser;
        $scope.updateUser = updateUser;
        $scope.revert = revert;

        activate();


        function activate() {
            if ($scope.teamMemberId) {
                TeamFactory.get({
                        id: $scope.teamMemberId
                    },
                    function (data) {
                        master = angular.copy(data);
                        $scope.teamMember = angular.copy(data);
                    }, function (e) {
                        console.error(e);
                    });
            }
            $scope.company = AuthServerProvider.currentUserCompany();
        }

        function addUser() {
            if (!$scope.teamMemberId) {
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
            $scope.supplierInviteDTO.status = "PENDING";
            $scope.supplierInviteDTO.email = $scope.teamMember.userDTO.email;
            $scope.supplierInviteDTO.role = $scope.teamMember.userDTO.role;
            $scope.supplierInviteDTO.contactNumber = $scope.teamMember.company.contactNumber;

            $http.post("/api/invite_supplier_member",
                $scope.supplierInviteDTO)
                .success(function (data) {
                    console.log(data);
                    toaster.pop('success', 'Success', 'Member created');
                }).
                error(function (data) {
                    console.log(data);
                    toaster.pop('error', 'Error', 'Error! Please try again');
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
                    toaster.pop('success', 'Success', 'User updated');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Error! Please try again');
                });
        }

        function revert() {
            $scope.teamMember = angular.copy(master);
        }
    }

})();
