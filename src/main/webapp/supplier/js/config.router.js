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
                        templateUrl: 'tpl/app.html'
                    })
                    .state('app.overview', {
                        url: '/overview',
                        templateUrl: 'tpl/overview.html'
                    })
                    .state('app.order', {
                        url: '/order',
                        templateUrl: 'tpl/order.html'
                    })
                    .state('app.standingOrder', {
                        url: '/standing_order',
                        templateUrl: 'tpl/standing_order.html'
                    })
                    .state('app.paymentSchedule', {
                        url: '/payment_schedule',
                        templateUrl: 'tpl/payment_schedule.html'
                    })
                    .state('app.deliverySchedule', {
                        url: '/delivery_schedule',
                        templateUrl: 'tpl/delivery_schedule.html'
                    })
                    .state('app.customer', {
                        url: '/customer',
                        templateUrl: 'tpl/customer.html'
                    })
                    .state('app.product', {
                        url: '/product',
                        templateUrl: 'tpl/product.html'
                    })
                    .state('app.pricing', {
                        url: '/pricing',
                        templateUrl: 'tpl/pricing.html'
                    })
                    .state('app.setting', {
                        url: '/setting',
                        templateUrl: 'tpl/setting.html'
                    })
                    .state('app.personalProfile', {
                        url: '/personal_profile',
                        templateUrl: 'tpl/personal_profile.html'
                    })
                    .state('app.activityLog', {
                        url: '/activity_log',
                        templateUrl: 'tpl/activity_log.html'
                    })
                    .state('app.dashboard', {
                        url: '/dashboard',
                        templateUrl: 'tpl/admin/dashboard.html'
                    })
                    .state('app.inquiry', {
                        url: '/inquiry',
                        templateUrl: 'tpl/admin/inquiry.html'
                    })
                    .state('app.companySettings', {
                        url: '/company_settings',
                        templateUrl: 'tpl/admin/company_settings.html'
                    })
                    .state('app.profileSettings', {
                        url: '/profile_settings',
                        templateUrl: 'tpl/admin/profile_settings.html'
                    })
                    .state('app.teamMember', {
                        url: '/team_member',
                        templateUrl: 'tpl/admin/team_member.html'
                    })

            }
        ]
    );
