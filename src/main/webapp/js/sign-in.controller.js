(function () {
    'use strict';

    app.controller('SignInController', SignInController);

    SignInController
        .$inject = [
        '$scope',
        '$window',
        'localStorageService',
        'AuthServerProvider',
        'AccountFactory'
    ];

    function SignInController($scope, $window, localStorageService, AuthServerProvider) {
        $scope.credentials = {};
        $scope.error = '';

        $scope.login = login;

        function login(credentials) {
            AuthServerProvider.login(credentials.email, credentials.password)
                .then(function (data) {
                    localStorageService.set('token', data.data);
                    redirectToApp(AuthServerProvider.currentUser().role);
                },
                function (e) {
                    $scope.error = 'Please try again.';
                    console.error(e);
                });
        }

        function redirectToApp(role) {
            if (role == 'ROLE_SUPER_ADMIN') {
                $window.location.href = '/super_admin/index.html';
            } else if (role == 'ROLE_EATERY_ADMIN' || role == 'ROLE_EATERY') {
                $window.location.href = '/eatery/index.html';
            } else if (role == 'ROLE_SUPPLIER_ADMIN' || role == 'ROLE_SUPPLIER') {
                $window.location.href = '/supplier/index.html';
            }
        }
    }
})();
