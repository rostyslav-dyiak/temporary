(function() {
    'use strict';

    app.controller('TeamMemberCustomersController', TeamMemberCustomersController);
    TeamMemberCustomersController
        .$inject = [
        '$scope',
        'CustomersFactory'
    ];

    function TeamMemberCustomersController($scope, CustomersFactory) {
        $scope.customers = [];
        $scope.rowCollection = [];

        $scope.itemsByPage = 5;

        activate();
        function activate() {
            CustomersFactory.get({},
                function (data) {
                    $scope.customers = data.customers;
                    $scope.rowCollection = data.customers;
                }, function (e) {
                    console.error(e);
                });
        }
    }

})();
