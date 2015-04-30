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
                        master = angular.copy(data);
                        $scope.teamMember = angular.copy(data);
                    }, function (e) {
                        console.error(e);
                    });
            }
        }

        function save(teamMember) {
        	if (teamMemberId) {
	            TeamFactory.update(teamMember,
	                function (data) {
	                    console.log(data);
	                }, function (e) {
	                    console.error(e);
	                });
        	}
        	else{
        		TeamFactory.save(teamMember,
                        function (data) {
                            console.log(data);
                        }, function (e) {
                            console.error(e);
                        });	
        	}

        }

        function cancel() {
            $scope.teamMember = angular.copy(master);
        }
    }

})();
