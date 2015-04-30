(function() {
    'use strict';

    app.factory('OffDayFactory', OffDayFactory);

    OffDayFactory
        .$inject = [
        '$resource'
    ];

    function OffDayFactory($resource) {
        return $resource('/api/dayoffs/:id', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

})();
