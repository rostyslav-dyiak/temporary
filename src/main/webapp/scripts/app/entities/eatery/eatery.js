'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('eatery', {
                parent: 'entity',
                url: '/eatery',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.eatery.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/eatery/eaterys.html',
                        controller: 'EateryController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('eatery');
                        return $translate.refresh();
                    }]
                }
            })
            .state('eateryDetail', {
                parent: 'entity',
                url: '/eatery/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.eatery.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/eatery/eatery-detail.html',
                        controller: 'EateryDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('eatery');
                        return $translate.refresh();
                    }]
                }
            });
    });
