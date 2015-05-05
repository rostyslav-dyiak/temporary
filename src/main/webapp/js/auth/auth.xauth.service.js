(function () {

    'use strict';

    angular.module('app').factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider.$inject = [
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
            currentUserCompany: currentUserCompany,
            userRole: userRole,
            hasRole: hasRole,
            updateUserInfo: updateUserInfo
        };

        return service;

        function login(email, password) {
            var data = "email=" + email + "&password="
                + password;

            return $http.post('/api/authenticate', data, {
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
            var newUser = localStorageService.get('user');
            if (!user || user != newUser) {
                user = newUser;
            }
            return user;
        }

        function currentUserCompany() {
            return currentUser().company;
        }

        function userRole() {
            return currentUser().role;
        }

        function updateUserInfo() {
            return $http.get("/api/account")
                .success(function (data) {
                    localStorageService.set('user', data)
                    return data;
                })
        }

        function hasRole(authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                authorizedRoles = [authorizedRoles];
            }
            return currentUser() && authorizedRoles.indexOf(userRole()) !== -1;
        }
    }
})();
