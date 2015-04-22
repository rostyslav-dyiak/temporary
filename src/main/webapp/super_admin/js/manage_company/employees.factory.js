(function() {
    'use strict';

    app.factory('EmployeesFactory', EmployeesFactory);

    EmployeesFactory
        .$inject = [
        '$resource'
    ];

    function EmployeesFactory($resource) {
        return $resource('api/employees.json', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });
    }

})();
