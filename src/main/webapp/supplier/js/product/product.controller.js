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
