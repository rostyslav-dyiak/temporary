'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('businessType', {
                parent: 'entity',
                url: '/businessType',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.businessType.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/businessType/businessTypes.html',
                        controller: 'BusinessTypeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('businessType');
                        return $translate.refresh();
                    }]
                }
            })
            .state('businessTypeDetail', {
                parent: 'entity',
                url: '/businessType/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.businessType.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/businessType/businessType-detail.html',
                        controller: 'BusinessTypeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('businessType');
                        return $translate.refresh();
                    }]
                }
            });
    });
