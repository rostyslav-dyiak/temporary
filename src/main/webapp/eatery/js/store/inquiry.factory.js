(function() {
    'use strict';

    app.factory('InquiryFactory', InquiryFactory);

    InquiryFactory
        .$inject = [
        '$resource'
    ];

    function InquiryFactory($resource) {
        return $resource('api/inquiries.json', {}, {
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
