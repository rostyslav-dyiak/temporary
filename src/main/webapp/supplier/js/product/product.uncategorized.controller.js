(function () {
    'use strict';

    app.controller('ProductUncategorizedController', ProductUncategorizedController);
    ProductUncategorizedController
        .$inject = [
        '$scope',
        'toaster',
        'ProductUncategorizedFactory',
        'CategoryFactory',
        'ProductFactory'
    ];

    function ProductUncategorizedController($scope, toaster, ProductUncategorizedFactory, CategoryFactory, ProductFactory) {
        $scope.products = [];
        $scope.rowCollection = [];
        $scope.allCategories = [];
        $scope.categories = [];
        $scope.selectCategory = selectCategory;
        $scope.selectSubCategory = selectSubCategory;
        $scope.selectSubSubCategory = selectSubSubCategory;
        $scope.mock = {id: 0, title: ''};

        activate();

        function activate() {
            $scope.products = [];
            $scope.rowCollection = [];
            $scope.allCategories = [];
            $scope.categories = [];
            ProductUncategorizedFactory.query({},
                function (data) {
                    $scope.products = data;
                    $scope.rowCollection = data;
                    injectCategories();
                }, function (e) {
                    console.error(e);
                });
        }

        function injectCategories(){
            CategoryFactory.query({},
                function (data) {
                    $scope.allCategories = data;
                    for(var i = 0; i < $scope.allCategories.length; i++){
                        if($scope.allCategories[i].parentId == null){
                            $scope.categories.push($scope.allCategories[i]);
                        }
                    }
                    for(var i=0; i<$scope.rowCollection.length; i++){
                        var product = $scope.rowCollection[i];
                        product.categoryIndex = null;
                        product.subCategoryIndex = null;
                        product.subSubCategoryIndex = null;
                        if(product.category != null){
                            product.listSubCategories =[];
                            for(var j = 0; j < $scope.allCategories.length; j++){
                                var subcategory = $scope.allCategories[j];
                                if(subcategory.parentId != null && product.category.id == subcategory.parentId){
                                    product.listSubCategories.push(subcategory);
                                    if(product.subCategory != null && product.subCategory.id == subcategory.id){
                                        product.listSubSubCategories = [];
                                        product.listSubSubCategories.push($scope.mock);
                                        for(var k = 0; k < $scope.allCategories.length; k++){
                                            var subSubCategory = $scope.allCategories[k];
                                            if(subSubCategory.parentId != null && subSubCategory.parentId == product.subCategory.id){
                                                product.listSubSubCategories.push(subSubCategory);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        $scope.rowCollection[i] = product;
                    }
                }, function (e) {
                    console.error(e);
                });
        }

        function selectCategory(row){
            var index = parseInt(row.categoryIndex);
            var category = $scope.categories[index];
            if(row.category == null || category.id != row.category.id){
                row.category = category;
                row.subCategory = null;
                row.subSubCategory = null;
                row.listSubCategories = [];
                row.listSubSubCategories = [];
                row.listSubCategories.push($scope.mock);
                for(var i = 0; i < $scope.allCategories.length; i++){
                    var subCategory = $scope.allCategories[i];
                    if(subCategory.parentId != null && subCategory.parentId == $scope.categories[index].id){
                        row.listSubCategories.push(subCategory);
                    }
                }
                updateProduct(row);
            }
        }

        function selectSubCategory(row){
            var index = parseInt(row.subCategoryIndex);
            var category = row.listSubCategories[index];
            if(row.subCategory == null || category.id != row.subCategory.id){
                row.subCategory = category;
                row.subSubCategory = null;
                row.listSubSubCategories = [];
                row.listSubSubCategories.push($scope.mock);
                for(var i = 0; i < $scope.allCategories.length; i++){
                    var subCategory = $scope.allCategories[i];
                    if(subCategory.parentId != null && subCategory.parentId == row.listSubCategories[index].id){
                        row.listSubSubCategories.push(subCategory);
                    }
                }
                updateProduct(row);
            }
        }

        function selectSubSubCategory(row){
            var index = parseInt(row.subSubCategoryIndex);
            var category = row.listSubSubCategories[index];
            if(category.id != 0){
                row.subSubCategory = category;
                updateProduct(row);
            }
        }

        function updateProduct(row){
            ProductFactory.update(
                {
                    available: row.available,
                    basePrice: row.basePrice,
                    brand: row.brand,
                    category: row.category,
                    certifiedHalal: row.certifiedHalal,
                    code: row.code,
                    codeGenerate: row.codeGenerate,
                    company: row.company,
                    createdBy: row.createdBy,
                    createdDate: row.createdDate,
                    description: row.description,
                    id: row.id,
                    lastModifiedBy: row.lastModifiedBy,
                    lastModifiedDate: row.lastModifiedDate,
                    origin: row.origin,
                    picture: row.picture,
                    productAliasSet: row.productAliasSet,
                    productPricingGroups: row.productPricingGroups,
                    quantity: row.quantity,
                    subCategory: row.subCategory,
                    subSubCategory: row.subSubCategory,
                    title: row.title,
                    unit: row.unit,
                    unitDescription: row.unitDescription,
                    unitHide: row.unitHide
                }
                , function (data) {
                    toaster.pop('success', 'Success', 'Product updated');
                    if(row.subSubCategory != null){
                        activate();
                    }
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }
    }

})();
