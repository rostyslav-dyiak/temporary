(function () {
    'use strict';

    app.controller('CurrencyAddEditController', CurrencyAddEditController);

    CurrencyAddEditController
        .$inject = [
        '$scope',
        '$stateParams',
        'toaster',
        'CurrencyFactory'
    ];

    function CurrencyAddEditController($scope, $stateParams, toaster, CurrencyFactory) {
        var currencyId = $stateParams.id;

        $scope.currency = {};

        $scope.save = save;
        $scope.delete = deleteCurrency;

        activate();

        function activate() {
            if (currencyId) {
                CurrencyFactory.get({
                        id: currencyId
                    },
                    function (data) {
                        $scope.currency = data;
                    }, function (e) {
                        console.error(e);
                    });
            }
        }

        function save() {
            CurrencyFactory.save($scope.currency,
                function (data) {
                    $scope.currency = data;
                    toaster.pop('success', 'Success', 'Currency has been saved');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function deleteCurrency() {
            CurrencyFactory.delete({
                    id: $scope.currency.id
                },
                function () {
                    toaster.pop('success', 'Success', 'Currency has been deleted');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

    }
})();
