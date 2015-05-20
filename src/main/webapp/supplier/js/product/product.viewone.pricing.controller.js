(function () {
    'use strict';

    app.controller('ViewPricingProductController', ViewPricingProductController);
    ViewPricingProductController
        .$inject = [
        '$scope',
        'toaster',
        'PricingGroupFactory'
    ];

    function ViewPricingProductController($scope, toaster, PricingGroupFactory) {
        $scope.pricingGroups = [];
        $scope.rowCollection = [];
        $scope.editPrice = editPrice;
        activate();

        function activate() {
            PricingGroupFactory.query({},
                function (data) {
                    $scope.pricingGroups = data;
                }, function (e) {
                    console.error(e);
                }
            )
        }

        function editPrice(group) {
            disableAllInputs();
            group.editMode = true;
        }

        function disableAllInputs() {
            for (var i = 0; i < $scope.rowCollection.length; i++) {
                $scope.rowCollection[i].editMode = false;
            }
        }

    }

})
();
