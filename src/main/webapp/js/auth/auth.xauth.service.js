(function () {

    'use strict';

    angular.module('app').factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider
        .$inject = [
        '$http',
        'localStorageService'
    ];

    function AuthServerProvider($http, localStorageService) {
        return {
            login: function (email, password) {
                var data = "email=" + email + "&password="
                    + password;
                return $http.post('api/authenticate', data, {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "Accept": "application/json"
                    }
                }).success(function (response) {
                    localStorageService.set('token', response);
                   // $http.defaults.headers.common["x-auth-token"]= response.token;
                    return response;
                });
            },
            logout: function () {
                localStorageService.clearAll();
            },
            getToken: function () {
                return localStorageService.get('token');
            },
            hasValidToken: function () {
                var token = this.getToken();
                return token && token.expires && token.expires > new Date().getTime();
            }
        };
    }

})();
