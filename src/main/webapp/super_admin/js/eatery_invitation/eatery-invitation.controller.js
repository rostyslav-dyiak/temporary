(function () {
    'use strict';

    app.controller('EateryInvitationController', EateryInvitationController);

    EateryInvitationController
        .$inject = [
        '$scope',
        'toaster',
        'EateryInvitationFactory'
    ];

    function EateryInvitationController($scope, toaster, EateryInvitationFactory) {
        $scope.eateryInvitation = {};
        $scope.eateryInvitations = {};

        $scope.rowClass = rowClass;
        $scope.editInvitation = editInvitation;
        activate();

        function activate() {
            EateryInvitationFactory.query({},
                function (data) {
                    $scope.eateryInvitations = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function rowClass(invitation) {
            if (invitation.status == 'Pending Send') {
                return 'red-supplier-row';
            }
            else {
                return 'green-supplier-row';
            }
        }


        function editInvitation(invitation) {
            $scope.eateryInvitation = invitation;
        }


    }
})();
