(function () {
    'use strict';

    app.controller('SignUpController', SignUpController);

    SignUpController
        .$inject = [
        '$scope',
        '$http',
        '$location',
        'localStorageService',
        'AuthServerProvider'
    ];

    function SignUpController($scope, $http, $location, localStorageService, AuthServerProvider) {
        $scope.user = {};
        $scope.authError = '';

        $scope.expired = false;
        $scope.created = false;

        $scope.signup = signup;

        activate();

        function activate() {
            $http.get('/api/activate', {
                params: {
                    key: $location.search().key
                }
            }).success(function (user) {
                    $scope.user = user;
                }
            ).error(function (e) {
                    $scope.expired = false;
                    console.error(e);
                });
        }

        function signup() {
            $http.post('/api/account/password', $scope.user
            ).success(function (data) {
                    AuthServerProvider.login($scope.user.email, $scope.user.password)
                        .then(function (data) {
                        }, function (e) {
                            $scope.authError = 'Please try again.';
                            console.error(e);
                        });
                    $scope.created = true;
                    $scope.user = AuthServerProvider.currentUser();
                }).error(function (data) {
                    $scope.authError = "Check your data";
                    console.error(data);
                });
        }
    }
})();
