'use strict';

angular.module('kbappApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


