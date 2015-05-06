'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('systemAnnouncement', {
                parent: 'entity',
                url: '/systemAnnouncement',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.systemAnnouncement.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/systemAnnouncement/systemAnnouncements.html',
                        controller: 'SystemAnnouncementController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('systemAnnouncement');
                        return $translate.refresh();
                    }]
                }
            })
            .state('systemAnnouncementDetail', {
                parent: 'entity',
                url: '/systemAnnouncement/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.systemAnnouncement.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/systemAnnouncement/systemAnnouncement-detail.html',
                        controller: 'SystemAnnouncementDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('systemAnnouncement');
                        return $translate.refresh();
                    }]
                }
            });
    });
