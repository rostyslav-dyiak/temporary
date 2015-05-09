(function () {
    'use strict';

    app.controller('TaxTypeController', TaxTypeController);

    TaxTypeController
        .$inject = [
        '$scope',
        'toaster',
        'TaxTypeFactory'
    ];

    function TaxTypeController($scope, toaster, TaxTypeFactory) {
        $scope.taxTypes = [];
        $scope.rowCollection = [];

        $scope.delete = deleteCurrency;

        activate();

        function activate() {
            TaxTypeFactory.query({},
                function (data) {
                    $scope.taxTypes = data;
                    $scope.rowCollection = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function deleteCurrency(id) {
            TaxTypeFactory.delete({
                    id: id
                },
                function () {
                    activate();
                    toaster.pop('success', 'Success', 'Currency has been deleted');
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }


    }
})();
