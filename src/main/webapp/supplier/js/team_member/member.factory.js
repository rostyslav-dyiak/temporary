(function () {
    'use strict';

    app.factory('MemberFactory', MemberFactory);

    MemberFactory
        .$inject = [
        '$resource'
    ];

    function MemberFactory($resource) {
        return $resource('/api/companies/users', {}, {
            'update': {
                method: 'PUT'
            }
        });
    }
})();
