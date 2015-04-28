'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('supplierDetailsPublicHolidays', {
                parent: 'entity',
                url: '/supplierDetailsPublicHolidays',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.supplierDetailsPublicHolidays.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/supplierDetailsPublicHolidays/supplierDetailsPublicHolidayss.html',
                        controller: 'SupplierDetailsPublicHolidaysController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('supplierDetailsPublicHolidays');
                        return $translate.refresh();
                    }]
                }
            })
            .state('supplierDetailsPublicHolidaysDetail', {
                parent: 'entity',
                url: '/supplierDetailsPublicHolidays/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.supplierDetailsPublicHolidays.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/supplierDetailsPublicHolidays/supplierDetailsPublicHolidays-detail.html',
                        controller: 'SupplierDetailsPublicHolidaysDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('supplierDetailsPublicHolidays');
                        return $translate.refresh();
                    }]
                }
            });
    });
