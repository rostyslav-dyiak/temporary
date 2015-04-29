(function () {
    'use strict';

    app.controller('AreaDeliveryController', AreaDeliveryController);

    AreaDeliveryController
        .$inject = [
        '$scope',
        'DistrictFactory'
    ];

    function AreaDeliveryController($scope, DistrictFactory) {
        $scope.districts = {};

        $scope.saveDistrict = saveDistrict;

        activate();

        function activate() {
            DistrictFactory.query({},
            function(data) {
                $scope.districts = data;
            },
            function(e) {
                console.log(e);
            });
        }

        function saveDistrict(district) {
            //DistrictFactory.save(district,
            //    function(data) {
            //        activate();
            //    },
            //    function(e) {
            //        console.log(e);
            //    });
            console.log("Saved district " + district.id);
        }
    }
})();
