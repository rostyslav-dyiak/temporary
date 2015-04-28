(function() {
    'use strict';

    app.controller('TeamMembersController', TeamMembersController);
    TeamMembersController
        .$inject = [
        '$scope',
        'TeamFactory',
        'AccountFactory'
    ];

    function TeamMembersController($scope, TeamFactory, AccountFactory) {
        $scope.teamMembers = {};

        $scope.itemsByPage = 5;
        
        $scope.companyId;

        
        activate();
        function activate() {

        	TeamFactory.get({}, function(data) {
				$scope.teamMembers = data.teamMembers;
				}, function(e) {
				console.error(e);
			});
        }
    }

})();
