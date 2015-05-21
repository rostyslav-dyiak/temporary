(function() {
    'use strict';

    app.factory('InquiryReplyFactory', InquiryReplyFactory);

    InquiryReplyFactory
        .$inject = [
        '$resource'
    ];

    function InquiryReplyFactory($resource) {
        return $resource('api/inquiry-replies.json', {}, {
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
