(function () {
    'use strict';

    app.controller('PaymentTermController', PaymentTermController);

    PaymentTermController
        .$inject = [
        '$scope',
        'toaster',
        'PaymentFactory'
    ];

    function PaymentTermController($scope, toaster, PaymentFactory) {
        $scope.selectedPayment = {};
        $scope.payments = [];

        $scope.types = ['COD', 'EOM', 'NET', 'MFI'];

        $scope.savePayment = savePayment;
        $scope.editPayment = editPayment;
        $scope.addPayment = addPayment;
        $scope.removePayment = removePayment;
        $scope.addPostalCode = addPostalCode;
        $scope.removePostalCode = removePostalCode;
        $scope.cancel = cancel;

        activate();

        function activate() {
            PaymentFactory.query({},
                function (data) {
                    $scope.payments = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function savePayment() {
            PaymentFactory.update($scope.selectedPayment,
                function () {
                    toaster.pop('success', 'Success', 'Payment has been saved');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });

        }

        function addPayment() {
            $scope.selectedPayment = {};
            $scope.newType = true;
        }

        function removePayment() {
            PaymentFactory.delete({
                    id: $scope.selectedPayment.id
                },
                function () {
                    toaster.pop('success', 'Success', 'Payment has been removed');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function editPayment(payment) {
            $scope.selectedPayment = angular.copy(payment);
        }

        function addPostalCode() {
            if(!$scope.selectedPayment.postalCodes) {
                $scope.selectedPayment.postalCodes = [];
            }
            $scope.selectedPayment.postalCodes.push('');
        }

        function removePostalCode(code) {
            var index = $scope.selectedPayment.postalCodes.indexOf(code);
            $scope.selectedPayment.postalCodes.splice(index, 1);
        }

        function cancel() {
            $scope.selectedPayment = {};
        }

    }
})();
