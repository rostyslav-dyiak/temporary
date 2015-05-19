(function () {
    'use strict';

    app.controller('StoreController', StoreController);

    StoreController
        .$inject = [
        '$scope'
    ];

    function StoreController($scope) {
        $scope.searchQuery = '';
        $scope.cartDialogOpened = false;
        $scope.cartItems = 3;

        $scope.toggleDialog = toggleDialog;
        $scope.search = search;
        $scope.addToCart = addToCart;

        function search() {
            // $scope.searchQuery
            console.log("Sending request... " + $scope.searchQuery);
        }

        function toggleDialog() {
            $scope.cartDialogOpened = $scope.cartDialogOpened ? false : true;
        }

        function addToCart(product) {
            console.log(product.title + " have been added to cart");
            $scope.cartItems++;
        }
    }
})();
