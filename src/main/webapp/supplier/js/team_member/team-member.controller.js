(function() {
	'use strict';

    app.controller('TeamMembersController', TeamMembersController);

    TeamMembersController.$inject = [
        '$scope',
        'TeamFactory'
    ];

    function TeamMembersController($scope, TeamFactory) {
        $scope.teamMembers = [];
        $scope.rowCollection = [];

        activate();

        function activate() {
            TeamFactory.query({}, function (data) {
                $scope.teamMembers = data;
                $scope.rowCollection = data;
            }, function (e) {
                console.error(e);
            });
        }
    }

})();
