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
        ['$stateProvider', '$urlRouterProvider',
            function ($stateProvider, $urlRouterProvider) {

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
                                        'js/auth/auth.xauth.service.js',
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
                                        'js/users.factory.js',
                                        'js/sign-up.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
            }
        ]
    );
})();
