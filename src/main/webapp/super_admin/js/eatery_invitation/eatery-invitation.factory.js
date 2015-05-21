(function () {
    'use strict';

    app.factory('EateryInvitationFactory', EateryInvitationFactory);

    EateryInvitationFactory
        .$inject = [
        '$resource'
    ];

    function EateryInvitationFactory($resource) {
        return $resource('api/eatery-invitation.json', {}, {
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
