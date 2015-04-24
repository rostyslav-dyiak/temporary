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
                    .otherwise('/super_admin/companies/list');
                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/super_admin',
                        templateUrl: 'templates/app.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load('js/directives/back-button.directive.js');
                                }
                            ]
                        }
                    })
                    .state('app.manageCompany', {
                        url: '/companies',
                        template: '<div ui-view></div>'
                    })
                    .state('app.manageCompany.add', {
                        url: '/add',
                        templateUrl: 'templates/manage_company/add_edit.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/directives/file-model.directive.js',
                                        'js/directives/click-selector.directive.js',
                                        'js/manage_company/company.factory.js',
                                        'js/manage_company/company.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.manageCompany.edit', {
                        url: '/edit/:id',
                        templateUrl: 'templates/manage_company/add_edit.html',
                        controller: function ($stateParams) {
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/directives/file-model.directive.js',
                                        'js/directives/click-selector.directive.js',
                                        'js/manage_company/company.factory.js',
                                        'js/manage_company/company.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.manageCompany.list', {
                        url: '/list',
                        templateUrl: 'templates/manage_company/list.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/manage_company/company.factory.js',
                                        'js/manage_company/company.list.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.manageCompany.outlets', {
                        url: '/:id/outlets',
                        templateUrl: 'templates/manage_company/customers.html',
                        controller: function ($stateParams) {
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/manage_company/company.outlets.factory.js',
                                        'js/manage_company/company.outlets.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.manageCompany.outletsAdd', {
                        url: '/:companyId/outlets/add',
                        templateUrl: 'templates/manage_company/outlets_add_edit.html',
                        controller: function ($stateParams) {
                            $stateParams.companyId
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/manage_company/outlets.factory.js',
                                        'js/manage_company/outlets.company.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.manageCompany.outletsEdit', {
                        url: '/:companyId/outlets/:id',
                        templateUrl: 'templates/manage_company/outlets_add_edit.html',
                        controller: function ($stateParams) {
                            $stateParams.companyId,
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/manage_company/outlets.factory.js',
                                        'js/manage_company/outlets.company.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.manageCompany.employees', {
                        url: '/:id/employees',
                        templateUrl: 'templates/manage_company/employees.html',
                        controller: function ($stateParams) {
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/manage_company/company.factory.js',
                                        'js/manage_company/company.employees.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.order', {
                        url: '/order',
                        templateUrl: 'templates/order.html'
                    })
                    .state('app.productType', {
                        url: '/product_type',
                        templateUrl: 'templates/product_type.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/product_type/product-type.factory.js',
                                        'js/product_type/product-type.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.taxType', {
                        url: '/tax_type',
                        templateUrl: 'templates/tax_type.html'
                    })
                    .state('app.publicHoliday', {
                        url: '/public_holiday',
                        templateUrl: 'templates/public_holiday.html'
                    })
                    .state('app.systemAnnouncement', {
                        url: '/system_announcement',
                        templateUrl: 'templates/system_announcement.html'
                    })
                    .state('app.businessType', {
                        url: '/business_type',
                        templateUrl: 'templates/business_type.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/business_type/business-type.factory.js',
                                        'js/business_type/business-type.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.unit', {
                        url: '/unit',
                        templateUrl: 'templates/unit.html'
                    })
                    .state('app.paymentTerms', {
                        url: '/payment_terms',
                        templateUrl: 'templates/payment_terms.html'
                    })
                    .state('app.eateryInvitation', {
                        url: '/eatery_invitation',
                        templateUrl: 'templates/eatery_invitation.html'
                    })
                    .state('app.standingOrderConvertion', {
                        url: '/standing_order_convertion',
                        templateUrl: 'templates/standing_order_convertion.html'
                    })
                    .state('app.currency', {
                        url: '/currency',
                        templateUrl: 'templates/currency.html'
                    })
                    .state('app.deliveryLocation', {
                        url: '/delivery_location',
                        templateUrl: 'templates/delivery_location.html'
                    })
            }
        ]
    );
})();
