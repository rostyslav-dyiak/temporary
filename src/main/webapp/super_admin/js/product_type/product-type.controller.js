(function () {
    'use strict';

    app.controller('ProductTypeController', ProductTypeController);

    ProductTypeController
        .$inject = [
        '$scope',
        'toaster',
        'ProductTypeFactory'
    ];

    function ProductTypeController($scope,toaster, ProductTypeFactory) {
        $scope.type = {};
        $scope.types = [];
        $scope.saveType = saveType;
        $scope.editType = editType;
        $scope.removeType = removeType;
        $scope.newProductType = newProductType;

        activate();

        function activate() {
            ProductTypeFactory.query({},
                function (data) {
                    $scope.types = data;
                }, function (e) {
                    console.error(e);
                });
        }


        $scope.sortableOptions = {
            stop: function (e, ui) {
                var logEntry = $scope.types.map(function (i) {
                    return i.order;
                }).join(', ');
                console.log('Stop: ' + logEntry);

            }
        };


        function saveType() {
            ProductTypeFactory.update({
                id: $scope.type.id,
                name: $scope.type.name,
                description: $scope.type.description
            }, function (data) {
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
            $scope.type.id = type.id;
            $scope.type.name = type.name;
            $scope.type.description = type.description;
        }

    }
})();
