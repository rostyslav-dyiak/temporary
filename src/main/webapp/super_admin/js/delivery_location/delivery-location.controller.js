(function () {
    'use strict';

    app.controller('DeliveryLocationController', DeliveryLocationController);

    DeliveryLocationController
        .$inject = [
        '$scope',
        'toaster',
        'DistrictFactory'
    ];

    function DeliveryLocationController($scope, toaster, DistrictFactory) {
        var master = {};
        $scope.selectedDistrict = {};
        $scope.districts = [];

        $scope.saveDistrict = saveDistrict;
        $scope.editDistrict = editDistrict;
        $scope.addDistrict = addDistrict;
        $scope.removeDistrict = removeDistrict;
        $scope.addPostalCode = addPostalCode;
        $scope.removePostalCode = removePostalCode;
        $scope.cancel = cancel;

        activate();

        function activate() {
            DistrictFactory.query({},
                function (data) {
                    $scope.districts = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function saveDistrict() {
            DistrictFactory.update($scope.selectedDistrict,
                function () {
                    toaster.pop('success', 'Success', 'District has been saved');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });

        }

        function addDistrict() {
            $scope.selectedDistrict = {};
            $scope.newType = true;
        }

        function removeDistrict() {
            DistrictFactory.delete({
                    id: $scope.selectedDistrict.id
                },
                function () {
                    toaster.pop('success', 'Success', 'District has been removed');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function editDistrict(district) {
            $scope.selectedDistrict = angular.copy(district);
            master = $scope.selectedDistrict;
        }

        function addPostalCode() {
            if(!$scope.selectedDistrict.postalCodes) {
                $scope.selectedDistrict.postalCodes = [];
            }
            $scope.selectedDistrict.postalCodes.push('');
        }

        function removePostalCode(code) {
            var index = $scope.selectedDistrict.postalCodes.indexOf(code);
            $scope.selectedDistrict.postalCodes.splice(index, 1);
        }

        function cancel() {
            $scope.selectedDistrict = master;
        }

    }
})();
