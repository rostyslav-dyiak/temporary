'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('supplierDetails', {
                parent: 'entity',
                url: '/supplierDetails',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.supplierDetails.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/supplierDetails/supplierDetailss.html',
                        controller: 'SupplierDetailsController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('supplierDetails');
                        return $translate.refresh();
                    }]
                }
            })
            .state('supplierDetailsDetail', {
                parent: 'entity',
                url: '/supplierDetails/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.supplierDetails.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/supplierDetails/supplierDetails-detail.html',
                        controller: 'SupplierDetailsDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('supplierDetails');
                        return $translate.refresh();
                    }]
                }
            });
    });
