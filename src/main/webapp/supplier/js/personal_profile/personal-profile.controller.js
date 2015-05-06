(function () {
    'use strict';

    app.controller('PersonalProfileController', PersonalProfileController);

    PersonalProfileController
        .$inject = [
        '$scope',
        '$http',
        'toaster',
        'UserFactory',
        'AuthServerProvider',
        'FileUploadService'
    ];

    function PersonalProfileController($scope, $http, toaster, UserFactory, AuthServerProvider, FileUploadService) {
        var master = {};
        $scope.user = {};
        $scope.confirmPassword = '';
        $scope.logo = {};

        $scope.save = save;
        $scope.uploadLogo = uploadLogo;
        $scope.removeLogo = removeLogo;
        $scope.changePassword = changePassword;
        $scope.revert = revert;

        activate();

        function activate() {
            AuthServerProvider.updateUserInfo()
                .then(function () {
                    $scope.user = angular.copy(AuthServerProvider.currentUser());
                    master = angular.copy(AuthServerProvider.currentUser());
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
                            toaster.pop('success', 'Success', 'User has been updated');
                        })
                        .error(function (data) {
                            console.log(data);
                            toaster.pop('error', 'Error', 'Please try again');
                        });
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function uploadLogo(image) {
            if ($scope.logo && image.length > 0) {
                FileUploadService.uploadFileToUrl(image[0])
                    .then(function (response) {
                        $scope.user.company.logo = {
                            title: image[0].name,
                            url: response.data.path
                        };
                    });
            }
        }

        function revert() {
            $scope.user = master;
        }

        function removeLogo() {
            $scope.user.company.logo = {
                title: 'logo_placeholder',
                url: '/logo_placeholder.png'
            };
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
                }).error(function (response) {
                    console.log(response);
                });
        }
    }
})();
