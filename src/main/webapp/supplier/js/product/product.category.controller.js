(function () {
    'use strict';

    app.controller('ProductCategoryController', ProductCategoryController);
    ProductCategoryController
        .$inject = [
        '$scope',
        'toaster',
        'ProductUncategorizedFactory',
        'CategoryFactory',
        'AuthServerProvider'
    ];

    function ProductCategoryController($scope, toaster, ProductUncategorizedFactory, CategoryFactory, AuthServerProvider) {
        $scope.products = [];
        $scope.allCategories = [];
        $scope.categories = [];
        $scope.subcategories = [];
        $scope.subsubcategories = [];
        $scope.showSubCategories = showSubCategories;
        $scope.showSubSubCategories = showSubSubCategories;
        $scope.uncategorized = 0;
        $scope.isSuperAdmin = false;
        $scope.addNewCat = false;
        $scope.addNewCategory = addNewCategory;
        $scope.saveCategory = saveCategory;
        $scope.discardNewCategory= discardNewCategory;
        $scope.addNewSub = false;
        $scope.addNewSubCategory = addNewSubCategory;
        $scope.saveSubCategory = saveSubCategory;
        $scope.discardNewSubCategory= discardNewSubCategory;
        $scope.addNewSubSub = false;
        $scope.addNewSubSubCategory = addNewSubSubCategory;
        $scope.saveSubSubCategory = saveSubSubCategory;
        $scope.discardNewSubSubCategory= discardNewSubSubCategory;
        $scope.categorySelected = null;
        $scope.subCategorySelected = null;
        $scope.category = null;
        $scope.subcategory = null;
        $scope.subsubcategory = null;
        $scope.editCat = false;
        $scope.editSub = false;
        $scope.editSubSub = false;
        $scope.editCategory = editCategory;
        $scope.editSubCategory = editSubCategory;
        $scope.editSubSubCategory = editSubSubCategory;
        $scope.edited = null;
        $scope.update = update;
        $scope.cancel = cancel;
        $scope.deleted = deleted;

        activate();

        function activate() {
            $scope.addNewCat = false;
            $scope.addNewSub = false;
            $scope.addNewSubSub = false;
            $scope.isSuperAdmin = AuthServerProvider.hasRole('ROLE_SUPPLIER_ADMIN');
            $scope.categorySelected = null;
            $scope.subCategorySelected = null;
            $scope.category = null;
            $scope.subcategory = null;
            $scope.subsubcategory = null;
            $scope.editCat = false;
            $scope.editSub = false;
            $scope.editSubSub = false;
            $scope.allCategories = [];
            $scope.categories = [];
            $scope.subcategories = [];
            $scope.subsubcategories = [];
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
            $scope.categorySelected = data;
            $scope.subCategorySelected = null;
            $scope.subcategories = [];
            $scope.subsubcategories = [];
            $scope.editSub = false;
            $scope.editSubSub = false;
            discardNewSubCategory();
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
            $scope.subCategorySelected = data;
            $scope.subsubcategories = [];
            $scope.editCat = false;
            $scope.editSubSub = false;
            discardNewSubSubCategory();
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

        function addNewCategory(){
            $scope.editCat = false;
            $scope.editSub = false;
            $scope.editSubSub = false;
            $scope.addNewCat = true;
        }

        function saveCategory(){
            CategoryFactory.update(
                {
                    createdBy: null,
                    createdDate: null,
                    id: 0,
                    lastModifiedBy: null,
                    lastModifiedDate: null,
                    parentId: null,
                    title: $scope.category
                }
                , function (data) {
                    toaster.pop('success', 'Success', 'Category saved');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }
        function discardNewCategory(){
            $scope.addNewCat = false;
        }

        function addNewSubCategory(){
            $scope.editCat = false;
            $scope.editSub = false;
            $scope.editSubSub = false;
            $scope.addNewSub = true;
        }

        function saveSubCategory(){
            CategoryFactory.update(
                {
                    createdBy: null,
                    createdDate: null,
                    id: 0,
                    lastModifiedBy: null,
                    lastModifiedDate: null,
                    parentId: $scope.categorySelected.category.id,
                    title: $scope.subcategory
                }
                , function (data) {
                    toaster.pop('success', 'Success', 'Category saved');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }
        function discardNewSubCategory(){
            $scope.addNewSub = false;
            $scope.subcategory = null;
        }

        function addNewSubSubCategory(){
            $scope.editCat = false;
            $scope.editSub = false;
            $scope.editSubSub = false;
            $scope.addNewSubSub = true;
        }

        function saveSubSubCategory(){
            CategoryFactory.update(
                {
                    createdBy: null,
                    createdDate: null,
                    id: 0,
                    lastModifiedBy: null,
                    lastModifiedDate: null,
                    parentId: $scope.subCategorySelected.category.id,
                    title: $scope.subsubcategory
                }
                , function (data) {
                    toaster.pop('success', 'Success', 'Category saved');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function discardNewSubSubCategory(){
            $scope.addNewSubSub = false;
            $scope.subsubcategory = null;
        }

        function editCategory(data){
            $scope.editCat = true;
            $scope.editSub = false;
            $scope.editSubSub = false;
            $scope.edited = data.category;
            discardNewCategory();
            discardNewSubCategory();
            discardNewSubSubCategory();
        }

        function editSubCategory(data){
            $scope.editSub = true;
            $scope.editCat = false;
            $scope.editSubSub = false;
            $scope.edited = data.category;
            discardNewCategory();
            discardNewSubCategory();
            discardNewSubSubCategory();
        }

        function editSubSubCategory(data){
            $scope.editCat = false;
            $scope.editSub = false;
            $scope.editSubSub = true;
            $scope.edited = data.category;
            discardNewCategory();
            discardNewSubCategory();
            discardNewSubSubCategory();
        }

        function update(){
            CategoryFactory.update(
                $scope.edited
                , function (data) {
                    toaster.pop('success', 'Success', 'Category updated');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function cancel() {
            CategoryFactory.get({
                    id: $scope.edited.id
                },
                function (data) {
                    for(var i = 0; i < $scope.allCategories.length; i++){
                        var category = $scope.allCategories[i];
                        if(category.category.id == $scope.edited.id)
                        {
                            $scope.allCategories[i].category = data;
                            $scope.edited = $scope.allCategories[i].category;
                        }
                    }

                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function deleted() {
            CategoryFactory.deleted({
                    id: $scope.edited.id
                },
                function (data) {
                    toaster.pop('success', 'Success', 'Category deleted');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

    }

})();
