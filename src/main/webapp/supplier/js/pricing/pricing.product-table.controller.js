(function () {
    'use strict';

    app.controller('PricingProductTableController', PricingProductTableController);
    PricingProductTableController
        .$inject = [
        '$scope',
        'toaster',
        'PricingGroupFactory',
        'PricingProductTableFactory'
    ];

    function PricingProductTableController($scope, toaster, PricingGroupFactory, PricingProductTableFactory) {
        $scope.productsPricing = [];
        $scope.pricingGroups = [];
        $scope.rowCollection = [];

        $scope.editBasePrice = editBasePrice;
        $scope.editPrice = editPrice;
        $scope.saveProduct = saveProduct;

        activate();

        function activate() {
            PricingProductTableFactory.query({},
                function (data) {
                    $scope.productsPricing = data;
                    $scope.rowCollection = data;
                }, function (e) {
                    console.error(e);
                });
            PricingGroupFactory.query({},
                function (data) {
                    $scope.pricingGroups = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function editPrice(group) {
            disableAllInputs();
            group.editMode = true;
        }

        function editBasePrice(product) {
            disableAllInputs();
            product.basePriceEditMode = true;
        }

        function saveProduct(product, group) {
            toggleSavingLabel(product, group);
            PricingProductTableFactory.update(product,
                function () {
                    disableAllInputs();
                    toaster.pop('success', 'Success', 'Product Price has been updated');
                    toggleSavingLabel(product, group);
                }, function (e) {
                    toaster.pop('error', 'Error', 'Saving Error');
                    console.error(e);
                    toggleSavingLabel(product, group);
                });
        }

        function toggleSavingLabel(product, group) {
            if (group) {
                group.saving = group.saving ? false : true;
            } else {
                product.saving = product.saving ? false : true;
            }
        }

        function disableAllInputs() {
            for (var i = 0; i < $scope.rowCollection.length; i++) {
                $scope.rowCollection[i].basePriceEditMode = false;
                for (var j = 0; j < $scope.rowCollection[i].pricingGroups.length; j++) {
                    $scope.rowCollection[i].pricingGroups[j].editMode = false;
                }
            }
        }
    }

})();
