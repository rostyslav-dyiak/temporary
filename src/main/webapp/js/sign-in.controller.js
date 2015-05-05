(function () {
    'use strict';

    app.controller('SignInController', SignInController);

    SignInController
        .$inject = [
        '$scope',
        '$window',
        '$http',
        'toaster',
        'AuthServerProvider'
    ];

    function SignInController($scope, $window, $http, toaster, AuthServerProvider) {
        $scope.credentials = {};
        $scope.error = '';

        $scope.login = login;

        function login() {
            AuthServerProvider.login($scope.credentials.email, $scope.credentials.password)
                .then(function () {
                    $http.get("/api/account")
                        .success(function (data) {
                            AuthServerProvider.setUser(data);
                            redirectToApp();
                        })
                        .error(function (data) {
                            console.log(data);
                        });
                },
                function (e) {
                    toaster.pop('error', 'Login Failed', 'Email or Password is Incorrect');
                    console.error(e);
                });
        }

        function redirectToApp() {
            if (AuthServerProvider.hasRole('ROLE_SUPER_ADMIN')) {
                $window.location.href = '/super_admin/index.html';
            } else if (AuthServerProvider.hasRole(['ROLE_EATERY_ADMIN', 'ROLE_EATERY'])) {
                $window.location.href = '/eatery/index.html';
            } else if (AuthServerProvider.hasRole(['ROLE_SUPPLIER_ADMIN', 'ROLE_SUPPLIER'])) {
                $window.location.href = '/supplier/index.html';
            }
        }
    }
})();
