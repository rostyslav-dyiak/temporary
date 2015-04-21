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
                        templateUrl: 'tpl/order_standing.html'
                    })
                    .state('app.payment', {
                        url: '/payment',
                        templateUrl: 'tpl/payment.html'
                    })
                    .state('app.store', {
                        url: '/store',
                        templateUrl: 'tpl/store.html'
                    })
                    .state('app.inventory', {
                        url: '/inventory',
                        templateUrl: 'tpl/inventory.html'
                    })
                    .state('app.suplier', {
                        url: '/suplier',
                        templateUrl: 'tpl/suplier.html'
                    })
                    .state('app.setting', {
                        url: '/setting',
                        templateUrl: 'tpl/setting.html'
                    })
                    .state('app.profile', {
                        url: '/profile',
                        templateUrl: 'tpl/profile.html'
                    })
                    .state('app.dashboard', {
                        url: '/dashboard',
                        templateUrl: 'tpl/admin/dashboard.html'
                    })
                    .state('app.inquiry', {
                        url: '/inquiry',
                        templateUrl: 'tpl/admin/inquiry.html'
                    })
                    .state('app.companyProfile', {
                        url: '/company_profile',
                        templateUrl: 'tpl/admin/company_profile.html'
                    })
                    .state('app.teamMember', {
                        url: '/team_member',
                        templateUrl: 'tpl/admin/team_member.html'
                    })
                    .state('app.activityLog', {
                        url: '/activity_log',
                        templateUrl: 'tpl/admin/activity_log.html'
                    })

            }
        ]
    );
