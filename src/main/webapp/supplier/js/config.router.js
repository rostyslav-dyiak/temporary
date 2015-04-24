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
                    .otherwise('/supplier/overview');
                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/supplier',
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
                        templateUrl: 'templates/standing_order.html'
                    })
                    .state('app.paymentSchedule', {
                        url: '/payment_schedule',
                        templateUrl: 'templates/payment_schedule.html'
                    })
                    .state('app.deliverySchedule', {
                        url: '/delivery_schedule',
                        templateUrl: 'templates/delivery_schedule.html'
                    })
                    .state('app.customer', {
                        url: '/customer',
                        templateUrl: 'templates/customer.html'
                    })
                    .state('app.product', {
                        url: '/product',
                        templateUrl: 'templates/product.html'
                    })
                    .state('app.pricing', {
                        url: '/pricing',
                        templateUrl: 'templates/pricing.html'
                    })
                    .state('app.setting', {
                        url: '/setting',
                        templateUrl: 'templates/setting.html'
                    })
                    .state('app.personalProfile', {
                        url: '/personal_profile',
                        templateUrl: 'templates/personal_profile.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/directives/file-model.directive.js',
                                        'js/directives/click-selector.directive.js',
                                        'js/personal_profile/users.factory.js',
                                        'js/personal_profile/personal-profile.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.activityLog', {
                        url: '/activity_log',
                        templateUrl: 'templates/activity_log.html'
                    })
                    .state('app.dashboard', {
                        url: '/dashboard',
                        templateUrl: 'templates/admin/dashboard.html'
                    })
                    .state('app.inquiry', {
                        url: '/inquiry',
                        templateUrl: 'templates/admin/inquiry.html'
                    })
                    .state('app.companySettings', {
                        url: '/company_settings',
                        templateUrl: 'templates/admin/company_settings.html'
                    })
                    .state('app.teamMember', {
                        url: '/team_member',
                        templateUrl: 'templates/admin/team_member.html'
                    })

            }
        ]
    );
