(function () {
    'use strict';

    app.controller('ProductTypeController', ProductTypeController);

    ProductTypeController
        .$inject = [
        '$scope',
        'ProductTypeFactory'
    ];

    function ProductTypeController($scope, ProductTypeFactory) {
        $scope.types = [];
        $scope.save = save;
        $scope.editType = editType;
        $scope.removeType = removeType;

        activate();

        function activate() {
            ProductTypeFactory.query({},
                function (data) {
                    $scope.types = data.types;
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


        function save(id) {
            console.log("Saved type with id: " + id);
        }

        function editType(id) {
            ProductTypeFactory.get({
                    id: id
                },
                function (data) {
                    $scope.types = data.types;
                }, function (e) {
                    console.error(e);
                });

        }

        function removeType(id) {
            ProductTypeFactory.delete({
                        id: id
                    },
                    function () {
                       console.log("Deleted type with id: " + id)
                    }, function (e) {
                        console.error(e);
                    });
        }
    }
})();
