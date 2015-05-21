(function() {
    'use strict';

    app.factory('InquiryFactory', InquiryFactory);

    InquiryFactory
        .$inject = [
        '$resource'
    ];

    function InquiryFactory($resource) {
        return $resource('/api/inquirys/last/:id', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'updateLast': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
