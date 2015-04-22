(function () {
    'use strict';

    app.controller('ProductTypeController', ProductTypeController);

    ProductTypeController
        .$inject = [
        '$scope',
        'ProductTypeFactory'
    ];

    function ProductTypeController($scope, ProductTypeFactory) {
        $scope.type = {};

        $scope.save = save;
        $scope.editType = editType;
        $scope.removeType = removeType;

        activate();

        function activate() {
            ProductTypeFactory.get({},
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
            //ProductTypeFactory.get({
            //        id: id
            //    },
            //    function (data) {
            //        $scope.types = data.types;
            //    }, function (e) {
            //        console.error(e);
            //    });
            if (id == 0) {
                $scope.type = {
                    id: 0,
                    name: 'Staples',
                    description: 'Some dummy text 0',
                    value: 0
                }
            } else if (id == 1) {
                $scope.type = {
                    id: 1,
                    name: 'Dried Stuff',
                    description: 'Some dummy text 1',
                    value: 2
                }
            } else if (id == 3) {
                $scope.type = {
                    id: 3,
                    name: 'Frozen Stuff',
                    description: 'Some dummy text 3',
                    value: 4
                }
            }

        }

        function removeType(id) {
            //ProductTypeFactory.delete({
                //        id: id
                //    },
                //    function () {
                //       console.log("Deleted type with id: " + id)
                //    }, function (e) {
                //        console.error(e);
                //    });
            console.log("Deleted type with id: " + id);
        }
    }
})();
