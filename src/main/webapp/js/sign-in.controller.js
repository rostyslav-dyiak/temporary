(function () {
    'use strict';

    app.controller('SignInController', SignInController);

    SignInController
        .$inject = [
        '$scope',
        '$window',
        'AuthServerProvider',
        'AccountFactory'
    ];

    function SignInController($scope, $window, AuthServerProvider, AccountFactory) {
        $scope.user = {};
        $scope.credentials = {};

        $scope.rememberMe = true;
        $scope.login = login;

        function login() {
            AuthServerProvider.login($scope.email, $scope.password)
                .then(function (data) {
                    console.log(data);
                    AccountFactory.get({},
                        function (data) {
                            $scope.user = data;
                            console.log($scope.user);
                        }, function (e) {
                            console.error(e);
                        });
                //$window.location.href = '/super_admin/index.html';
            });
        }
    }
})();
