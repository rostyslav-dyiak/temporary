'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('outlet', {
                parent: 'entity',
                url: '/outlet',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.outlet.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/outlet/outlets.html',
                        controller: 'OutletController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('outlet');
                        return $translate.refresh();
                    }]
                }
            })
            .state('outletDetail', {
                parent: 'entity',
                url: '/outlet/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.outlet.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/outlet/outlet-detail.html',
                        controller: 'OutletDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('outlet');
                        return $translate.refresh();
                    }]
                }
            });
    });
