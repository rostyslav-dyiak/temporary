(function () {
    'use strict';

    app.controller('ViewCustomersProductController', ViewCustomersProductController);
    ViewCustomersProductController
        .$inject = [
        '$scope',
        'toaster',
        'ProductCustomersFactory'
    ];

    function ViewCustomersProductController($scope, toaster, ProductCustomersFactory) {
        $scope.customers = [];
        $scope.rowCollection = [];
        activate();

        function activate() {
            ProductCustomersFactory.query({},
                function (data) {
                    $scope.customers = data;
                    $scope.rowCollection = data;
                }, function (e) {
                    console.error(e);
                }
            )
        }

    }

})
();
