'use strict';

var app =
    angular.module('app')
        .config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
            function ($controllerProvider, $compileProvider, $filterProvider, $provide) {
                app.controller = $controllerProvider.register;
                app.directive = $compileProvider.directive;
                app.filter = $filterProvider.register;
                app.factory = $provide.factory;
                app.service = $provide.service;
                app.constant = $provide.constant;
                app.value = $provide.value;
            }
        ])
        .config(['$translateProvider', function ($translateProvider) {
            $translateProvider.useStaticFilesLoader({
                prefix: 'l10n/',
                suffix: '.js'
            });
            $translateProvider.preferredLanguage('en');
            $translateProvider.useLocalStorage();
        }])
        .config(['$httpProvider', function ($httpProvider) {
            $httpProvider.interceptors.push(['$q', '$window', 'localStorageService', function ($q, $window, localStorageService) {
                return {
                    'request': function (config) {
                        config.headers = config.headers || {};
                        var token = localStorageService.get('token');
                        if (token && token.expires > new Date().getTime()) {
                            config.headers['x-auth-token'] = token.token;
                        } else {
                            delete config.headers['x-auth-token'];
                        }
                        return config;
                    },
                    'responseError': function (response) {
                        if ($window.location.hash != "#/app/sign_in") {
                            if (response.status === 401 || response.status === 403) {
                                $window.location.href = '/index.html';
                            }
                        }
                        return $q.reject(response);

                    }
                };
            }]);
        }])
        .config(['stConfig', function(stConfig) {
            stConfig.pagination.template = '../templates/pagination.custom.html';
            stConfig.pagination.itemsByPage = 10;
        }]);


