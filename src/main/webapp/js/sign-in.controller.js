(function() {
    'use strict';

    app.controller('SignInController', SignInController);

    SignInController
        .$inject = [
        '$scope',
        '$window',
        'AuthServerProvider'
    ];

    function SignInController($scope, $window,  AuthServerProvider) {
        $scope.user = {};
        $scope.credentials = {};

        $scope.rememberMe = true;
        $scope.login = function () {
            AuthServerProvider.login($scope.email,$scope.password).then(function (data) {
                $window.location.href= '/super_admin/index.html';
            });
            //var data = "username=" + $scope.username + "&password="
            //    + $scope.password;
            //
            //$http.post('api/authenticate', data, {
            //    headers: {
            //        "Content-Type": "application/x-www-form-urlencoded",
            //        "Accept": "application/json"
            //    }
            //}).success(function (response) {
            //    localStorageService.set('token', response);
            //    console.log( response);
            //    return response;
            //});
        };
    }
})();
