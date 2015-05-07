(function() {
    'use strict';

    app.factory('SystemAnnouncementFactory', SystemAnnouncementFactory);

    SystemAnnouncementFactory
        .$inject = [
        '$resource'
    ];

    function SystemAnnouncementFactory($resource) {
        return $resource('/api/messages/:id', {}, {
            'query': {
                method: 'GET'
            },
            'update': {
                method: 'PUT'
            }
        });
    }

})();
