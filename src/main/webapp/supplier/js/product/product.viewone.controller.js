(function () {
    'use strict';

    app.controller('ViewProductController', ViewProductController);
    ViewProductController
        .$inject = [
        '$scope',
        'toaster',
        'ProductFactory',
        'UnitFactory',
        'CategoryFactory'
    ];

    function ViewProductController($scope, toaster, ProductFactory, UnitFactory, CategoryFactory) {
        $scope.product = {
            quantity: 0
        };


        $scope.units = [];
        $scope.categories = [];

        $scope.removeOtherName = removeOtherName;
        $scope.addOtherName = addOtherName;
        $scope.saveProduct = saveProduct;
        $scope.deleteProduct = deleteProduct;

        activate();

        function activate() {
            UnitFactory.query({},
                function (data) {
                    $scope.units = data;
                }, function (e) {
                    console.error(e);
                }
            )
            CategoryFactory.query({},
                function (data) {
                    data.forEach(function (category) {
                        if (category.parentId == null) {
                            $scope.categories.push(category);
                        }
                    });
                }, function (e) {
                    console.error(e);
                }
            )
        }

        function saveProduct() {
            ProductFactory.update(
                $scope.product
                , function (data) {
                    toaster.pop('success', 'Success', 'Product saved');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function deleteProduct() {
            ProductFactory.delete(
                $scope.product
                , function (data) {
                    toaster.pop('success', 'Success', 'Product deleted');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function removeOtherName(name) {
            var index = $scope.product.productAliasSet.indexOf(name);
            $scope.product.productAliasSet.splice(index, 1);
        }

        function addOtherName() {
            if (!$scope.product.productAliasSet) {
                $scope.product.productAliasSet = [];
            }
            $scope.product.productAliasSet.push('');
        }
    }

})
();
