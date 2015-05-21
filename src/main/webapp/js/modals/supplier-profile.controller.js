(function () {
    'use strict';

    app.controller('EateryProfileController', EateryProfileController);

    EateryProfileController
        .$inject = [
        '$scope',
        '$modalInstance',
        'supplier',
        'SupplierCategoryFactory',
        'PopularProductsFactory'
    ];

    function EateryProfileController($scope, $modalInstance, supplier, SupplierCategoryFactory, PopularProductsFactory) {
        $scope.supplier = {};
        $scope.selectedProduct = {};
        $scope.popularProducts = [];

        $scope.openProduct = openProduct;
        $scope.dismiss = dismiss;

        activate();

        function activate() {
            $scope.supplier = supplier;
            SupplierCategoryFactory.query({
                id: $scope.supplier.id
            }, function (data) {
                $scope.supplier.categories = angular.copy(data);
            }, function (e) {
                console.error(e);
            });
            PopularProductsFactory.query({},
                function (data) {
                    $scope.popularProducts = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function openProduct(product) {
            $scope.selectedProduct = product;
        }

        function dismiss() {
            if($scope.selectedProduct.id) {
                $scope.selectedProduct = {};
            } else {
                $modalInstance.dismiss('cancel');
            }
        }
    }
})();
