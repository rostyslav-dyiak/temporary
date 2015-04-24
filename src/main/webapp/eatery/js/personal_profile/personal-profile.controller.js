(function () {
    'use strict';

    app.controller('PersonalProfileController', PersonalProfileController);

    PersonalProfileController
        .$inject = [
        '$scope',
        'UserFactory'
    ];

    function PersonalProfileController($scope, UserFactory) {
        $scope.master = {};
        $scope.user = {};
        $scope.confirmPassword = '';

        $scope.save = save;
        $scope.cancel = cancel;
        $scope.removeImage = removeImage;
        $scope.changePassword = changePassword;
        $scope.cancelChangePassword = cancelChangePassword;

        activate();

        function activate() {
            UserFactory.get({
                    id: 1
                },
                function (data) {
                    $scope.master = angular.copy(data.user);
                    $scope.user = angular.copy(data.user);
                }, function (e) {
                    console.error(e);
                });
        }


        function save(user) {
            //UserFactory.save({
            //        id: user.id
            //    },
            //    function (data) {
            //        console.log(data);
            //    }, function (e) {
            //        console.error(e);
            //    });

            console.log("Saved type with id: " + user.id);
        }

        function cancel() {
            $scope.user = angular.copy($scope.master);
        }

        function changePassword(user) {
            //UserFactory.save({
            //        id: user.id
            //    },
            //    function (data) {
            //        console.log(data);
            //    }, function (e) {
            //        console.error(e);
            //    });
        }

        function cancelChangePassword() {
            $scope.user = angular.copy($scope.master);
        }

        function removeImage() {
            console.log("Removed image");
        }
    }
})();
