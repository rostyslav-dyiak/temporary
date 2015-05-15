(function () {
    'use strict';

    app.controller('ViewProductController', ViewProductController);
    ViewProductController
        .$inject = [
        '$scope',
        'toaster',
        'ProductFactory'
    ];

    function ViewProductController($scope, toaster, ProductFactory) {
        $scope.product = {};
        $scope.products = [];

        activate();

        function activate() {
            ProductFactory.query({},
                function (data) {
                    $scope.products = data;
                }, function (e) {
                    console.error(e);
                });
        }
    }

})();
