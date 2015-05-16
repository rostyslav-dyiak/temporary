(function () {
    'use strict';

    app.controller('ViewProductController', ViewProductController);
    ViewProductController
        .$inject = [
        '$scope',
        'toaster',
        'ProductFactory'
    ];

    function ViewProductController($scope, toaster, ProductFactory) {
        $scope.product = {
            quantity: 1,
            productAliasSet: [
                {
                    name: 'first myName'
                },
                {
                    name: 'second myName'
                }
            ]
        };

        $scope.quantity1 = 3;
        $scope.products = [];

        $scope.removeOtherName = removeOtherName;
        $scope.addOtherName = addOtherName;

        activate();

        function activate() {
            //ProductFactory.query({},
            //    function (data) {
            //        $scope.products = data;
            //    }, function (e) {
            //        console.error(e);
            //    });
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
