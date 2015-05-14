(function () {
    'use strict';

    app.controller('ProductCategoryController', ProductCategoryController);
    ProductCategoryController
        .$inject = [
        '$scope',
        'toaster',
        'ProductUncategorizedFactory',
        'CategoryFactory'
    ];

    function ProductCategoryController($scope, toaster, ProductUncategorizedFactory, CategoryFactory) {
        $scope.products = [];
        $scope.allCategories = [];
        $scope.categories = [];
        $scope.subcategories = [];
        $scope.subsubcategories = [];
        $scope.showSubCategories = showSubCategories;
        $scope.showSubSubCategories = showSubSubCategories;
        $scope.uncategorized = 0;

        activate();

        function activate() {
            ProductUncategorizedFactory.query({},
                function (data) {
                    $scope.products = data;
                    $scope.uncategorized = $scope.products.length;
                    activateCategories();
                }, function (e) {
                    console.error(e);
                });
        }

        function activateCategories(){
            CategoryFactory.query({},
                function (data) {
                    for(var i = 0; i < data.length; i++){
                        var category = data[i];
                        var subCount = 0;
                        for(var j = 0; j < data.length; j++){
                            var subCategory = data[j];
                            if(subCategory.parentId != null && subCategory.parentId == category.id){
                                subCount++;
                            }
                        }
                        var dto = {category: category, count: subCount, clicked: false};
                        $scope.allCategories.push(dto);
                        if(category.parentId == null){
                            $scope.categories.push(dto);
                        }
                    }
                }, function (e) {
                    console.error(e);
                });
        }

        function showSubCategories(data) {
            $scope.subcategories = [];
            $scope.subsubcategories = [];
            for(var i = 0; i < $scope.categories.length; i++){
                $scope.categories[i].clicked = false;
            }
            for(var i = 0; i < $scope.allCategories.length; i++){
                var subCategory = $scope.allCategories[i];
                if(subCategory.category.parentId == data.category.id){
                    $scope.subcategories.push(subCategory);
                }
            }
            data.clicked = true;
        }

        function showSubSubCategories(data) {
            $scope.subsubcategories = [];
            for(var i = 0; i < $scope.subcategories.length; i++){
                $scope.subcategories[i].clicked = false;
            }
            data.clicked =true;
            for(var i = 0; i < $scope.allCategories.length; i++){
                var subCategory = $scope.allCategories[i];
                if(subCategory.category.parentId == data.category.id){
                    $scope.subsubcategories.push(subCategory);
                }
            }
        }
    }

})();
