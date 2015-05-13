(function () {
    'use strict';

    app.controller('ProductController', ProductController);
    ProductController
        .$inject = [
        '$scope',
        'toaster',
        'ProductFactory'
    ];

    function ProductController($scope, toaster, ProductFactory) {
        $scope.product = {};
        $scope.products = [];
        $scope.rowCollection = [];

        activate();

        function activate() {
            ProductFactory.query({},
                function (data) {
                    $scope.products = data;
                    $scope.rowCollection = data;
                }, function (e) {
                    console.error(e);
                });
        }
    }

})();
