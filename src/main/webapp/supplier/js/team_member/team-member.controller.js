(function() {
    'use strict';

    app.controller('TeamMembersController', TeamMembersController);
    TeamMembersController
        .$inject = [
        '$scope',
        'TeamFactory'
    ];

    function TeamMembersController($scope, TeamFactory) {
        $scope.teamMembers = {};

        $scope.itemsByPage = 5;

        activate();
        function activate() {
            TeamFactory.get({},
                function (data) {
                    $scope.teamMembers = data.teamMembers;
                }, function (e) {
                    console.error(e);
                });
        }
    }

})();
