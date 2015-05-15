(function () {
    'use strict';

    app.controller('StoreListController', StoreListController);

    StoreListController
        .$inject = [
        '$scope',
        'toaster',
        'CategoryFactory',
        'SupplierForCategoryFactory',
        'SupplierCategoryFactory',
        'SupplierProductFactory'
    ];

    function StoreListController($scope, toaster, CategoryFactory, SupplierForCategoryFactory, SupplierCategoryFactory, SupplierProductFactory) {
        $scope.categories = [];
        $scope.selectedCategory = [];
        $scope.selectedSuppliers = [];

        $scope.selectCategory = selectCategory;

        activate();

        function activate() {
            CategoryFactory.query({},
                function (data) {
                    $scope.categories = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function selectCategory(category) {
            $scope.selectedCategory = category;
            SupplierForCategoryFactory.query({
                id: category.id
            }, function (data) {
                $scope.selectedSuppliers = angular.copy(data);
                for (var i = 0; i < $scope.selectedSuppliers.length; i++) {
                    getSupplierCategories(i);
                    getSupplierProducts(i);
                }
            }, function (e) {
                console.error(e);
            });
        }

        function getSupplierCategories(supplierIndex) {
            SupplierCategoryFactory.query({
                id: $scope.selectedSuppliers[supplierIndex].id
            }, function (data) {
                $scope.selectedSuppliers[supplierIndex].categories = angular.copy(data);
            }, function (e) {
                console.error(e);
            });
        }

        function getSupplierProducts(supplierIndex) {
            SupplierProductFactory.query({
                id: $scope.selectedSuppliers[supplierIndex].id
            }, function (data) {
                $scope.selectedSuppliers[supplierIndex].products = angular.copy(data);
            }, function (e) {
                console.error(e);
            });
        }
    }
})();
