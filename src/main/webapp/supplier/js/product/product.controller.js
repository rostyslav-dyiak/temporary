(function () {
    'use strict';

    app.controller('ProductController', ProductController);
    ProductController
        .$inject = [
        '$scope',
        'toaster',
        'ProductFactory',
        'UnitFactory'
    ];

    function ProductController($scope, toaster, ProductFactory, UnitFactory) {
        $scope.product = {};
        $scope.units = [];
        $scope.products = [];
        $scope.rowCollection = [];

        activate();

        function activate() {
            UnitFactory.query({},
                function (data) {
                    $scope.units = data;
                }, function (e) {
                    console.error(e);
                });
            ProductFactory.query({},
                function (data) {
                    for(var i = 0; i < data.length; i++){
                        for(var j = 0; j < $scope.units.length; j++){
                            if($scope.units[j].id == data[i].unitId){
                                data[i].unitId = $scope.units[i];
                            }
                        }

                    }
                    $scope.products = data;
                    $scope.rowCollection = data;
                }, function (e) {
                    console.error(e);
                });
        }
    }

})();
