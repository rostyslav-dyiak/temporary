(function () {

    'use strict';

    angular.module('app')
        .run(['$rootScope', '$state', '$stateParams', '$window', 'AuthServerProvider',
            function ($rootScope, $state, $stateParams, $window, AuthServerProvider) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
                $rootScope.$on('$stateChangeStart', function (event, next) {
                    var authorizedRoles = next.data.authorizedRoles;
                    if (!AuthServerProvider.hasRole(authorizedRoles)) {
                        event.preventDefault();
                        $window.location.href = '/index.html';
                    }
                });
            }
        ])
        .config(['$stateProvider', '$urlRouterProvider', 'USER_ROLES',
            function ($stateProvider, $urlRouterProvider, USER_ROLES) {

                $urlRouterProvider
                    .otherwise('/eatery/overview');
                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/eatery',
                        templateUrl: 'templates/app.html',
                        data: {
                            authorizedRoles: [USER_ROLES.eatery, USER_ROLES.eateryAdmin]
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
                        templateUrl: 'templates/store/store.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        '/js/directives/ng-enter.directive.js',
                                        '/js/filters/range.filter.js',
                                        'js/store/category.factory.js',
                                        'js/store/inquiry.factory.js',
                                        'js/store/outlets.factory.js',
                                        'js/store/supplier-for-category.factory.js',
                                        'js/store/supplier.category.factory.js',
                                        'js/store/supplier.product.factory.js',
                                        'js/store/store.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.store.list', {
                        url: '/list',
                        templateUrl: 'templates/store/store.list.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/store/store.list.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.store.supplier', {
                        url: '/supplier/:id',
                        templateUrl: 'templates/store/store.supplier.html',
                        controller: function ($stateParams) {
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/store/store.supplier.controller.js'
                                    ]);
                                }
                            ]
                        }
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
                                        '/js/services/file-upload.service.js',
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
                        templateUrl: 'templates/admin/inquiry/inquiry.html',
                        data: {
                            authorizedRoles: [USER_ROLES.eateryAdmin]
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/inquiry/company.factory.js',
                                        'js/inquiry/inquiry.factory.js',
                                        'js/inquiry/inquiry.reply.factory.js',
                                        'js/inquiry/inquiry.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.companyProfile', {
                        url: '/company_profile',
                        templateUrl: 'templates/admin/company_profile.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        '/js/services/file-upload.service.js',
                                        'js/company_profile/company.factory.js',
                                        'js/company_profile/outlets.factory.js',
                                        'js/company_profile/outlets.company.factory.js',
                                        'js/company_profile/company-profile.company.controller.js',
                                        'js/company_profile/company-profile.outlets.controller.js'
                                    ]);
                                }
                            ]
                        },
                        data: {
                            authorizedRoles: [USER_ROLES.eateryAdmin]
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
                        },
                        data: {
                            authorizedRoles: [USER_ROLES.eateryAdmin]
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
                                        'js/team_member/member.factory.js',
                                        'js/team_member/outlets.company.factory.js',
                                        'js/team_member/team-member-add-edit.controller.js'
                                    ]);
                                }
                            ]
                        },
                        data: {
                            authorizedRoles: [USER_ROLES.eateryAdmin]
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
                                        'js/team_member/outlets.company.factory.js',
                                        'js/team_member/member.factory.js',
                                        'js/team_member/team-member-add-edit.controller.js'
                                    ]);
                                }
                            ]
                        },
                        data: {
                            authorizedRoles: [USER_ROLES.eateryAdmin]
                        }
                    })
                    .state('app.activityLog', {
                        url: '/activity_log',
                        templateUrl: 'templates/activity_log.html',
                        data: {
                            authorizedRoles: [USER_ROLES.eateryAdmin]
                        }
                    });
            }
        ]);

})();
