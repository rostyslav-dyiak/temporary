(function() {
    'use strict';

    app.factory('ManageCompanyListFactory', ManageCompanyListFactory);

    ManageCompanyListFactory
        .$inject = [
        '$resource'
    ];

    function ManageCompanyListFactory($resource) {
        return $resource('api/manage-company-list.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
