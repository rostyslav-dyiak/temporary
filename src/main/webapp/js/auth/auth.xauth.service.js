(function () {

    'use strict';

    angular.module('app').factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider
        .$inject = [
        '$http',
        '$q',
        'localStorageService'
    ];

    function AuthServerProvider($http, $q, localStorageService) {
        var user;

        var service = {
            login: login,
            logout: logout,
            getToken: getToken,
            currentUser: currentUser,
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
                localStorageService
                $http.get("/api/account")
                    .success(function (data) {
                        localStorageService.set('user', data);
                    });
                return response;
            });
        }

        function logout() {
            localStorageService.clearAll();
        }

        function getToken() {
            return localStorageService.get('token');
        }

        function currentUser() {
            if(!user) {
                localStorageService.get('user');
            }
            return user;
        }

        function currentUserCompany() {
            if(!user) {
                localStorageService.get('user');
            }
            return user.company;
        }
    }
})();
