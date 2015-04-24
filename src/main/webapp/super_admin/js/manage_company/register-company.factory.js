/**
 * Created by rdyyak on 24.04.15.
 */
(function() {
    'use strict';

    app.factory('RegisterCompany', RegisterCompany);

    RegisterCompany
        .$inject = [
        '$resource'
    ];

    function RegisterCompany($resource) {
        return $resource('api/register', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
