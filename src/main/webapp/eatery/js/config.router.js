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
        ['$stateProvider', '$urlRouterProvider',
            function($stateProvider, $urlRouterProvider) {

                $urlRouterProvider
                    .otherwise('/eatery/overview');
                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/eatery',
                        templateUrl: 'templates/app.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load('js/directives/back-button.directive.js');
                                }
                            ]
                        }
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
                    .state('app.personalProfile', {
                        url: '/profile',
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
                                        'js/company_profile/company.outlets.factory.js',
                                        'js/company_profile/contact-person.factory.js',
                                        'js/company_profile/company-profile.company.controller.js',
                                        'js/company_profile/company-profile.outlets.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.teamMember', {
                        url: '/team_member',
                        templateUrl: 'templates/admin/team_member/team_members.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/team_member/team.factory.js',
                                        'js/team_member/team-member.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.teamMemberAdd', {
                        url: '/add',
                        templateUrl: 'templates/admin/team_member/team_member_add_edit.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/team_member/team.factory.js',
                                        'js/company_profile/company.outlets.factory.js',
                                        'js/team_member/team-member-company.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.teamMemberEdit', {
                        url: '/edit/:id',
                        templateUrl: 'templates/admin/team_member/team_member_add_edit.html',
                        controller: function ($stateParams) {
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/team_member/team.factory.js',
                                        'js/company_profile/company.outlets.factory.js',
                                        'js/team_member/team-member-company.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.activityLog', {
                        url: '/activity_log',
                        templateUrl: 'templates/admin/activity_log.html'
                    })

            }
        ]
    );
