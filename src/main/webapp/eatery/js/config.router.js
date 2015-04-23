'use strict';

/**
 * Config for the router
 */
angular.module('app')
    .run(
        ['$rootScope', '$state', '$stateParams',
            function($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]
    )
    .config(
        ['$stateProvider', '$urlRouterProvider', 'JQ_CONFIG',
            function($stateProvider, $urlRouterProvider, JQ_CONFIG) {

                $urlRouterProvider
                    .otherwise('/eatery/overview');
                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/eatery',
                        templateUrl: 'templates/app.html'
                    })
                    .state('app.overview', {
                        url: '/overview',
                        templateUrl: 'templates/overview.html'
                    })
                    .state('app.order', {
                        url: '/order',
                        templateUrl: 'templates/order.html'
                    })
                    .state('app.standingOrder', {
                        url: '/standing_order',
                        templateUrl: 'templates/order_standing.html'
                    })
                    .state('app.payment', {
                        url: '/payment',
                        templateUrl: 'templates/payment.html'
                    })
                    .state('app.store', {
                        url: '/store',
                        templateUrl: 'templates/store.html'
                    })
                    .state('app.inventory', {
                        url: '/inventory',
                        templateUrl: 'templates/inventory.html'
                    })
                    .state('app.supplier', {
                        url: '/supplier',
                        templateUrl: 'templates/supplier.html'
                    })
                    .state('app.setting', {
                        url: '/setting',
                        templateUrl: 'templates/setting.html'
                    })
                    .state('app.profile', {
                        url: '/profile',
                        templateUrl: 'templates/profile.html'
                    })
                    .state('app.dashboard', {
                        url: '/dashboard',
                        templateUrl: 'templates/admin/dashboard.html'
                    })
                    .state('app.inquiry', {
                        url: '/inquiry',
                        templateUrl: 'templates/admin/inquiry.html'
                    })
                    .state('app.companyProfile', {
                        url: '/company_profile',
                        templateUrl: 'templates/admin/company_profile.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/directives/file-model.directive.js',
                                        'js/directives/click-selector.directive.js',
                                        'js/company_profile/companies.factory.js',
                                        'js/company_profile/contact-person.factory.js',
                                        'js/company_profile/company-profile.company.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.teamMember', {
                        url: '/team_member',
                        templateUrl: 'templates/admin/team_member.html'
                    })
                    .state('app.activityLog', {
                        url: '/activity_log',
                        templateUrl: 'templates/admin/activity_log.html'
                    })

            }
        ]
    );
