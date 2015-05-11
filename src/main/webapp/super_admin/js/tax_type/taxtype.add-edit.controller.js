(function () {
    'use strict';

    app.controller('TaxTypeAddEditController', TaxTypeAddEditController);

    TaxTypeAddEditController
        .$inject = [
        '$scope',
        '$stateParams',
        'toaster',
        'TaxTypeFactory'
    ];

    function TaxTypeAddEditController($scope, $stateParams, toaster, TaxTypeFactory) {
        var taxType = $stateParams.id;

        $scope.taxType = {};

        $scope.save = save;
        $scope.delete = deleteCurrency;

        activate();

        function activate() {
            if (taxType) {
                TaxTypeFactory.get({
                        id: taxType
                    },
                    function (data) {
                        $scope.taxType = data;
                    }, function (e) {
                        console.error(e);
                    });
            }
        }

        function save() {
            $scope.taxType.calculationType = $scope.taxType.calculationType.value;
            TaxTypeFactory.update($scope.taxType,
                function (data) {
                    $scope.taxType = data;
                    toaster.pop('success', 'Success', 'Currency has been saved');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function deleteCurrency() {
            TaxTypeFactory.delete({
                    id: $scope.taxType.id
                },
                function () {
                    toaster.pop('success', 'Success', 'Currency has been deleted');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        $scope.calculationTypes = [
            {
                name: "Percentage of total amount",
                value: "PERCENTAGE_OF_TOTAL_AMOUNT"
            },
            {
                name: "Percentage of subtotal",
                value: "PERCENTAGE_OF_SUBTOTAL"
            },
            {
                name: "Percentage of grand total",
                value: "PERCENTAGE_OF_GRAND_TOTAL"
            },
        ];

    }
})();
