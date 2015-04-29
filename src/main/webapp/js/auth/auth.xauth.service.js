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
                localStorageService.set('token', response);
                fetchUser().then(
                    function(data) {
                        user = data;
                    }
                );
                $http.get("/api/account")
                    .success(function (data) {
                        user = data;
                        def.resolve(data);
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
            return user;
        }

        function currentUserCompany() {
            return user.company;
        }

        function fetchUser() {
            var def = $q.defer();

            $http.get("/api/account")
                .success(function (data) {
                    user = data;
                    def.resolve(data);
                })
                .error(function () {
                    def.reject("Failed to get user");
                });
            return def.promise;
        }
    }
})();
