(function () {
    'use strict';

    app.controller('StoreSupplierController', StoreSupplierController);

    StoreSupplierController
        .$inject = [
        '$scope',
        '$stateParams',
        'toaster',
        'CategoryFactory',
        'SupplierForCategoryFactory',
        'SupplierCategoryFactory',
        'SupplierProductFactory'
    ];

    function StoreSupplierController($scope, $stateParams, toaster, CategoryFactory, SupplierForCategoryFactory, SupplierCategoryFactory, SupplierProductFactory) {
        var supplierId = $stateParams.id;

        $scope.categories = [];
        $scope.selectedProducts = [];
        $scope.selectedCategory = {};
        $scope.supplier = {};

        $scope.selectCategory = selectCategory;
        $scope.selectSubCategory = selectSubCategory;
        $scope.selectSubSubCategory = selectSubSubCategory;

        activate();

        function activate() {
            SupplierForCategoryFactory.query({
                    id: supplierId
                },
                function (data) {
                    $scope.supplier = angular.copy(data[0]);
                    console.log($scope.supplier);

                    SupplierCategoryFactory.query({
                        id: $scope.supplier.id
                    }, function (data) {
                        $scope.categories = angular.copy(data);
                    }, function (e) {
                        console.error(e);
                    });

                    SupplierProductFactory.query({
                        id: $scope.supplier.id
                    }, function (data) {
                        $scope.selectedProducts = angular.copy(data);
                    }, function (e) {
                        console.error(e);
                    });
                }, function (e) {
                    console.error(e);
                });

        }

        function selectCategory(category) {
            $scope.selectedCategory = category;
            $scope.selectedCategory.subcategory = {};
        }

        function selectSubCategory(subcategory) {
            $scope.selectedCategory.subcategory = subcategory;
            $scope.selectedCategory.subcategory.subsubcategory = {};
        }

        function selectSubSubCategory(subsubcategory) {
            $scope.selectedCategory.subcategory.subsubcategory = subsubcategory;

        }
    }
})();
