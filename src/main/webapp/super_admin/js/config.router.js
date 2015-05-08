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
                    .otherwise('/super_admin/companies/list');
                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/super_admin',
                        templateUrl: 'templates/app.html',
                        data: {
                            authorizedRoles: [USER_ROLES.superAdmin]
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
                                        'js/business_type/business-type.factory.js',
                                        'js/manage_company/company.add-edit.controller.js',
                                        'js/services/file-upload.service.js'
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
                                        'js/services/file-upload.service.js',
                                        'js/manage_company/company.factory.js',
                                        'js/business_type/business-type.factory.js',
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
                        url: '/:companyId/outlets',
                        templateUrl: 'templates/manage_company/outlets.html',
                        controller: function ($stateParams) {
                            $stateParams.companyId
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
                                        'js/manage_company/outlet.add-edit.controller.js'
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
                                        'js/manage_company/outlet.add-edit.controller.js'
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
                                        'js/manage_company/company.users.factory.js',
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
                        templateUrl: 'templates/public_holiday/public_holiday.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/public_holiday/public-holiday.factory.js',
                                        'js/public_holiday/public-holiday.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.publicHolidayAdd', {
                        url: '/public_holiday/add',
                        templateUrl: 'templates/public_holiday/public_holiday_add_edit.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        '/js/directives/click-selector.directive.js',
                                        'js/public_holiday/public-holiday.factory.js',
                                        'js/public_holiday/public-holiday.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.publicHolidayEdit', {
                        url: '/public_holiday/:id',
                        templateUrl: 'templates/public_holiday/public_holiday_add_edit.html',
                        controller: function ($stateParams) {
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/public_holiday/public-holiday.factory.js',
                                        'js/public_holiday/public-holiday.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.systemAnnouncement', {
                        url: '/system_announcement',
                        templateUrl: 'templates/system_announcement/system_announcement.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/system_announcement/system-announcement.factory.js',
                                        'js/system_announcement/system-announcement.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.systemAnnouncementAdd', {
                        url: '/system_announcement/add',
                        templateUrl: 'templates/system_announcement/system_announcement_add_edit.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/system_announcement/user.factory.js',
                                        'js/system_announcement/system-announcement.user.factory.js',
                                        'js/system_announcement/system-announcement.factory.js',
                                        'js/system_announcement/system-announcement.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.systemAnnouncementEdit', {
                        url: '/system_announcement/:id',
                        templateUrl: 'templates/system_announcement/system_announcement_add_edit.html',
                        controller: function ($stateParams) {
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/system_announcement/user.factory.js',
                                        'js/system_announcement/system-announcement.factory.js',
                                        'js/system_announcement/system-announcement.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
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
                        templateUrl: 'templates/payment_terms.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        '/js/filters/range.filter.js',
                                        'js/payment_term/payment.factory.js',
                                        'js/payment_term/payment-term.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.eateryInvitation', {
                        url: '/eatery_invitation',
                        templateUrl: 'templates/eatery_invitation.html'
                    })
                    .state('app.standingOrderConversion', {
                        url: '/standing_order_conversion',
                        templateUrl: 'templates/standing_order_conversion.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        '/js/filters/range.filter.js',
                                        'js/standing_order/standing-order.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.currency', {
                        url: '/currency',
                        templateUrl: 'templates/currency/currency.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/currency/currency.factory.js',
                                        'js/currency/currency.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.currencyAdd', {
                        url: '/currency/add',
                        templateUrl: 'templates/currency/currency.add-edit.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/currency/currency.factory.js',
                                        'js/currency/currency.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.currencyEdit', {
                        url: '/currency/:id/edit',
                        templateUrl: 'templates/currency/currency.add-edit.html',
                        controller: function ($stateParams) {
                            $stateParams.id
                        },
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/currency/currency.factory.js',
                                        'js/currency/currency.add-edit.controller.js'
                                    ]);
                                }
                            ]
                        }
                    })
                    .state('app.deliveryLocation', {
                        url: '/delivery_location',
                        templateUrl: 'templates/delivery_location.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'js/delivery_location/district.factory.js',
                                        'js/delivery_location/delivery-location.controller.js'
                                    ]);
                                }
                            ]
                        }
                    });
            }]);
})();
