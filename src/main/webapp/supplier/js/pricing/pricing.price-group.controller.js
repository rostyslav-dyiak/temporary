(function () {
    'use strict';

    app.controller('PricingPriceGroupController', PricingPriceGroupController);
    PricingPriceGroupController
        .$inject = [
        '$scope',
        'toaster',
        'PricingGroupFactory',
        'PricingEateryFactory'
    ];

    function PricingPriceGroupController($scope, toaster, PricingGroupFactory, PricingEateryFactory) {
        $scope.pricingGroups = [];
        $scope.rowCollection = [];
        $scope.eateryPricingGroup = [];
        $scope.eateryRowCollection = [];
        $scope.selectedPricingGroup = {};
        $scope.newGroup = false;

        $scope.selectPricingGroup = selectPricingGroup;
        $scope.addPricingGroup = addPricingGroup;
        $scope.deletePricingGroup = deletePricingGroup;
        $scope.savePricingGroup = savePricingGroup;
        $scope.revertChanges = revertChanges;

        activate();

        function activate() {
            PricingGroupFactory.query({},
                function (data) {
                    $scope.pricingGroups = data;
                    $scope.rowCollection = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function addPricingGroup() {
            $scope.selectedPricingGroup = {};
            $scope.newGroup = true;
        }

        function deletePricingGroup(id) {
            if($scope.eateryPricingGroup.length > 0) {
                toaster.pop('error', 'Error', 'You have customers for this group');
            } else {
                PricingGroupFactory.delete({
                    id: id
                },
                function () {
                    toaster.pop('success', 'Success', 'Pricing Group has been removed');
                    $scope.selectedPricingGroup = {};
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
            }
        }

        function selectPricingGroup(pricingGroup) {
            $scope.selectedPricingGroup = angular.copy(pricingGroup);
            PricingEateryFactory.query({
                id: pricingGroup.id
            }, function(data) {
                $scope.eateryPricingGroup = data;
                $scope.eateryRowCollection = data;
            }, function(e) {
                console.error(e);
            });
        }

        function savePricingGroup() {
            PricingGroupFactory.update($scope.selectedPricingGroup,
                function (data) {
                    toaster.pop('success', 'Success', 'Pricing Group has been saved');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function revertChanges() {
            $scope.selectedPricingGroup = {};
            $scope.newGroup = false;
        }
    }

})();
