(function () {
    'use strict';

    app.controller('PricingPriceGroupController', PricingPriceGroupController);
    PricingPriceGroupController
        .$inject = [
        '$scope',
        'toaster',
        'PricingGroupFactory'
    ];

    function PricingPriceGroupController($scope, toaster, PricingGroupFactory) {
        $scope.pricingGroups = [];

        activate();

        function activate() {
            PricingGroupFactory.query({},
                function (data) {
                    $scope.pricingGroups = data;
                }, function (e) {
                    console.error(e);
                });
        }


    }

})();
