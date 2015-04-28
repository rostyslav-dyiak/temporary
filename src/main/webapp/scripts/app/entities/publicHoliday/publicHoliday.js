'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('publicHoliday', {
                parent: 'entity',
                url: '/publicHoliday',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.publicHoliday.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/publicHoliday/publicHolidays.html',
                        controller: 'PublicHolidayController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('publicHoliday');
                        return $translate.refresh();
                    }]
                }
            })
            .state('publicHolidayDetail', {
                parent: 'entity',
                url: '/publicHoliday/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.publicHoliday.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/publicHoliday/publicHoliday-detail.html',
                        controller: 'PublicHolidayDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('publicHoliday');
                        return $translate.refresh();
                    }]
                }
            });
    });
