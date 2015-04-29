(function () {
    'use strict';

    angular.module('app')
        .run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]
    )
        .config(
        ['$stateProvider', '$urlRouterProvider', '$httpProvider',
            function ($stateProvider, $urlRouterProvider, $httpProvider) {

                $urlRouterProvider
                    .otherwise('/app/sign_in');
                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/app',
                        template: '<div ui-view></div>'
                    })
                    .state('app.signIn', {
                        url: '/sign_in',
                        templateUrl: 'templates/sign_in.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/account.factory.js',
                                        'js/sign-in.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.signUp', {
                        url: '/sign_up',
                        templateUrl: 'templates/sign_up.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/account.factory.js',
                                        'js/sign-up.controller.js'
                                    ]);
                                }
                            ]
                        }
                    });

                $httpProvider.interceptors.push(['$q', '$window', 'localStorageService', function ($q, $window, localStorageService) {
                    return {
                        'request': function (config) {
                            config.headers = config.headers || {};
                            var token = localStorageService.get('token');
                            if (token.token && token.expires > new Date().getTime()) {
                                config.headers['x-auth-token'] = token.token;
                            } else {
                                delete config.headers['x-auth-token'];
                            }
                            return config;
                        },
                        'responseError': function (response) {
                            if (response.status === 401 || response.status === 403) {
                                $window.location.href = '/index.html';
                            }
                            return $q.reject(response);
                        }
                    };
                }]);
            }
        ]
    );
})();
