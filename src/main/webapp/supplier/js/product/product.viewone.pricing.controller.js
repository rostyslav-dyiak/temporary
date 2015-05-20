(function () {
    'use strict';

    app.controller('ViewPricingProductController', ViewPricingProductController);
    ViewPricingProductController
        .$inject = [
        '$scope',
        'toaster',
        'ProductFactory',
        'UnitFactory',
        'CategoryFactory',
        'SubCategoryFactory',
        'FileUploadService'
    ];

    function ViewPricingProductController($scope, toaster, ProductFactory, UnitFactory, CategoryFactory, SubCategoryFactory, FileUploadService) {
        $scope.product = {
            code: "ABCD-0001",
            quantity: 0
        };


        $scope.units = [];
        $scope.categories = [];
        $scope.subCategories = [];
        $scope.subSubCategories = [];
        $scope.generateCode = true;

        $scope.removeOtherName = removeOtherName;
        $scope.addOtherName = addOtherName;
        $scope.saveProduct = saveProduct;
        $scope.deleteProduct = deleteProduct;
        $scope.loadSubCategories = loadSubCategories;
        $scope.loadSubSubCategories = loadSubSubCategories;
        $scope.uploadLogo = uploadLogo;
        $scope.removeLogo = removeLogo;
        $scope.revertChanges = revertChanges;

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

        function loadSubCategories() {
            SubCategoryFactory.query({
                id: $scope.product.category.id
            }, function (data) {
                $scope.subCategories = data;
            }, function (e) {
                console.error(e);
            });
        }

        function loadSubSubCategories() {
            SubCategoryFactory.query({
                    id: $scope.product.subcategory.id
                }
                , function (data) {
                    $scope.subSubCategories = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function uploadLogo(image) {
            if (image.length > 0) {
                FileUploadService.uploadFileToUrl(image[0])
                    .then(function (response) {
                        $scope.product.picture = {
                            title: image[0].name,
                            url: response.data.path
                        };
                    });
            }
        }

        function removeLogo() {
            $scope.product.picture = {
                title: 'logo_placeholder',
                url: '/logo_placeholder.png'
            };
        }

        function revertChanges() {
            $scope.product = {
                code: "ABCD-0001",
                quantity: 0
            };
            $scope.generateCode = true;
        }
    }

})
();
