'use strict';
app.factory('AuthServerProvider', AuthServerProvider);
    AuthServerProvider
        .$inject = [
        '$http',
        'localStorageService'
    ];
function AuthServerProvider($http,localStorageService){
        return {
            login: function(email,password) {
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
            },
            logout: function() {
                //Stateless API : No server logout
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
    };
