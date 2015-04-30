(function() {
	'use strict';

    app.controller('TeamMembersController', TeamMembersController);

    TeamMembersController.$inject = [
        '$scope',
        'TeamFactory'
    ];

    function TeamMembersController($scope, TeamFactory) {
        $scope.teamMembers = [];

        $scope.itemsByPage = 5;

        $scope.companyId;

        activate();

        function activate() {
            TeamFactory.query({}, function (data) {
                $scope.teamMembers = data;
            }, function (e) {
                console.error(e);
            });
        }
    }

})();
