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

        $scope.teamMember = {};
        $scope.outlets = {};

        $scope.save = save;

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
    }

})();
