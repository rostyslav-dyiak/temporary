(function () {

    'use strict';

    angular.module('app').factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider
        .$inject = [
        '$http',
        'localStorageService'
    ];

    function AuthServerProvider($http, localStorageService) {
        var user;

        var service = {
            login: login,
            logout: logout,
            getToken: getToken,
            currentUser: currentUser,
            setUser: setUser,
            currentUserCompany: currentUserCompany
        };

        return service;

        function login(email, password) {
            var data = "email=" + email + "&password="
                + password;

            return $http.post('api/authenticate', data, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "Accept": "application/json"
                }
            }).success(function (response) {
                localStorageService.set('token', response);

                return response;
            });
        }

        function logout() {
            localStorageService.clearAll();
        }

        function setUser(user) {
            return localStorageService.set('user', user);
        }


        function getToken() {
            return localStorageService.get('token');
        }

        function currentUser() {
            if (!user) {
                user = localStorageService.get('user');
            }
            return user;
        }

        function currentUserCompany() {
            if (!user) {
                user = localStorageService.get('user');
            }
            return user.company;
        }
    }
})();
