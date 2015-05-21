(function () {
    'use strict';

    app.controller('PersonalProfileController', PersonalProfileController);

    PersonalProfileController
        .$inject = [
        '$scope',
        '$modalInstance',
        'user'
    ];

    function PersonalProfileController($scope, $modalInstance, user) {
        $scope.user = {};

        $scope.dismiss = dismiss;

        activate();

        function activate() {
            $scope.user = user;
        }

        function dismiss() {
            $modalInstance.dismiss('cancel');
        }
    }
})();
