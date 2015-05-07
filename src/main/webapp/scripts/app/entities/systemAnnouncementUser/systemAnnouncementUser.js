'use strict';

angular.module('kbappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('systemAnnouncementUser', {
                parent: 'entity',
                url: '/systemAnnouncementUser',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.systemAnnouncementUser.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/systemAnnouncementUser/systemAnnouncementUsers.html',
                        controller: 'SystemAnnouncementUserController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('systemAnnouncementUser');
                        return $translate.refresh();
                    }]
                }
            })
            .state('systemAnnouncementUserDetail', {
                parent: 'entity',
                url: '/systemAnnouncementUser/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'kbappApp.systemAnnouncementUser.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/systemAnnouncementUser/systemAnnouncementUser-detail.html',
                        controller: 'SystemAnnouncementUserDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('systemAnnouncementUser');
                        return $translate.refresh();
                    }]
                }
            });
    });
