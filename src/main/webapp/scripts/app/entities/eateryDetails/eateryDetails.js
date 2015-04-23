'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('eateryDetails', {
                parent: 'entity',
                url: '/eateryDetails',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.eateryDetails.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/eateryDetails/eateryDetailss.html',
                        controller: 'EateryDetailsController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('eateryDetails');
                        return $translate.refresh();
                    }]
                }
            })
            .state('eateryDetailsDetail', {
                parent: 'entity',
                url: '/eateryDetails/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.eateryDetails.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/eateryDetails/eateryDetails-detail.html',
                        controller: 'EateryDetailsDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('eateryDetails');
                        return $translate.refresh();
                    }]
                }
            });
    });
