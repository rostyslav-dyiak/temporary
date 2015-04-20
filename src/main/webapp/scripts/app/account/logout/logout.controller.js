'use strict';

angular.module('kbappApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
