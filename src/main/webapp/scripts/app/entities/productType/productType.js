'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('productType', {
                parent: 'entity',
                url: '/productType',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.productType.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/productType/productTypes.html',
                        controller: 'ProductTypeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('productType');
                        return $translate.refresh();
                    }]
                }
            })
            .state('productTypeDetail', {
                parent: 'entity',
                url: '/productType/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.productType.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/productType/productType-detail.html',
                        controller: 'ProductTypeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('productType');
                        return $translate.refresh();
                    }]
                }
            });
    });
