(function () {
    'use strict';

    app.controller('PersonalProfileController', PersonalProfileController);

    PersonalProfileController
        .$inject = [
        '$scope',
        '$http',
        'AuthServerProvider'
    ];

    function PersonalProfileController($scope, $http, AuthServerProvider) {
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
            $scope.master = angular.copy(AuthServerProvider.currentUser());
            $scope.user = angular.copy(AuthServerProvider.currentUser());
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

        function changePassword() {
            var data = {
                oldPassword: $scope.user.currentPassword,
                newPassword: $scope.user.newPassword
            }
            $http.post('http://localhost:8080/api/account/change_password', data)
                .success(function (response) {
                    AuthServerProvider.login(AuthServerProvider.currentUser().email, $scope.user.newPassword)
                        .then(function () {
                            console.log("User changed.");
                        },
                        function (e) {
                            console.error(e);
                        });
                    $scope.user = {};
                    console.log(response);
                }).error(function (response) {
                    console.log(response);
                    $scope.user = {};
                });
        }

        function cancelChangePassword() {
            $scope.user = angular.copy($scope.master);
        }

        function removeImage() {
            console.log("Removed image");
        }
    }
})();
