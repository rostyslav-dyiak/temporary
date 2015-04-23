(function() {
    'use strict';

    app.factory('ContactPersonFactory', ContactPersonFactory);

    ContactPersonFactory
        .$inject = [
        '$resource'
    ];

    function ContactPersonFactory($resource) {
        return $resource('api/contact-persons.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
