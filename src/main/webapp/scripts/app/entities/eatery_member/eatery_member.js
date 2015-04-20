'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('eatery_member', {
                parent: 'entity',
                url: '/eatery_member',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.eatery_member.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/eatery_member/eatery_members.html',
                        controller: 'Eatery_memberController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('eatery_member');
                        return $translate.refresh();
                    }]
                }
            })
            .state('eatery_memberDetail', {
                parent: 'entity',
                url: '/eatery_member/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.eatery_member.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/eatery_member/eatery_member-detail.html',
                        controller: 'Eatery_memberDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('eatery_member');
                        return $translate.refresh();
                    }]
                }
            });
    });
