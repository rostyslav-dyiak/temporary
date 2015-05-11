(function () {
    'use strict';

    app.controller('UnitListController', UnitListController);
    UnitListController
        .$inject = [
        '$scope',
        'toaster',
        'UnitFactory'
    ];

    function UnitListController($scope, toaster, UnitFactory) {
        $scope.unit = {};
        $scope.units = [];
        $scope.rowCollection = [];
        $scope.addNewUnit = addNewUnit;
        $scope.removeUnit = removeUnit;
        $scope.editUnit = editUnit;
        $scope.saveUnit = saveUnit;
        $scope.cancel = cancel;
        $scope.componentIndex = null;

        activate();

        function activate() {
            UnitFactory.query({},
                function (data) {
                    $scope.units = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function addNewUnit() {
            $scope.unit = {};
            $scope.newUnit = true;
            $scope.isCompositeUnit = false;
            $scope.unit.available = true;
            $scope.composeUnits = $scope.units;
        }

        function removeUnit(id) {
            UnitFactory.delete({
                    id: id
                },
                function () {
                    toaster.pop('success', 'Success', 'Unit removed');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function editUnit(unit) {
            $scope.unit = unit;
            $scope.composeUnits = [];

            if($scope.unit.component == null){
                $scope.isCompositeUnit = false;
            }else{
                $scope.isCompositeUnit = true;
            }
            for(var i = 0; i < $scope.units.length; i++){
                var composeUnit = $scope.units[i];
                if(composeUnit.id != $scope.unit.id)
                {
                    $scope.composeUnits.push(composeUnit);
                }
            }

        }

        function saveUnit() {

            if($scope.componentIndex){
                var component = $scope.composeUnits[$scope.componentIndex];
                $scope.unit.component = component;
            }
            if(!$scope.isCompositeUnit){
                $scope.unit.component = null;
            }
            UnitFactory.update(
                $scope.unit
            , function (data) {
                toaster.pop('success', 'Success', 'Unit saved');
                activate();
            }, function (e) {
                console.error(e);
                toaster.pop('error', 'Error', 'Please try again');
            });

        }

        function cancel() {
            UnitFactory.get({
                    id: $scope.unit.id
                },
                function (data) {
                    for(var i = 0; i < $scope.units.length; i++){
                        var changedUnit = $scope.units[i];
                        if(changedUnit.id == $scope.unit.id)
                        {
                            $scope.units[i] = data;
                            $scope.unit = $scope.units[i];
                        }
                    }

                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }
    }

})();
