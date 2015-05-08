(function() {
    'use strict';

    app.factory('SystemAnnouncementUserFactory', SystemAnnouncementUserFactory);

    SystemAnnouncementUserFactory
        .$inject = [
        '$resource'
    ];

    function SystemAnnouncementUserFactory($resource) {
        return $resource('/api/messages/:systemAnnouncementId/users/:id', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'update': {
                method: 'PUT'
            }
        });
    }
})();
