(function () {
    'use strict';

    app.controller('ProductInfoController', ProductInfoController);

    ProductInfoController
        .$inject = [
        '$scope',
        '$modalInstance',
        'product'
    ];

    function ProductInfoController($scope, $modalInstance, product) {
        $scope.product = {};

        $scope.dismiss = dismiss;

        activate();

        function activate() {
            $scope.product = product;
            $scope.product.names = $scope.product.title;
            for (var i = 0; i < $scope.product.productAliasSet.length; i++) {
                $scope.product.names += ', ' + $scope.product.productAliasSet[i];
            }
            $scope.product.unitSymbol = $scope.product.unit.unitSymbol;
            if ($scope.product.unit.equivalentQuantity != 0) {
                $scope.product.unitSymbol += '(' + $scope.product.unit.equivalentQuantity + ')';
            }
            if ($scope.product.productPricingGroups.length > 0) {
                $scope.product.price = $scope.product.productPricingGroups[0];
            } else if ($scope.product.unitHide) {
                $scope.product.price = $scope.product.basePrice;
            }
        }

        function dismiss() {
            $modalInstance.dismiss('cancel');
        }
    }
})();
