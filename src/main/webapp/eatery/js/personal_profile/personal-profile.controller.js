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
        $scope.removePhoto = removeImage;
        $scope.changePassword = changePassword;

        activate();

        function activate() {
            $scope.master = angular.copy(AuthServerProvider.currentUser());
            AuthServerProvider.updateUserInfo()
                .then(function () {
                    $scope.user = angular.copy(AuthServerProvider.currentUser());
                },
                function (e) {
                    $scope.error = 'Please try again.';
                    console.error(e);
                });

        }


        function save() {
            UserFactory.update($scope.user,
                function (data) {
                    $http.get("/api/account")
                        .success(function (data) {
                            AuthServerProvider.setUser(data);
                            toaster.pop('success', 'Success', 'User saved');
                        })
                        .error(function (data) {
                            console.log(data);
                            toaster.pop('error', 'Error', 'Please try again');
                        });
                }, function (e) {
                    console.error(e);
                });
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
                            toaster.pop('success', 'Success', 'User password changed');
                        },
                        function (e) {
                            console.error(e);
                            toaster.pop('error', 'Error', 'Please try again');
                        });
                    $scope.user = {};
                }).error(function (response) {
                    console.log(response);
                    $scope.user = {};
                });
        }

        function removeImage() {
            console.log("Removed image");
        }
    }
})();
