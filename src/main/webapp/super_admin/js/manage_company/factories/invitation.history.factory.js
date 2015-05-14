(function () {
    'use strict';

    app.factory('InvitationHistoryFactory', InvitationHistoryFactory);

    InvitationHistoryFactory
        .$inject = [
        '$resource'
    ];

    function InvitationHistoryFactory($resource) {
        return $resource('/api/invitations/company/:id', {}, {
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


