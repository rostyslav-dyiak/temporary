(function () {
    'use strict';

    app.controller('CurrencyController', CurrencyController);

    CurrencyController
        .$inject = [
        '$scope',
        'toaster',
        'CurrencyFactory'
    ];

    function CurrencyController($scope, toaster, CurrencyFactory) {
        $scope.currencies = [];

        $scope.delete = deleteCurrency;

        activate();

        function activate() {
            CurrencyFactory.query({},
                function (data) {
                    $scope.currencies = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function deleteCurrency(id) {
            CurrencyFactory.delete({
                    id: id
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
