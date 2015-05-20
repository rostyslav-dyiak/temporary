(function () {
    'use strict';

    app.controller('ViewProductController', ViewProductController);
    ViewProductController
        .$inject = [
        '$scope',
        '$stateParams',
        'toaster',
        'ProductFactory',
        'UnitFactory',
        'CategoryFactory',
        'SubCategoryFactory',
        'FileUploadService',
        'AuthServerProvider'
    ];

    function ViewProductController($scope, $stateParams, toaster, ProductFactory, UnitFactory, CategoryFactory, SubCategoryFactory, FileUploadService, AuthServerProvider) {
        $scope.productId = $stateParams.id;
        var code = AuthServerProvider.currentUserCompany().code;
        $scope.product = {
            code: code + randomString(4, '0123456789'),
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
            if ($scope.productId) {
                ProductFactory.get({
                        id: $scope.productId
                    }, function (data) {
                        $scope.product = data;
                        $scope.category = $scope.product.category;
                        $scope.subCategory = $scope.product.subCategory;
                        $scope.subSubCategory = $scope.product.subSubCategory;
                        /*loadSubCategories();
                         loadSubSubCategories();*/
                        $scope.unit = $scope.product.unit;
                    }, function (e) {
                        console.error(e);
                    }
                )
            }
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
                    id: $scope.product.subCategory.id
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
                code: code + randomString(4, '0123456789'),
                quantity: 0
            };
            $scope.generateCode = true;
        }

        function randomString(length, chars) {
            var result = '';
            for (var i = length; i > 0; --i) result += chars[Math.round(Math.random() * (chars.length - 1))];
            return result;
        }
    }

})
();
