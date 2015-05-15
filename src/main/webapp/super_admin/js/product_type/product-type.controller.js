(function () {
    'use strict';

    app.controller('ProductTypeController', ProductTypeController);

    ProductTypeController
        .$inject = [
        '$scope',
        'toaster',
        'ProductTypeFactory'
    ];

    function ProductTypeController($scope, toaster, ProductTypeFactory) {
        $scope.type = {};
        $scope.selectedType = {};
        $scope.types = [];

        $scope.saveType = saveType;
        $scope.editType = editType;
        $scope.removeType = removeType;
        $scope.newProductType = newProductType;

        activate();

        function activate() {
            ProductTypeFactory.query({},
                function (data) {
                    $scope.types = angular.copy(data);
                    //Delete this
                    for(var i=0; i<$scope.types.length; i++) {
                        $scope.types[i].order = i;
                    }
                    // End of delete
                    $scope.types.sort(function (a, b) {
                        return a.order > b.order;
                    });
                }, function (e) {
                    console.error(e);
                });
        }

        $scope.sortableOptions = {
            stop: function () {
                for (var index in $scope.types) {
                    $scope.types[index].order = index;
                }
            }
        };

        function saveType() {
            ProductTypeFactory.update({
                id: $scope.type.id,
                name: $scope.type.name,
                description: $scope.type.description
            }, function () {
                toaster.pop('success', 'Success', 'Product type saved');
                activate();
            }, function (e) {
                console.error(e);
                toaster.pop('error', 'Error', 'Please try again');
            });

        }

        function newProductType() {
            $scope.type = {};
            $scope.newType = true;
        }


        function removeType(id) {
            ProductTypeFactory.delete({
                    id: id
                },
                function () {
                    toaster.pop('success', 'Success', 'Product type removed');
                    activate();
                }, function (e) {
                    console.error(e);
                    toaster.pop('error', 'Error', 'Please try again');
                });
        }

        function editType(type) {
            $scope.newType = false;
            $scope.selectedType = type;
            $scope.type.id = type.id;
            $scope.type.name = type.name;
            $scope.type.description = type.description;
        }

    }
})();
