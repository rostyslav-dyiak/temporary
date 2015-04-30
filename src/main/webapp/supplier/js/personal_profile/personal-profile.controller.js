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
        $scope.user = {};
        $scope.confirmPassword = '';

        $scope.save = save;
        $scope.removePhoto = removeImage;
        $scope.changePassword = changePassword;

        activate();

        function activate() {
            $scope.user = AuthServerProvider.currentUser();
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

        function changePassword() {
            var data = {
                oldPassword: $scope.user.currentPassword,
                newPassword: $scope.user.newPassword
            };
            $http.post('http://localhost:8080/api/account/change_password', data)
                .success(function (response) {
                    AuthServerProvider.login(AuthServerProvider.currentUser().email, $scope.user.newPassword)
                        .then(function () {
                            console.log("User changed.");
                        },
                        function (e) {
                            console.error(e);
                        });
                }).error(function (response) {
                    console.log(response);
                });
        }

        function removeImage() {
            console.log("Removed image");
        }
    }
})();
