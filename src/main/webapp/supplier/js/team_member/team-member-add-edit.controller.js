(function () {
    'use strict';

    app.controller('TeamMemberAddEditController', TeamMemberAddEditController);
    TeamMemberAddEditController
        .$inject = [
        '$scope',
        '$stateParams',
        'TeamFactory'
    ];

    function TeamMemberAddEditController($scope, $stateParams, TeamFactory) {
        var teamMemberId = $stateParams.id;
        var master = {};
        $scope.teamMember = {};
        $scope.outlets = {};

        $scope.save = save;
        $scope.cancel = cancel;

        activate();


        function activate() {
            if (teamMemberId) {
                TeamFactory.get({
                        id: teamMemberId
                    },
                    function (data) {
                        master = angular.copy(data.teamMembers[0]);
                        $scope.teamMember = angular.copy(data.teamMembers[0]);
                    }, function (e) {
                        console.error(e);
                    });
            }
        }

        function save(teamMember) {
            TeamFactory.save({
                    id: teamMember.id
                },
                function (data) {
                    console.log(data);
                }, function (e) {
                    console.error(e);
                });

        }

        function cancel() {
            $scope.teamMember = angular.copy(master);
        }
    }

})();
