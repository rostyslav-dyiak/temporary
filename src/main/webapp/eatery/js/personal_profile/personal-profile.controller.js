(function () {
    'use strict';

    app.controller('PersonalProfileController', PersonalProfileController);

    PersonalProfileController
        .$inject = [
        '$scope',
        '$http',
        'UserFactory',
        'AuthServerProvider'
    ];

    function PersonalProfileController($scope, $http, UserFactory, AuthServerProvider) {
        $scope.master = {};
        $scope.user = {};
        $scope.confirmPassword = '';

        $scope.save = save;
        $scope.cancel = cancel;
        $scope.removePhoto = removeImage;
        $scope.changePassword = changePassword;
        $scope.cancelChangePassword = cancelChangePassword;

        activate();

        function activate() {
            $scope.master = angular.copy(AuthServerProvider.currentUser());
            $scope.user = angular.copy(AuthServerProvider.currentUser());
        }


        function save() {
            UserFactory.update($scope.user,
                function (data) {
                    $http.get("/api/account")
                        .success(function (data) {
                            AuthServerProvider.setUser(data);
                        })
                        .error(function (data) {
                            console.log(data);
                        });
                }, function (e) {
                    console.error(e);
                });
        }

        function cancel() {
            $scope.user = angular.copy($scope.master);
        }

        function changePassword() {
            var data = {
                oldPassword: $scope.user.currentPassword,
                newPassword: $scope.user.newPassword
            };
            $http.post('/api/account/change_password', data)
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
